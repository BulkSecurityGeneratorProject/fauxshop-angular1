package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.FauxshopApp;
import com.mycompany.myapp.domain.Authority;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.repository.AuthorityRepository;
import com.mycompany.myapp.repository.UserRepository;
import com.mycompany.myapp.security.AuthoritiesConstants;
import com.mycompany.myapp.service.*;
import com.mycompany.myapp.service.dto.UserDTO;
import com.mycompany.myapp.web.rest.vm.KeyAndPasswordVM;
import com.mycompany.myapp.web.rest.vm.ManagedUserVM;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the AccountResource REST controller.
 *
 * @see CartResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FauxshopApp.class)
public class CartResourceIntTest {

    @Autowired
    private HttpMessageConverter[] httpMessageConverters;

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductsService productsService;

    @Autowired
    private ProductsDescriptionService productsDescriptionService;

    @Mock
    private MailService mockMailService;

    @Mock
    private CartService mockCartService;

    @Mock
    private ProductsService mockProductsService;

    @Mock
    private ProductsDescriptionService mockProductsDescriptionService;

    private MockMvc restUserMockMvc;

    private MockMvc restMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        doNothing().when(mockMailService).sendActivationEmail(anyObject());

        CartResource cartResource =
            new CartResource(cartService, productsService, productsDescriptionService);

        CartResource cartUserMockResource =
            new CartResource(cartService, productsService, productsDescriptionService);

        this.restMvc = MockMvcBuilders.standaloneSetup(cartResource)
            .setMessageConverters(httpMessageConverters)
            .build();
        this.restUserMockMvc = MockMvcBuilders.standaloneSetup(cartUserMockResource).build();
    }

    @Test
    @Transactional
    public void testGetCartById() throws Exception {
        restUserMockMvc.perform(get("/api/cart/5")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string("??"));
    }
}
