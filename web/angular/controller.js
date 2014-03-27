app.controller("MainController", function ($scope, $log) {

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

    $scope.markerClickEvent = {
        click: function (marker, eventName, args) {

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