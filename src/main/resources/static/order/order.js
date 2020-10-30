angular.module('app').controller('orderController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';

    $scope.orderContentRequest = function () {
        $http({
            url: contextPath + '/api/v1/order/test',
            method: 'GET'
        })
            .then(function (response) {
                console.log(response.data);
                $scope.order = response.data;
            });
    };

    $scope.orderContentRequest();
});