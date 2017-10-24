(function() {
    'use strict';

    angular
        .module('fauxshopApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('contact', {
            parent: 'app',
            url: '/contact',
            data: {
                authorities: [],
                pageTitle: 'contact.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/shop/contact/contact.html',
                    controller: 'ContactController',
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
