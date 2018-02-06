(function() {
    'use strict';

    angular
        .module('fauxshopApp')
        .factory('MessageSearch', MessageSearch);

    MessageSearch.$inject = ['$resource'];

    function MessageSearch($resource) {
        var resourceUrl =  'api/_search/messages/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
