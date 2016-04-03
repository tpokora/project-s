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
            url: '/home',
            templateUrl: 'views/admin/admin_home.html'
        })
        .state('admin.users', {
            url: '/users',
            templateUrl: 'views/admin/admin_users.html',
            controller: 'UsersController as usersCtrl'
        })
        .state('admin.user_update', {
            url: '/user/{id:int}/update',
            templateUrl: 'views/users/user_update.html',
            controller: 'UserUpdateController as userUpdCtrl'
        })
        .state('admin.user_delete', {
            url: '/user/{id:int}/delete',
            templateUrl: 'views/users/user_delete.html',
            controller: 'UserDeleteController as userDltCtrl'
        })
        .state('admin.database', {
            url: '/database',
            templateUrl: 'views/admin/admin_database.html',
            controller: 'DatabaseController as databaseCtrl'
        })

        // Article ui-views
        .state('articles', {
            url: '/articles',
            templateUrl: 'views/article/articles.html',
            controller: 'ArticleController as articleCtrl'
        })

        .state('article', {
            url: '/article/{id:int}',
            templateUrl: 'views/article/article.html',
            controller: 'ArticleController as articleCtrl'
        })

        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
}]);