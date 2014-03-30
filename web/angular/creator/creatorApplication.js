var app = angular.module('CreatorApp', ['google-maps']).filter('locationFilter', function(){

    return function(items, location){

        var arrayToReturn = [];
        for (var i=0; i<items.length; i++){
            if (items[i].location.latitude == location.latitude && items[i].location.longitude == location.longitude) {
                arrayToReturn.push(items[i]);
            }
        }

        return arrayToReturn;
    };
});