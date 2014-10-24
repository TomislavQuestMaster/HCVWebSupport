app.controller("ShopAdminController", ['$scope', '$filter', '$log', '$http', 'ShopService',
    function ($scope, $filter, $log, $http, ShopService) {

        $scope.filter = {};

        $scope.unsortedPackage = {
            id: 0,
            name: "UNSORTED",
            type: "package",
            description: "",
            keypoints: "",
            trainings: [],
            sortOrder: 1
        };

        $scope.packages = [];
        $scope.packages.push($scope.unsortedPackage);

        $scope.trainings = [];
        ShopService.getTrainingsFromShop().then(function (data) {
            $scope.trainings = data;
        });

        ShopService.getPackagesFromShop().then(function (data) {
            data.forEach(function (item) {
                item.sortOrder = $scope.packages.length + 1;
                item.type = "package";
                item.trainings.forEach(function (training) {
                    training.type = 'training';
                });
                $scope.packages.push(item);
            });
        });

        ShopService.getUnsortedTrainingsFromShop().then(function (trainings) {
            trainings.forEach(function (training) {
                training.type = 'training';
            });
            $scope.unsortedPackage.trainings = trainings;
        });

        $scope.editPackage = {};

        $scope.addPackage = function () {
            $scope.editPackage = {
                id: -1,
                name: "Test",
                type: "package",
                trainings: [],
                sortOrder: $scope.packages.length
            };
        };

        $scope.editThePackage = function (trainingsPackage) {
            $scope.editPackage = trainingsPackage;
        };

        $scope.savePackage = function (trainingsPackage) {

            if (trainingsPackage.id != -1) {
                ShopService.updatePackage(trainingsPackage);
                return;
            }

            ShopService.addPackage(trainingsPackage).then(function (id) {
                trainingsPackage.id = id;
                $scope.packages.push(trainingsPackage);
            });
        };

        $scope.removePackage = function (trainingsPackage) {
            if (trainingsPackage.id == 0) {
                window.alert("This package cannot be removed");
            }
            if (window.confirm('Are you sure to remove this package?')) {
                var index = $scope.packages.indexOf(trainingsPackage);
                if (index > -1) {

                    ShopService.removePackage(trainingsPackage.id).then(function () {

                        $scope.packages.forEach(function (pack) {
                            if (pack.id == 0) {
                                $scope.packages[index].trainings.forEach(function (training) {
                                    training.sortOrder = pack.trainings.length;
                                    pack.trainings.push(training);
                                });
                            }
                        });

                        $scope.packages.splice(index, 1)[0];

                    });
                }
            }
        };

        $scope.savePackages = function () {
            for (var i = $scope.packages.length - 1; i >= 0; i--) {
                var trainingPackage = $scope.packages[i];
                trainingPackage.sortOrder = i + 1;
                if(trainingPackage.id != 0){
                    $scope.savePackage(trainingPackage);
                }
            }
        };

        $scope.options = {
            accept: function (sourceNode, destNodes, destIndex) {
                var data = sourceNode.$modelValue;
                var destType = destNodes.$element.attr('type');
                return (data.type == destType); // only accept the same type
            },
            dropped: function (event) {
                console.log(event);
                var sourceNode = event.source.nodeScope;
                var destNodes = event.dest.nodesScope;
                // update changes to server
                if (destNodes.isParent(sourceNode)
                    && destNodes.$element.attr('type') == 'training') { // If it moves in the same group, then only update group
                    $scope.savePackages();
                    //var group = destNodes.$nodeScope.$modelValue;
                    //group.save();
                } else { // save all
                    $scope.savePackages();
                }
            },
            beforeDrop: function (event) {
                //if (!window.confirm('Are you sure you want to drop it here?')) {
                //    event.source.nodeScope.$$apply = false;
                //}
            }
        };

    }]);
