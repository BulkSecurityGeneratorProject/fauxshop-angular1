(function() {
    'use strict';

    angular
        .module('jhipangoneApp')
        .controller('MessageController', MessageController);

    MessageController.$inject = ['Message', 'MessageSearch'];

    function MessageController(Message, MessageSearch) {

        var vm = this;

        vm.messages = [];
        vm.clear = clear;
        vm.search = search;
        vm.loadAll = loadAll;

        loadAll();

        function loadAll() {
            Message.query(function(result) {
                vm.messages = result;
                vm.searchQuery = null;
            });
        }

        function search() {
            if (!vm.searchQuery) {
                return vm.loadAll();
            }
            MessageSearch.query({query: vm.searchQuery}, function(result) {
                vm.messages = result;
                vm.currentSearch = vm.searchQuery;
            });
        }

        function clear() {
            vm.searchQuery = null;
            loadAll();
        }    }
})();
