'use strict';

describe('Service Tests', function () {
    beforeEach(mockApiCartGet);
    beforeEach(mockApiAccountCall);
    beforeEach(mockI18nCalls);
    beforeEach(mockScriptsCalls);

    describe('CartService', function () {
        var $httpBackend, $http, $resource, cartService;

        beforeEach(inject(function($injector, $http, $resource, CartService) {
            $httpBackend = $injector.get('$httpBackend');
            cartService = CartService;
        }));

        //make sure no expectations were missed in your tests.
        //(e.g. expectGET or expectPOST)
        afterEach(function() {
            $httpBackend.verifyNoOutstandingExpectation();
            $httpBackend.verifyNoOutstandingRequest();
        });

        it('should call backend on get api/cart/{id}', function(){
            //GIVEN

            //WHEN
            var apiCartGetResult = cartService.getCartByUserId(1).get();
            //flush the backend to "execute" the request to do the expectedGET assertion.
            $httpBackend.flush();

            //THEN
            expect(apiCartGetResult[0]).toBe("apiCartGetArrayResult");
        });
    });
});
