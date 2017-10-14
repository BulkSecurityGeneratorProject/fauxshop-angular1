(function() {
    'use strict';

    angular
        .module('fauxshopApp')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$scope', 'Principal', 'LoginService', 'CartService', '$state'];

    function HomeController ($scope, Principal, LoginService, CartService, $state) {
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
            Principal.identity().then(function(account) {
                vm.account = account;
                vm.isAuthenticated = Principal.isAuthenticated;
                if (vm.account != null){
                    getCartInvoices();
                }
            });
        }

        function register () {
            $state.go('register');
        }

        function getCartInvoices() {
            vm.cartInvoices = CartService.getCartByUserId(vm.account.id).get();
        }
    }
})();
