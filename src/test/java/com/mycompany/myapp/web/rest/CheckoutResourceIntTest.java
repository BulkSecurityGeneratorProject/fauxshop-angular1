package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.FauxshopApp;
import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.*;
import com.mycompany.myapp.service.dto.OrderDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the CartResource REST controller.
 *
 * @see CartResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FauxshopApp.class)
public class CheckoutResourceIntTest {

    @Autowired
    private HttpMessageConverter[] httpMessageConverters;

    @Autowired
    private CartService cartService;

    @Autowired
    private CheckoutService checkoutService;

    @Mock
    private MailService mockMailService;

    @Mock
    private CheckoutService mockCheckoutService;

    @Mock
    private CartService mockCartService;

    private MockMvc restCheckoutMockMvc;

    private MockMvc restMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        doNothing().when(mockMailService).sendActivationEmail(anyObject());

        CheckoutResource checkoutResource =
            new CheckoutResource(checkoutService, cartService);

        CheckoutResource checkoutMockResource =
            new CheckoutResource(mockCheckoutService, mockCartService);

        this.restMvc = MockMvcBuilders.standaloneSetup(checkoutResource)
            .setMessageConverters(httpMessageConverters)
            .build();
        this.restCheckoutMockMvc = MockMvcBuilders.standaloneSetup(checkoutMockResource).build();
    }

    @Test
    @Transactional
    public void testCreateOrdersRecord() throws Exception {
        List<Cart> cartList = new ArrayList<>();
        Cart cart = new Cart();
        cart.setCartId(1L);
        cart.setId(2L);
        cart.setProductsId(3L);
        cart.setCartItemQuantity(10);
        cart.setCartItemTotalPrice(BigDecimal.TEN);
        cartList.add(cart);

        Orders orderRecordToPersist =  new Orders();
        orderRecordToPersist.setOrderStatus("initiated");

        when(mockCheckoutService.save(orderRecordToPersist)).thenReturn(orderRecordToPersist);

        restCheckoutMockMvc.perform(post("/api/createOrdersRecord")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cartList)))
            .andExpect(status().isCreated());
    }

    @Test
    @Transactional
    public void testCheckout() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(1L);

        Orders order = new Orders();
        order.setOrderId(1L);
        Optional<Orders> optionalOrder = Optional.of(order);

        when(mockCheckoutService.getOrdersByOrdersId(1L)).thenReturn(optionalOrder);

        restCheckoutMockMvc.perform(post("/api/checkout")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderDTO)))
            .andExpect(status().isCreated());
    }

    @Test
    @Transactional
    public void testUpdateChargeId() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(1L);

        Orders order = new Orders();
        order.setOrderId(1L);
        Optional<Orders> optionalOrder = Optional.of(order);

        when(mockCheckoutService.getOrdersByOrdersId(1L)).thenReturn(optionalOrder);

        restCheckoutMockMvc.perform(post("/api/updateChargeId")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderDTO)))
            .andExpect(status().isCreated());
    }


}
