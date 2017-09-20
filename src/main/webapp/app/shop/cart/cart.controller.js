(function() {
    'use strict';

    angular
        .module('fauxshopApp')
        .controller('CartController', CartController);

    CartController.$inject = ['$stateParams', 'Auth', 'LoginService'];

    function CartController ($stateParams, Auth, LoginService) {
        var vm = this;

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

	vm.removeItem = function(index) {
		vm.invoice.items.splice(index, 1);
	},

	vm.total = function() {
		var total = 0;
		angular.forEach(vm.invoice.items, function(item) {
				total += item.qty * item.cost;
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
