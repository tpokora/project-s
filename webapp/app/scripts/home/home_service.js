'use strict'

App.factory('HomeService', ['$http', '$q', 'ConfigService', function($http, $q, ConfigService) {

    var baseUrl = '';
    ConfigService.baseUrl().then(function(data) {
        baseUrl = data;
    })

    return {
        home: function() {
            return $http.get(baseUrl + '/rest/home')
                .then(
                    function(response) {
                        return response.data;
                    }, function(errResponse) {
                        console.error('Error while fetching home page');
                        return $q.reject(errResponse);
                    }
                );
            }
        }
    }
]);