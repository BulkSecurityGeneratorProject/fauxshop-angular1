(function() {
    'use strict';

    angular
        .module('fauxshopApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('product1', {
            parent: 'product',
            url: '/product1',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/products/product1/product1.html',
                    controller: 'ProductController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                    $translatePartialLoader.addPart('product1');
                    return $translate.refresh();
                }],
                productToDisplay: ['ProductsService', function(ProductsService) {
                    return ProductsService.getProductsByProductsId(1).get();
                }]
            }
        });
    }
})();
