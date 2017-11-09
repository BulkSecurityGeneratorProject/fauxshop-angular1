(function() {
    'use strict';

    angular
        .module('fauxshopApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('privacy', {
            parent: 'app',
            url: '/privacy',
            data: {
                authorities: [],
                pageTitle: 'privacy.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/shop/privacy/privacy.html',
                    controller: 'PrivacyController',
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
