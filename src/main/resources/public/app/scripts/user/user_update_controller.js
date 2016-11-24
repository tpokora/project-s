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

    function fetchUserById(id) {
        UserService.fetchUserById(id)
            .then(
                function(data) {
                    $scope.user = data;
                }, function(errResponse) {
                    console.error('Error while fetching user');
                }
            );
    };

    self.setResponseMsg = function() {
        $scope.responseMsg = '';
    }

    self.setBackUrl = function() {
        if ($location.path().indexOf('admin') > -1) {
            backUrl = '/admin/users';
        }
    }

    self.setBackUrl();

    if ($location.path().indexOf('profile') > -1) {
        backUrl = '/profile';
        fetchUserById($rootScope.loggedUser.id);
    } else {
        fetchUserById(params.id);
    }

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
                    UserService.updateUser($scope.user.id, self.userToUpdate)
                        .then(
                            function() {
                                console.log('Password changed!');
                                $scope.responseMsg = 'Password changed!';
                                $location.path(backUrl);
                            }
                         );
                    } else {
                        $scope.responseMsg = 'Password are not identical!';
                    }
            } else {
                $scope.responseMsg = 'New password to short! (min. 3 char)';
            }
        }
    };

    $scope.back = function() {
        $location.path(backUrl);
    };
});