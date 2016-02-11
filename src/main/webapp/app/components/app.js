'use strict'

var App = angular.module('myApp', [
    'ngRoute',
    'restangular' ])
    .directive('appTitle', function() {
        return {
            template: 'Spring Template Application'
        };
    });

App.config(['$routeProvider', 'RestangularProvider', function($routeProvider, RestangularProvider) {

    RestangularProvider.setBaseUrl('http://localhost:8080/project-s');

    $routeProvider
        .when('/home', {
            templateUrl: 'home',
            controller: 'HomeController as homeCtrl',
        })

        .otherwise({redirectTo:'/home'});
}]);