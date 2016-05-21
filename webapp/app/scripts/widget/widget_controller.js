'use strict'

App.controller('WidgetController', ['$scope', '$q', '$stateParams', '$location', 'RSSService', function($scope, $q, $stateParams, $location, RSSService) {
    var self = this;
    var params = $stateParams;

    $scope.fetchRSSSources = function() {
        RSSService.fetchRSSSources()
            .then(
                function(rssSources) {
                    $scope.rssSources = rssSources;
                }, function(errResponse) {
                    console.error('Error while fetching RSS Sources');
                }
            );
    }

    $scope.fetchRSSSources();

    $scope.fetchRSSBySource = function(source) {
        if (source != "" && source != null) {
        RSSService.fetchRSSBySource(source)
            .then(
                function(rss) {
                    $scope.rss = rss;
                },
                function(errResponse) {
                    console.error('Error while fetching RSS');
                }
            );
        }
    };
}]);