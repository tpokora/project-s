'use strict'

App.controller('UserUpdateController', function($rootScope, $scope, $location, $stateParams, UserService) {
    var self = this;
    var params = $stateParams;
    self.userToUpdate = {
        id: '',
        username: '',
        password: '',
        email: ''
    };

    var backUrl = '/users';

    self.fetchUserById = function(id) {
        UserService.fetchUserById(id)
            .then(
                function(data) {
                    $scope.user = data;
                }, function(errResponse) {
                    console.error('Error while fetching user');
                }
            );
    };

    self.setBackUrl = function() {
        if ($location.path().indexOf('admin') > -1) {
            backUrl = '/admin/users';
        }
    }

    if ($location.path().indexOf('profile') > -1) {
        self.fetchUserById($rootScope.loggedUser.id);
    } else {
        self.fetchUserById(params.id);
    }


    self.setBackUrl();

    function checkUpdatePasswords(firstPassword, secondPassword) {
        if (firstPassword == secondPassword) {
            return true;
        } else {
            return false;
        };
    };

    $scope.updateUser = function() {
        self.userToUpdate = $scope.user;
        if ($scope.newPassword != '') {
            if ($scope.newPassword.length > 2) {
                if (checkUpdatePasswords($scope.newPassword, $scope.newPasswordRepeat)) {
                    self.userToUpdate.password = $scope.newPassword;
                    $scope.updateError = '';
                    UserService.updateUser($scope.user.id, self.userToUpdate)
                        .then(
                            function() {
                                $location.path(backUrl);
                            }
                         );
                    } else {
                        $scope.updateError = 'Password are not identical!';
                    }
            } else {
                $scope.updateError = 'New password to short! (min. 3 char)';
            }
        }
    };

    $scope.back = function() {
        $location.path(backUrl);
    };
});