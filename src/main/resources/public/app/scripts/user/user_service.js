'use strict'

App.factory('UserService', ['$http', '$q', 'APIConfig', function($http, $q, APIConfig) {

    return {
        fetchAllUsers: function() {
            return $http.get(APIConfig.API_END_POINT() + '/rest/user/list')
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
            return $http.get(APIConfig.API_END_POINT() + '/rest/user/' + id)
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
                    return $http.get(APIConfig.API_END_POINT() + '/rest/user/name/' + username)
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

        fetchUserByEmail: function(email) {
            return $http.get(APIConfig.API_END_POINT() + '/rest/user/email?value=' + email)
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
            return $http.post(APIConfig.API_END_POINT() + '/rest/user/new', user)
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
            return $http.put(APIConfig.API_END_POINT() + '/rest/user/' + id + '/update', user)
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
            return $http.delete(APIConfig.API_END_POINT() + '/rest/user/' + id + '/delete')
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
