angular.module('app').controller('orderController', function ($scope, $http, $localStorage) {
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

    $scope.confirmOrder = function () {
        console.log($scope.order)
        $http.post(contextPath + '/api/v1/order/confirm', $scope.order)
            .then(function (response) {
                alert('Добавлен заказ');
           });
    };2


    $scope.orderContentRequest();
});