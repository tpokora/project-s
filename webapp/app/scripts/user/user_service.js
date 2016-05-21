'use strict'

App.factory('UserService', ['$http', '$q', 'ConfigService', function($http, $q, ConfigService) {
    
    var baseUrl = '';
    ConfigService.baseUrl().then(function(data) {
        baseUrl = data;
    });

    return {
        fetchAllUsers: function() {
            return $http.get(baseUrl + '/rest/user/list')
                .then(
                    function(response) {
                        return response.data.content.users;
                    },
                    function(errResponse) {
                        console.error('Error while fetching Users');
                        return $q.reject(errResponse);
                    }
                )
        },

        fetchUserById: function(id) {
            return $http.get(baseUrl + '/rest/user/' + id)
                .then(
                    function(response) {
                        response.data.content.users[0].password = '';
                        return response.data.content.users[0];
                    },
                    function(errResponse) {
                        console.error('Error while fetching User');
                        return $q.reject(errResponse);
                    }
                )
        },

        fetchUserByUsername: function(username) {
                    return $http.get(baseUrl + '/rest/user/name/' + username)
                        .then(
                            function(response) {
                                return response.data.content.users[0];
                            },
                            function(errResponse) {
                                console.error('Error while fetching User');
                                return $q.reject(errResponse);
                            }
                        )
                },

        createUser: function(user) {
            return $http.post(baseUrl + '/rest/user/new', user)
                .then(
                    function(response) {
                        return response.data;
                    }, function(errResponse) {
                        console.error('Error while creating new User');
                        return $q.reject(errResponse);
                    }
                )
        },

        updateUser: function(id, user) {
            return $http.put(baseUrl + '/rest/user/' + id + '/update', user)
                .then(
                    function(response) {
                        return response.data.content.users;
                    },
                    function(errResponse) {
                        console.error('Error while updating user');
                        return $q.reject(errResponse);
                    }
                )
        },

        deleteUserById: function(id) {
            return $http.delete(baseUrl + '/rest/user/' + id + '/delete')
                .then(
                    function(response) {
                        return response.data;
                    },
                    function(errResponse) {
                        console.error('Error while deleting User');
                        return $q.reject(errResponse);
                    }
                )
        }
    }
}]);