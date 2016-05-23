'use strict'

var App = angular.module('myApp', [
    'ui.router'
     ])
    .directive('appTitle', function() {
        return {
            template: 'Spring Template Application'
        };
    });

App.provider('APIConfig', function() {

    this._url = '';
    this._port = '';

    this.$get = function($http, $q) {
      var that = this;
      return {
        API_END_POINT: function() {
          return 'http://' + that._url + ':' + that._port;
        }
      }
    };
});

App.config(['$stateProvider', '$urlRouterProvider', '$httpProvider', 'APIConfigProvider', function($stateProvider, $urlRouterProvider, $httpProvider, APIConfigProvider) {

    APIConfigProvider._url = $ENV_URL$;
    APIConfigProvider._port = $ENV_PORT$;

    $urlRouterProvider.otherwise('/home')

    $stateProvider

        // Main page ui-views
        .state('home', {
            url: '/home',
            views: {
                'main-view': {
                    templateUrl: '../views/home.html',
                    controller: 'HomeController as homeCtrl'
                }
            }

        })
        .state('login', {
            url: '/login',
            views: {
                'main-view': {
                    templateUrl: '../views/login.html'
                },
            }
        })

        // User ui-views
        .state('users', {
            url: '/user/list',
            views: {
                'main-view': {
                    templateUrl: '../views/users/users.html',
                    controller: 'UsersController as usersCtrl'
                }
            }

        })
        .state('user', {
            url: '/user/{id:int}',
            views: {
                'main-view': {
                    templateUrl: '../views/users/user.html',
                    controller: 'UsersController as usersCtrl'
                }
            }
        })
        .state('user_new', {
            url: '/user/new',
            views: {
                'main-view': {
                    templateUrl: '../views/users/user_new.html',
                    controller: 'UserNewController as userNewCtrl'
                }
            }

        })
        .state('user_update', {
            url: '/user/{id:int}/update',
            views: {
                'main-view': {
                    templateUrl: '../views/users/user_update.html',
                    controller: 'UserUpdateController as userUpdCtrl'
                }
            }
        })
        .state('user_delete', {
            url: '/user/{id:int}/delete',
            views: {
                'main-view': {
                    templateUrl: '../views/users/user_delete.html',
                    controller: 'UserDeleteController as userDltCtrl'
                }
            }

        })

        // Admin ui-views
        .state('admin', {
            url: '/admin',
            views: {
                'main-view': {
                    templateUrl: '../views/admin/panel.html'
                }
            }

        })
        .state('admin.home', {
            url: '/home',
            views: {
                'admin-view': {
                    templateUrl: '../views/admin/admin_home.html'
                }
            }
        })
        .state('admin.users', {
            url: '/users',
            views: {
                'admin-view': {
                    templateUrl: '../views/admin/admin_users.html',
                    controller: 'UsersController as usersCtrl'
                }
            }
        })
        .state('admin.user_update', {
            url: '/user/{id:int}/update',
            views: {
                'admin-view': {
                    templateUrl: '../views/users/user_update.html',
                    controller: 'UserUpdateController as userUpdCtrl'
                }
            }
        })
        .state('admin.user_delete', {
            url: '/user/{id:int}/delete',
            views: {
                'admin-view': {
                    templateUrl: '../views/users/user_delete.html',
                    controller: 'UserDeleteController as userDltCtrl'
                }
            }
        })
        .state('admin.database', {
            url: '/database',
            views: {
                'admin-view': {
                    templateUrl: '../views/admin/admin_database.html',
                    controller: 'DatabaseController as databaseCtrl'
                }
            }
        })

        // Article ui-views
        .state('articles', {
            url: '/articles',
            views: {
                'main-view' : {
                    templateUrl: '../views/article/articles.html',
                    controller: 'ArticleController as articleCtrl'
                }
            }

        })

        .state('article', {
            url: '/article/{id:int}',
            views: {
                'main-view' : {
                    templateUrl: '../views/article/article.html',
                    controller: 'ArticleController as articleCtrl'
                }
            }
        })

        .state('article_new', {
            url: '/article/new',
            views: {
                'main-view' : {
                        templateUrl: '../views/article/article_new.html',
                        controller: 'ArticleController as articleCtrl'
                }
            }
        });

        $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
}]);
