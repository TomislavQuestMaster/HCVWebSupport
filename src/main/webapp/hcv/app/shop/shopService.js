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

    this.getUnsortedTrainingsFromShop = function () {

        var deferred = $q.defer();

        $http.get('/shop/trainings/unsorted')
            .success(function (data) {

                deferred.resolve(data);

            }).error(function (data, status, headers, config) {
                $log.log(data, status, headers, config);
            });

        return deferred.promise;
    };

    this.getTrainingsFromShop = function () {

        var deferred = $q.defer();

        $http.get('/shop/trainings')
            .success(function (data) {

                deferred.resolve(data);

            }).error(function (data, status, headers, config) {
                $log.log(data, status, headers, config);
            });

        return deferred.promise;
    };

    this.getPackagesFromShop = function () {

        var deferred = $q.defer();

        $http.get('/shop/packages')
            .success(function (data) {

                deferred.resolve(data);

            }).error(function (data, status, headers, config) {
                $log.log(data, status, headers, config);
            });

        return deferred.promise;
    };

    this.getPackagesFromShop = function () {

        var deferred = $q.defer();

        $http.get('/shop/packages')
            .success(function (data) {

                deferred.resolve(data);

            }).error(function (data, status, headers, config) {
                $log.log(data, status, headers, config);
            });

        return deferred.promise;
    };

    this.addPackage = function (packageItem) {

        var deferred = $q.defer();

        var packageToSend = angular.copy(packageItem);

        delete packageToSend.sortOrder;
        delete packageToSend.type;

        $http.post('/shop/package', packageToSend, {headers: {
            'Content-Type': 'application/json' }})
            .success(function (id) {

                deferred.resolve(id);

            }).error(function (data, status, headers, config) {
                $log.log(data, status, headers, config);
            });

        return deferred.promise;
    };

    this.updatePackage = function (packageItem) {

        var packageToSend = angular.copy(packageItem);

        delete packageToSend.sortOrder;
        delete packageToSend.type;

        packageToSend.trainings.forEach(function(training){
            delete training.type;
        });

        $http.put('/shop/package', packageToSend, {headers: {
            'Content-Type': 'application/json' }})
            .success(function () {})
            .error(function (data, status, headers, config) {
                $log.log(data, status, headers, config);
            });
    };

    this.removePackage = function (packageId) {

        var deferred = $q.defer();

        $http.delete('/shop/package/'+packageId)
            .success(function () {
                deferred.resolve();
            })
            .error(function (data, status, headers, config) {
                $log.log(data, status, headers, config);
            });

        return deferred.promise;
    };

}]);