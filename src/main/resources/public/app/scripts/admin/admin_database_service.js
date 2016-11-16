'use strict'

App.factory('DatabaseService', ['$http', '$q', 'APIConfig', function($http, $q, APIConfig) {

    return {
        fetchAllTablesDetails: function() {
            return $http.get(APIConfig.API_END_POINT() + '/rest/admin/database/table/list')
                .then(
                    function(response) {
                        return response.data.content.tables;
                    },
                    function(errResponse) {
                        console.error('Error while fetching tables details');
                        return $q.reject(errResponse);
                    }
                )
        }
    }
}])
