'use strict'

App.factory('RSSService', ['$http', '$q', 'APIConfig', function($http, $q, APIConfig) {

    return {
        fetchRSSSources: function() {
            return $http.get(APIConfig.API_END_POINT() + '/rest/widget/rss/list')
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
            return $http.get(APIConfig.API_END_POINT() + '/rest/widget/rss/' + source)
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
