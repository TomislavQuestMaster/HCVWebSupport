angular.module('Application').service('ShopService', ['$http', '$q', '$log', function ($http, $q, $log) {


    this.getTrainings = function () {

        var deferred = $q.defer();

        $http.get('/shopList')
            .success(function (data) {

                deferred.resolve(data);

            }).error(function (data, status, headers, config) {
                $log.log(data, status, headers, config);
            });

        return deferred.promise;
    };

}]);