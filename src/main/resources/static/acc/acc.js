angular.module('app').controller('accController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/market';

    $scope.submitUpdateProfile = function() {
        $http.put(contextPath + '/api/v1/profiles', $scope.profile)
            .then(function (response) {
                $scope.loadProfile();
                alert('Profile updated!')
            });
    };

    $scope.loadProfile = function() {
        $http.get(contextPath + '/api/v1/profiles')
            .then(function(response) {
                $scope.profile = response.data;
                console.log($scope.profile);
            });
    };

    $scope.loadProfile();
});