'use strict'

App.factory('ConfigService', ['$http', '$q', function($http, $q) {
    var deferred = $q.defer();

    return {
        baseUrl: function() {
            $http.get('../../properties/properties_dev.json').then(function(json) {
                deferred.resolve(json.data.API.url + ":" + json.data.API.port);
            })
            return deferred.promise;
        }
    }
}]);
