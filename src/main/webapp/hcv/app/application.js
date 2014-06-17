var app = angular.module('Application', ['ui.bootstrap', 'ngRoute', 'ngTable']);

app.config(['$routeProvider', function ($routeProvider) {

    $routeProvider
        .when('/shop', {
            templateUrl: 'shop/shop.html',
            controller: 'ShopController'
        });
}]);