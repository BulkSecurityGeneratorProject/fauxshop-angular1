(function() {
    'use strict';

    angular
        .module('fauxshopApp')
        .factory('CheckoutService', CheckoutService);

    CheckoutService.$inject = ['$http','$resource', '$state'];

    function CheckoutService ($http, $resource, $state) {
            console.log("INSIDE CheckoutService");

        var formData = [];

        var service = {
            createOrder: createOrder,
            goToCheckout2: goToCheckout2,
            loadFormVariables: loadFormVariables
        };

        return service;


        function goToCheckout2($scope) {
            formData.firstName = $scope.firstName;
            formData.lastName = $scope.lastName;
            formData.email = $scope.email
            formData.address1 = $scope.address1;
            formData.address2 = $scope.address2;
            formData.city = $scope.city;
            formData.postcode = $scope.postcode;
            formData.phone = $scope.phone;
            $state.go('checkout2');
        }

        function loadFormVariables(){
            return formData;
        }

        function createOrder(orderDTO) {
            $http.post('api/checkout', orderDTO).
            success(function(data, status, headers, config) {
                console.log(data);
                }).
              error(function(data, status, headers, config) {
                });

            return createOrder;
        }
    }
})();
