package com.tpokora.projects.user.web.rest;

import com.tpokora.projects.common.errors.AbstractError;
import com.tpokora.projects.common.utils.TestUtils;
import com.tpokora.projects.common.web.rest.AbstractControllerTest;
import com.tpokora.projects.user.dao.UserDAO;
import com.tpokora.projects.user.model.User;
import com.tpokora.projects.user.service.UserService;
import com.tpokora.projects.user.service.UserServiceImpl;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Created by pokor on 05.03.2016.
 */
public class UserControllerTest extends AbstractControllerTest {

    private static final Logger logger = Logger.getLogger(UserControllerTest.class);

    @Autowired
    @Mock
    UserDAO userDao;

    @Autowired
    @Mock
    UserService userService;

    @Autowired
    @Mock
    AbstractError userError;

    @InjectMocks
    UserController userController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void getAllUsers_usersExists() throws Exception {
        when(userService.getAllUsers()).thenReturn(TestUtils.generateUsers(3));

        mockMvc.perform(MockMvcRequestBuilders.get("/rest/user/list"))
                .andExpect(
                        MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.users").exists());
    }

    @Test
    public void getAllUsers_usersNotExists() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/rest/user/list"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }
}
