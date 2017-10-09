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
            checkoutThing: checkoutThing,
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

        function checkoutThing() {
            console.log("inside checkoutThing()");

        var checkoutThing1 = $resource('api/checkout', {}, {
            'query': {method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'save': { method:'POST' }
        });

            checkoutThing1.query();
            checkoutThing1.save();
//            checkoutThing1.get();

        }
    }
})();
