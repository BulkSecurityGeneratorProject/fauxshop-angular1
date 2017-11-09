(function() {
    'use strict';

    angular
        .module('fauxshopApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('faq', {
            parent: 'app',
            url: '/faq',
            data: {
                authorities: [],
                pageTitle: 'faq.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/shop/faq/faq.html',
                    controller: 'FaqController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('activate');
                    return $translate.refresh();
                }]
            }
        });
    }
})();
