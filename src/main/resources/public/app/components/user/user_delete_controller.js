'use strict'

App.controller('UserDeleteController', ['$scope', '$location', '$stateParams', 'UserService', function($scope, $location, $stateParams, UserService) {
    var self = this;
    var params = $stateParams;

    var backUrl = "/users";

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

    self.setBackUrl = function() {
        if ($location.path().indexOf("admin") > -1) {
            backUrl = "/admin/users";
        }
    }

    self.user = this.fetchUserById(params.id);
    self.setBackUrl();

    $scope.deleteUser = function() {
        UserService.deleteUserById($scope.user.id)
            .then(
                function() {
                    $location.path('/home');
                }
            );
    };

    $scope.back = function() {
        $location.path(backUrl);
    };

}]);