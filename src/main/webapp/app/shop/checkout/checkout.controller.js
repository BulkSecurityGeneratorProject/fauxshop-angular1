(function() {
    'use strict';

    angular
        .module('fauxshopApp')
        .controller('CheckoutController', CheckoutController);

    CheckoutController.$inject = ['$stateParams', 'CheckoutService'];

    function CheckoutController ($stateParams, CheckoutService) {
        var vm = this;

        vm.checkout = checkout;

        function checkout (event) {
            console.log("FUCK YEAH");
            CheckoutService.checkoutThing();
            console.log("WE MADE IT");
        }
    }
})();
