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

        // This bit of code is no longer useful in our application, but I'll leave it with an explanation.
        // The LoginController.login() function uses the following method to broadcast the 'authenticationSuccess':
        // $rootScope.$broadcast('authenticationSuccess');
        // This triggers the code below.
//        $scope.$on('authenticationSuccess', function() {
//            getAccount();
//        });

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
