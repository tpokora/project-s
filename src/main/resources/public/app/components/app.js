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

        .when('/user/list', {
            templateUrl: 'views/users/users.html',
            controller: 'UsersController as usersCtrl',
        })

        .when('/user/:id', {
            templateUrl: 'views/users/user.html',
            controller: 'UsersController as usersCtrl',
        })

        .when('/new/user', {
            templateUrl: 'views/users/user_new.html',
            controller: 'UserNewController as userNewCtrl'
        })

        .when('/user/:id/update', {
            templateUrl: 'views/users/user_update.html',
            controller: 'UserUpdateController as userUpdCtrl',
        })

        .when('/user/:id/delete', {
            templateUrl: 'views/users/user_delete.html',
            controller: 'UserDeleteController as userDltCtrl',
        })

        .when('/admin', {
            templateUrl: 'views/admin/panel.html',
            controller: 'AdminPanelController as adminPanelCtrl'
        })

        .otherwise({redirectTo:'/home'});

        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
}]);