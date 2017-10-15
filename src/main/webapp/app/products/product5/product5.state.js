(function() {
    'use strict';

    angular
        .module('fauxshopApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('product5', {
            parent: 'product',
            url: '/product5',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/products/product5/product5.html',
                    controller: 'ProductController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                    $translatePartialLoader.addPart('product5');
                    return $translate.refresh();
                }],
                productToDisplay: ['ProductsService', function(ProductsService) {
                    return ProductsService.getProductsByProductsId(5).get();
                }]
            }
        });
    }
})();
