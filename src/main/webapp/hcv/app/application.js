var app = angular.module('Application', ['ui.bootstrap', 'ngRoute', 'ngTable', 'ui.tree']);

app.config(['$routeProvider', function ($routeProvider) {

    $routeProvider
        .when('/shop', {
            templateUrl: 'shop/shop.html',
            controller: 'ShopController'
        })
        .when('/packages', {
            templateUrl: 'shopAdmin/shopAdmin.html',
            controller: 'ShopAdminController'
        });
}]);