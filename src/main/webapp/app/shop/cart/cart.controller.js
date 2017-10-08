(function() {
    'use strict';

    angular
        .module('fauxshopApp')
        .controller('CartController', CartController);

    CartController.$inject = ['$stateParams', '$state', '$scope', 'Auth', 'LoginService', 'CartService', 'ProductsService', 'User', 'Principal'];

    function CartController ($stateParams, $state, $scope, Auth, LoginService, CartService, ProductsService, User, Principal) {
        var vm = this;

        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;
        vm.removeFromCart = removeFromCart;
        vm.cartInvoices = null;
        $scope.$on('authenticationSuccess', function() {
            getAccount();
        });

        getAccount();

    vm.tax = 20;
	vm.invoice = {
		items: [{
				qty: 1,
				title: 'Tuscan urns',
				cost: 14,
				url: 'product1'},
				{qty: 1,
				title: 'Bosphorus bowls',
				cost: 28,
				url: 'product2'},
				{qty: 2,
				title: 'Langdon vases ',
				cost: 24,
				url: 'product3'}
				]
	};

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

    function getProductData(productId) {
        vm.productData = ProductsService.getProductsByProductsId(productId).get();
    }

    function removeFromCart(index, cartId) {
        CartService.removeFromCart(cartId).post();
        vm.cartInvoices.splice(index, 1);
    }

	vm.removeItem = function(index) {
		vm.invoice.items.splice(index, 1);
	},

	vm.total = function() {
		var total = 0;
		angular.forEach(vm.cartInvoices, function(item) {
				total += item.cartItemQuantity * item.productsPrice;
		})
		return total;
	}

	vm.shiping = function() {
		var shiping = 0;

		if(vm.total() < 100) {
			shiping = 25;
		} else {
			shiping = 0;
		}
		return shiping;
	}

        Auth.activateAccount({key: $stateParams.key}).then(function () {
            vm.error = null;
            vm.success = 'OK';
        }).catch(function () {
            vm.success = null;
            vm.error = 'ERROR';
        });

        vm.login = LoginService.open;
    }
})();
