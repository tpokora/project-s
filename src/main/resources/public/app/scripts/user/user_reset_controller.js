'use strict'

App.controller('UserResetController', ['$scope', '$location', 'UserService', function($scope, $location, UserService) {
    var self = this;
    self.user = {
        email: ''
    };

    self.userToReset = {
        id: '',
        username: '',
        email: '',
        role: ''
    };

    $scope.errors = {
        error: ''
    };

    $scope.resetPassword = function() {
        self.user = $scope.user;
        UserService.fetchUserByEmail(self.user.email).then(function(response) {
            self.userToReset = response;
            if (self.userToReset != null) {
                UserService.resetUserPasswordSession(self.userToReset)
                    .then(function() {
                        console.log('New user reset password session created');
                    });
            } else {
                console.error('User not exists');
            }
        });
    };

    $scope.back = function() {
        $location.path('/login');
    };

}]);