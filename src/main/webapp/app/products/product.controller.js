(function() {
    'use strict';

    angular
        .module('fauxshopApp')
        .controller('Product1Controller', ProductController);

    ProductController.$inject = ['$scope', 'Principal', '$state'];

    function ProductController ($scope, Principal, $state) {
        var vm = this;

        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;
        $scope.$on('authenticationSuccess', function() {
            getAccount();
        });
    }
})();
