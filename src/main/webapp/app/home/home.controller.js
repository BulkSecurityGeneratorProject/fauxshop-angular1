(function() {
    'use strict';

    angular
        .module('fauxshopApp')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$window', '$scope', '$state', 'Principal', 'LoginService', 'CartService'];

    function HomeController ($window, $scope, $state, Principal, LoginService, CartService) {
        var vm = this;

        vm.account = null;
        vm.cartInvoices = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;
        $scope.$on('authenticationSuccess', function() {
            getAccount();
        });

        getAccount();

        function getAccount() {
            console.log('$window.localStorage.guestId: ' + $window.localStorage.guestId);
            Principal.identity().then(function(account) {
                vm.account = account;
                vm.isAuthenticated = Principal.isAuthenticated;
                if (vm.account != null){
                    getCartInvoices();
                } else {
                    getGuestCartInvoices();
                }
            });
        }

        function getGuestCartInvoices() {
            vm.cartInvoices = CartService.getCartByUserId($window.localStorage.guestId).get();
        }

        function register () {
            $state.go('register');
        }

        function getCartInvoices() {
            vm.cartInvoices = CartService.getCartByUserId(vm.account.id).get();
        }
    }
})();
