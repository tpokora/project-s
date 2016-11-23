'use strict'

App.controller('AuthController', function($rootScope, $scope, $http, $location, APIConfig, UserService) {
    var self = this;

    $rootScope.loggedUser = '';

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
        var headers = credentials ? { authorization : 'Basic ' +
            btoa(credentials.username + ':' + credentials.password)
        } : {};

        $http.get(APIConfig.API_END_POINT() + '/auth', { headers : headers }).success(function(data) {
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
    };

    authenticate();
    $scope.credentials = {};
    $scope.login = function() {
        authenticate($scope.credentials, function() {
            if($rootScope.authenticated) {
                $location.path('/');
                $scope.error = false;
            } else {
                $location.path('/login');
                $scope.error = true;
            }
        });
    };

    $scope.logout = function() {
        $http.post('logout', {})
        .error(function(data) {
            $rootScope.authenticated = false;
        }).finally(function() {
        $rootScope.authenticated = false;
            $location.path('/');
            console.log('Logout successful');
        }).catch(function(e) {
            console.error('Unknown error during logout');
        });
    };
});
