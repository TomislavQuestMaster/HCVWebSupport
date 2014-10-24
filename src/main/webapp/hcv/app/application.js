var app = angular.module('Application', ['ui.bootstrap', 'ngRoute', 'ngTable', 'ui.tree']);

app.config(['$routeProvider', function ($routeProvider) {

    $routeProvider
        .when('/', {
            templateUrl: 'shopAdmin/shopAdmin.html',
            controller: 'ShopAdminController'
        })
        .when('/packages', {
            templateUrl: 'shopAdmin/shopAdmin.html',
            controller: 'ShopAdminController'
        });
}]);