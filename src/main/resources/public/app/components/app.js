'use strict'

var App = angular.module('myApp', [
    'ngRoute',
    'restangular' ])
    .directive('appTitle', function() {
        return {
            template: 'Spring Template Application'
        };
    });

App.config(['$routeProvider', 'RestangularProvider', '$httpProvider', function($routeProvider, RestangularProvider, $httpProvider) {

    RestangularProvider.setBaseUrl('http://localhost:8080/');

    $routeProvider
        .when('/home', {
            templateUrl: 'views/home.html',
            controller: 'HomeController as homeCtrl',
        })

        .when('/login', {
            templateUrl: 'views/login.html',

        })

        .when('/content', {
            templateUrl: 'views/content.html'
        })

        .otherwise({redirectTo:'/home'});

        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
}]);