angular.module('app').controller('accController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/market';

    $scope.editConfirm = function () {
        $http.post(contextPath + '/api/v1/user/edit', $scope.user)
            .then(function successCallback(response) {
                 log(response.data);
            }, function errorCallback(response) {
                window.alert(response.data.message);
                $scope.clearUser();
            });
    };

    $scope.getCurrentUser = function () {
        $http({
            url: contextPath + '/api/v1/user/edit',
            method: 'GET'
        })
            .then(function (response) {
                $scope.currentUser = response.data;
            });
    };

    $scope.getCurrentUser();
});