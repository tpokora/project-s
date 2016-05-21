'use strict'

App.controller('UserUpdateController', ['$scope', '$location', '$stateParams', 'UserService', function($scope, $location, $stateParams, UserService) {
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


    self.fetchUserById(params.id);
    self.setBackUrl();

    $scope.updateUser = function() {
        self.userToUpdate = $scope.user;
        UserService.updateUser(params.id, self.userToUpdate)
            .then(
                function() {
                    $location.path(backUrl);
                }
            );
    };

    $scope.back = function() {
        $location.path(backUrl);
    };
}]);