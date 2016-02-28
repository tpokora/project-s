'use strict'

App.controller('UsersController', ['$scope', '$routeParams', '$location', 'UserService', function($scope, $routeParams, $location, UserService) {
    var self = this;
    var params = $routeParams;

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

    this.fetchAllUsers();

    // Add user based on ID param
    if (params.id != null) {
        this.fetchUserById(params.id);
    }
}]);