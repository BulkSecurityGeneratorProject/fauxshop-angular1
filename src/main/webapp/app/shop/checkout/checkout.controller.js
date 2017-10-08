(function() {
    'use strict';

    angular
        .module('fauxshopApp')
        .controller('CheckoutController', CheckoutController);

    CheckoutController.$inject = ['$stateParams', '$scope', 'CheckoutService', 'Auth', 'LoginService', 'CartService', 'ProductsService', 'User', 'Principal'];

    function CheckoutController ($stateParams, $scope, CheckoutService, Auth, LoginService, CartService, ProductsService, User, Principal) {
        var vm = this;

        vm.firstName = null;
        vm.lastName = null;
        vm.email = null;
        vm.address1 = null;
        vm.address2 = null;
        vm.deliveryStreetAddress = vm.address1 + " " + vm.address2;

        vm.tax = 20;
        vm.checkout = checkout;
        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;
        vm.cartInvoices = null;
        $scope.$on('authenticationSuccess', function() {
            getAccount();
        });

        getAccount();

        function checkout (event) {
            console.log("FUCK YEAH");
            CheckoutService.checkoutThing();
            console.log("WE MADE IT");
        }

        function getAccount() {
            Principal.identity().then(function(account) {
                vm.account = account;
                vm.isAuthenticated = Principal.isAuthenticated;
                if (vm.account != null){
                    getCartInvoices();
                }
            });
        }

        function getCartInvoices() {
            vm.cartInvoices = CartService.getCartByUserId(vm.account.id).get();
        }

        function register () {
            $state.go('register');
        }

        vm.total = function() {
            var total = 0;
            angular.forEach(vm.cartInvoices, function(item) {
                    total += item.cartItemQuantity * item.productsPrice;
            })
            return total;
        }

        vm.shipping = function() {
            var shiping = 0;

            if(vm.total() < 100) {
                shiping = 25;
            } else {
                shiping = 0;
            }
            return shiping;
        }
    }
})();
