'use strict'

App.controller('UserUpdateController', ['$scope', '$location', '$routeParams', 'UserService', function($scope, $location, $routeParams, UserService) {
    var self = this;
    var params = $routeParams;
    self.userToUpdate = {
        id: '',
        name: '',
        password: '',
        email: ''
    };

    this.fetchUserById = function(id) {
        UserService.fetchUserById(id)
            .then(
                function(data) {
                    $scope.user = data;
                }, function(errResponse) {
                    console.error('Error while fetching user');
                }
            );
    };

    this.fetchUserById(params.id);

    $scope.updateUser = function() {
        self.userToUpdate = $scope.user;
        UserService.updateUser(params.id, self.userToUpdate)
            .then(
                function() {
                    $location.path('/user/' + params.id);
                }
            );
    };

    $scope.back = function() {
        $location.path('/user/' + params.id);
    };
}]);