'use strict'

App.controller('UserResetController', ['$scope', '$location', '$stateParams', 'UserService', function($scope, $location, $stateParams, UserService) {
    var self = this;
    var params = $stateParams;
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

    if ($location.path().indexOf('resetPassword') > -1) {
        console.log("link: /rest/user/reset/" + params.sessionID + "/changePassword");
        UserService.resetUserPassword(params.sessionID)
            .then(function() {
                console.log('User password reseted');
                $location.path('/login');
            });
    }


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