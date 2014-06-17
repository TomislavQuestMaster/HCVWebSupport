app.controller("ShopController", ['$scope', '$filter', '$log', '$http', 'ShopService',
    function ($scope, $filter, $log, $http, ShopService) {

        $scope.trainings = [];

        ShopService.getTrainings().then(function (data) {
            $scope.trainings = data;
        });

    }]);
