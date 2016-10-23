'use strict'

App.controller('UsersController', ['$scope', '$stateParams', '$location', 'UserService', function($scope, $stateParams, $location, UserService) {
    var self = this;
    var params = $stateParams;

    this.fetchAllUsers = function() {
            UserService.fetchAllUsers()
                .then(
                    function(users) {
                        $scope.users = users;
                    }, function(errResponse) {
                        console.error('Error while fetching users.');
                    }
                );
    };

    this.fetchUserById = function(id) {
        UserService.fetchUserById(id)
            .then(
                function(user) {
                    $scope.user = user;
                }, function(errResponse) {
                    console.error('Error while fetching user');
                }
            );
    };

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
            if (checkUpdatePasswords($scope.newPassword, $scope.newPasswordRepeat)) {
                // Password update
            } else {
                $scope.updateError = 'Password not the same';
            }
        }
    };

    this.fetchAllUsers();

    // Add user based on ID param
    if (params.id != null) {
        this.fetchUserById(params.id);
    }


}]);