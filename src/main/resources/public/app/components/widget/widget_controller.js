'use strict'

App.controller('WidgetController', ['$scope', '$q', '$stateParams', '$location', 'RSSService', function($scope, $q, $stateParams, $location, RSSService) {
    var self = this;
    var params = $stateParams;

    this.fetchRSSBySource = function(source) {
        RSSService.fetchRSSBySource(source)
            .then(
                function(rss) {
                    $scope.rss = rss;
                },
                function(errResponse) {
                    console.error('Error while fetching RSS');
                }
            );
    };

    if (params.source != null) {
        this.fetchRSSBySource(params.source);
    }
}]);