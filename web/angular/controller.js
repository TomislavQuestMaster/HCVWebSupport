
app.controller("MainController", function($scope,$log){

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

    $scope.addNew = function(lat,lon) {

        $scope.markers.push({
            latitude: lat,
            longitude: lon
        });

    }

});