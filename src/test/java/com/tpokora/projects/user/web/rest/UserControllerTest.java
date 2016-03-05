package com.tpokora.projects.user.web.rest;

import com.tpokora.projects.common.errors.AbstractError;
import com.tpokora.projects.common.utils.TestUtils;
import com.tpokora.projects.common.web.rest.AbstractControllerTest;
import com.tpokora.projects.user.dao.UserDAO;
import com.tpokora.projects.user.service.UserService;
import com.tpokora.projects.user.utils.UserTestUtils;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.exceptions.ExceptionIncludingMockitoWarnings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;

/**
 * Created by pokor on 05.03.2016.
 */
public class UserControllerTest extends AbstractControllerTest {

    private static final Logger logger = Logger.getLogger(UserControllerTest.class);

    @Mock
    UserController userController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void getAllUsers_usersExists() throws Exception {
        when(userController.getAllUsers()).thenReturn(UserTestUtils.generateResponseWithUsersList(3));
        mockMvc.perform(MockMvcRequestBuilders.get("/rest/user/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(
                        MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.users").exists());
    }

    @Test
    public void getUserById_1_userExists() throws Exception {
        when(userController.getUserById(1)).thenReturn(UserTestUtils.generateResponseWithUsersList(1));
        mockMvc.perform(MockMvcRequestBuilders.get("/rest/user/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(
                        MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.users[0].id").value(1));
    }

    @Test
    public void getUserByUsername_user1_userExists() throws Exception {
        when(userController.getUserById(1)).thenReturn(UserTestUtils.generateResponseWithUsersList(1));
        mockMvc.perform(MockMvcRequestBuilders.get("/rest/user/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(
                        MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.users[0].username").value("user1"));
    }

//    @Test
//    public void getAllUsers_userNotExists() throws Exception {
//        when(userController.getAllUsers()).thenReturn(UserTestUtils.generateResponseEmptyUsersList());
//        mockMvc.perform(MockMvcRequestBuilders.get("/rest/user/1"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.content").isEmpty())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.errors").exists());
//    }
}
