'use strict'

App.controller('UserDeleteController', ['$scope', '$location', '$routeParams', 'UserService', function($scope, $location, $routeParams, UserService) {
    var self = this;
    var params = $routeParams;
    self.user = [];


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

    self.user = this.fetchUserById(params.id);

    $scope.deleteUser = function() {
        UserService.deleteUserById($scope.user.id)
            .then(
                function() {
                    $location.path('/home');
                }
            );
    };

    $scope.back = function() {
        $location.path('/user/' + $scope.user.id);
    };

}]);