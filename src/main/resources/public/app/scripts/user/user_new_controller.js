'use strict'

App.controller('UserNewController', ['$scope', '$location', 'UserService', function($scope, $location, UserService) {
    var self = this;
    self.user = {
        id: '',
        username: '',
        password: '',
        password1: '',
        email: ''
    };

    $scope.errors = {
        error: ''
    };

    self.passwordCheck = function(password, password1) {
        if (password === password1) {
            return true;
        }
        return false;
    };

    $scope.createUser = function() {
        self.user = $scope.user;
        var passwordIdentical = self.passwordCheck(self.user.password, self.user.password1);
        if(passwordIdentical) {
            UserService.createUser(self.user)
                .then(function() {
                    $location.path('/home');
                });
        } else {
            $scope.errors.error = 'passwords.diff';
            console.log('Different passwords');
        }
    };

    $scope.back = function() {
        $location.path('/user/list');
    };

}]);