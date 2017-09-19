(function() {
    'use strict';

    angular
        .module('fauxshopApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('product3', {
            parent: 'product',
            url: '/product3',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/products/product3/product3.html',
                    controller: 'ProductController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                    $translatePartialLoader.addPart('product3');
                    return $translate.refresh();
                }]
            }
        });
    }
})();
