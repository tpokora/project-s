'use strict'

var App = angular.module('myApp', [
    'restangular',
    'ui.router'
     ])
    .directive('appTitle', function() {
        return {
            template: 'Spring Template Application'
        };
    });

App.config(['$stateProvider', '$urlRouterProvider', 'RestangularProvider', '$httpProvider', function($stateProvider, $urlRouterProvider, RestangularProvider, $httpProvider) {

    RestangularProvider.setBaseUrl('http://localhost:8080/');

    $urlRouterProvider.otherwise('/home')

    $stateProvider

        // Main page ui-views
        .state('home', {
            url: '/home',
            templateUrl: 'views/home.html',
            controller: 'HomeController as homeCtrl'
        })
        .state('login', {
            url: '/login',
            templateUrl: 'views/login.html'
        })
        .state('content', {
            url: '/content',
            templateUrl: 'views/content.html'
        })
        .state('users', {
            url: '/user/list',
            templateUrl: 'views/users/users.html',
            controller: 'UsersController as usersCtrl'
        })
        .state('user', {
            url: '/user/{id:int}',
            templateUrl: 'views/users/user.html',
            controller: 'UsersController as usersCtrl'
        })
        .state('user_new', {
            templateUrl: 'views/users/user_new.html',
            controller: 'UserNewController as userNewCtrl'
        })
        .state('user_update', {
            url: '/user/{id:int}/update',
            templateUrl: 'views/users/user_update.html',
            controller: 'UserUpdateController as userUpdCtrl'
        })
        .state('user_delete', {
            url: '/user/{id:int}/delete',
            templateUrl: 'views/users/user_delete.html',
            controller: 'UserDeleteController as userDltCtrl'
        })

        // Admin ui-views
        .state('admin', {
            url: '/admin',
            templateUrl: 'views/admin/panel.html'
        })
        .state('admin.home', {
            url: '/admin/home',
            templateUrl: 'views/admin/admin_home.html'
        })
        .state('admin.database', {
            url: '/admin/database',
            templateUrl: 'views/admin/admin_database.html'
        })

        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
}]);