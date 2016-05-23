'use strict'

App.factory('HomeService', ['$http', '$q', 'APIConfig', function($http, $q, APIConfig) {

    return {
        home: function() {
            return $http.get(APIConfig.API_END_POINT() + '/rest/home')
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
