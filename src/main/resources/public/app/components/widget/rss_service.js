'use strict'

App.factory('RSSService', ['$http', '$q', 'ConfigService', function($http, $q, ConfigService) {

    var baseUrl = '';
    ConfigService.baseUrl().then(function(data) {
        baseUrl = data;
    });

    return {
        fetchRSSSources: function() {
            return $http.get(baseUrl + '/rest/widget/rss/list')
                .then(
                    function(response) {
                        return response.data.content.RSSSources;
                    }, function(errResponse) {
                        console.error('Error while fetching RSS sources');
                        return $q.reject(errResponse);
                    }
                )
        },
        fetchRSSBySource: function(source) {
            return $http.get(baseUrl + '/rest/widget/rss/' + source)
                .then(
                    function(response) {
                        return response.data.content.RSS;
                    },
                    function(errResponse) {
                        console.error('Error while fetching RSS: ' + source);
                        return $q.reject(errResponse);
                    }
                )
        }
    }

}]);