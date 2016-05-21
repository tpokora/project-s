'use strict'

App.controller('AuthController', function($rootScope, $scope, $http, $location, ConfigService, UserService) {
    var self = this;

    $rootScope.loggedUser = '';

    var baseUrl = '';
    ConfigService.baseUrl().then(function(data) {
        baseUrl = data;
    });

    self.fetchUserByUsername = function(username) {
        UserService.fetchUserByUsername(username)
            .then(
                function(user) {
                    $rootScope.loggedUser = user;
                }, function(errResponse) {
                    console.error('Error while fetching user');
                }
            );
    };

    var authenticate = function(credentials, callback) {
        var headers = credentials ? { authorization : "Basic " +
            btoa(credentials.username + ":" + credentials.password)
        } : {};

        $http.get(baseUrl + '/auth', { headers : headers }).success(function(data) {
            if (data.name) {
                self.fetchUserByUsername(data.name);
                $rootScope.authenticated = true;
            } else {
                $rootScope.authenticated = false;
            }
            callback && callback();
        }).error(function() {
            $rootScope.authenticated = false;
            callback && callback();
        });
    }

    authenticate();
    $scope.credentials = {};
    $scope.login = function() {
        authenticate($scope.credentials, function() {
            if($rootScope.authenticated) {
                $location.path("/");
                $scope.error = false;
            } else {
                $location.path("/login");
                $scope.error = true;
            }
        });
    }

    $scope.logout = function() {
        $http.post('logout', {}).finally(function() {
            $rootScope.authenticated = false;
            $location.path("/");
        });
    }
});
