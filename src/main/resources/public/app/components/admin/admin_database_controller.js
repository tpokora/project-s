'use strict'

App.controller('DatabaseController', ['$scope', 'DatabaseService', function($scope, DatabaseService) {
    var self = this;

    self.fetchAllTablesDetails = function() {
        DatabaseService.fetchAllTablesDetails()
            .then(
                function(tables) {
                    $scope.tables = tables;
                }, function(errResponse) {
                    console.error('Error while fetching table details');
                }
            );
    };

    self.fetchAllTablesDetails();
}])