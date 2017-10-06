(function() {
    'use strict';

    angular
        .module('fauxshopApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('product2', {
            parent: 'product',
            url: '/product2',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/products/product2/product2.html',
                    controller: 'ProductController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                    $translatePartialLoader.addPart('product2');
                    return $translate.refresh();
                }],
                productToDisplay: ['ProductsService', function(ProductsService) {
                    return ProductsService.getProductsByProductsId(2).get();
                }]
            }
        });
    }
})();
