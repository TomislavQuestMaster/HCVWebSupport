app.controller("MainController", function ($scope, $http, $log) {

    $scope.map = {
        center: {
            latitude: 42.641900799999990000,
            longitude: 18.106484899999940000
        },
        zoom: 15,
        events: {
            click: function (mapModel, eventName, originalEventArgs) {

                var e = originalEventArgs[0];

                $scope.addNew(e.latLng.lat(), e.latLng.lng());
                $scope.$apply();
            }
        }
    };

    $scope.markers = [
        {
            latitude: 42.641900799999990000,
            longitude: 18.106484899999940000
        }
    ];

    $scope.selected = 0;
    $scope.lines = [];
    $scope.begin = {
        location: {
            latitude: 42.641900799999990000,
            longitude: 18.106484899999940000
        },
        marker: null
    };

    $scope.response = {
        status: 0,
        message: "hello"
    };

    $scope.markerClickEvent = {
        click: function (marker, eventName, args) {

            //{ status:0, message:"hello"} {'Content-Type': 'application/json'}
            var postData = {text: 'long blob of text'};
            $http.post('http://localhost:8080/hook', 'long blob of text'
                ).success(function(data, status, headers, config) {
                    alert("aha");
                }).error(function(data, status, headers, config) {
                    $log.log(data, status, headers, config);
                });


            if ($scope.selected == 0) {
                $scope.begin.location = {
                    latitude: marker.getPosition().lat(),
                    longitude: marker.getPosition().lng()
                };
                $scope.selected = 1;
                $scope.begin.marker = marker;
                marker.setAnimation(google.maps.Animation.BOUNCE);
            }
            else if ($scope.selected == 1) {
                $scope.lines.push(
                    [
                        $scope.begin.location,
                        {
                            latitude: marker.getPosition().lat(),
                            longitude: marker.getPosition().lng()
                        }
                    ]
                );
                $scope.begin.marker.setAnimation(null);
                $scope.selected = 0;
            }
            $scope.$apply();
        }
    };

    $scope.addNew = function (lat, lon) {
        $scope.markers.push({
            latitude: lat,
            longitude: lon
        });
    };


});