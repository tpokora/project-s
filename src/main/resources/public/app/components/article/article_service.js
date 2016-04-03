'use strict'

App.factory('ArticleService', ['$http', '$q', 'ConfigService', function($http, $q, ConfigService) {

    var baseUrl = '';
    ConfigService.baseUrl().then(function(data) {
        baseUrl = data;
    });

    return {
        fetchAllArticles: function() {
            return $http.get(baseUrl + '/rest/article/list')
                .then(function(response) {
                    return response.data.content.articles;
                }, function(errResponse) {
                    console.error('Error while fetching articles');
                    return $q.reject(errResponse);
                });
        }
    }
}]);