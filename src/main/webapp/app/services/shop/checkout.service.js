(function() {
    'use strict';

    angular
        .module('fauxshopApp')
        .factory('CheckoutService', CheckoutService);

    CheckoutService.$inject = ['$http','$resource', '$state'];

    function CheckoutService ($http, $resource, $state) {
            console.log("INSIDE CheckoutService");

        var checkoutData = [];

        var service = {
            createOrder: createOrder,
            goToCheckout2: goToCheckout2,
            loadCheckoutData: loadCheckoutData,
            createOrdersRecord: createOrdersRecord,
            updateChargeId: updateChargeId
        };

        return service;


        function goToCheckout2($scope) {
            checkoutData.firstName = $scope.firstName;
            checkoutData.lastName = $scope.lastName;
            checkoutData.email = $scope.email
            checkoutData.address1 = $scope.address1;
            checkoutData.address2 = $scope.address2;
            checkoutData.city = $scope.city;
            checkoutData.postcode = $scope.postcode;
            checkoutData.phone = $scope.phone;
            $state.go('checkout2');
        }

        function loadCheckoutData(){
            console.log(checkoutData);
            return checkoutData;
        }

        function createOrdersRecord(cartInvoices) {
            return $http.post('api/createOrdersRecord', cartInvoices).
            success(function(data, status, headers, config) {
                checkoutData.createdOrdersRecordId = data.orderId;
                }).
              error(function(data, status, headers, config) {
                });
        }

        function createOrder(orderDTO) {
            return $http.post('api/checkout', orderDTO).
            success(function(data, status, headers, config) {
                console.log(data);
                }).
              error(function(data, status, headers, config) {
                });
        }

        function updateChargeId(orderDTO) {
            $http.post('api/updateChargeId', orderDTO).
            success(function(data, status, headers, config) {
                console.log(data);
                }).
              error(function(data, status, headers, config) {
                });

            return updateChargeId;
        }

    }
})();
