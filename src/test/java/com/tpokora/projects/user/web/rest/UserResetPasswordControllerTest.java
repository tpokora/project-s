package com.tpokora.projects.user.web.rest;

import com.tpokora.projects.common.errors.AbstractError;
import com.tpokora.projects.common.utils.SecurityUtilities;
import com.tpokora.projects.common.utils.TestUtils;
import com.tpokora.projects.common.web.rest.AbstractControllerTest;
import com.tpokora.projects.user.model.User;
import com.tpokora.projects.user.model.UserPassword;
import com.tpokora.projects.user.model.UserResetPassword;
import com.tpokora.projects.user.service.UserPasswordService;
import com.tpokora.projects.user.service.UserResetPasswordService;
import com.tpokora.projects.user.service.UserService;
import com.tpokora.projects.user.utils.UserTestUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;

import static org.mockito.Mockito.when;


/**
 * Created by pokor on 30.10.2016.
 */
public class UserResetPasswordControllerTest extends AbstractControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(UserResetPasswordControllerTest.class);

    private static final String WRONG_SESSION_ID = "4ab87e69";
    private static final String CORRECT_SESSION_ID = "4ab87e69-4b33-41c5-9bb2-7c731225f664";

    @Mock
    private AbstractError abstractError;

    @Mock
    private UserService userService;

    @Mock
    private UserResetPasswordService userResetPasswordService;

    @Mock
    private UserPasswordService userPasswordService;

    @InjectMocks
    private UserResetPasswordController userResetPasswordController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userResetPasswordController).dispatchOptions(true).build();
    }

    @Test
    @Rollback(true)
    public void getUserResetPasswordBySessionID_found() throws Exception {
        User user = new User(1, "test", "test", "test", "ROLE_USER");
        UserResetPassword userResetPassword = new UserResetPassword(CORRECT_SESSION_ID, "", "", new Date(), user);
        when(userService.getUserById(user.getId())).thenReturn(user);
        when(userResetPasswordService.findBySessionId(WRONG_SESSION_ID)).thenReturn(userResetPassword);
        mockMvc.perform(MockMvcRequestBuilders.get("/rest/user/reset/" + WRONG_SESSION_ID))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.user").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.user.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.user.username").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.user.email").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.user.role").exists());
    }

    @Test
    @Rollback(true)
    public void changeUserPasswordBySessionID_changed() throws Exception {
        User user = new User(1, "test", "test", "test", "ROLE_USER");
        UserResetPassword userResetPassword = new UserResetPassword(CORRECT_SESSION_ID, "", "", new Date(), user);
        when(userService.getUserById(user.getId())).thenReturn(user);
        when(userResetPasswordService.findBySessionId(CORRECT_SESSION_ID)).thenReturn(userResetPassword);
        mockMvc.perform(MockMvcRequestBuilders.get("/rest/user/reset/" + CORRECT_SESSION_ID))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.user").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.user.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.user.username").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.user.email").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.user.role").exists());


        User userToUpdate = new User(1, "test1", "test2", "test", "ROLE_USER");
        when(userService.createOrUpdateUser(userToUpdate)).thenReturn(userToUpdate);
        mockMvc.perform(MockMvcRequestBuilders.put("/rest/user/reset/" + CORRECT_SESSION_ID + "/changePassword")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(TestUtils.convertObjectToJsonBytes(userToUpdate)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").exists());
                    // TODO: fix test
//                .andExpect(MockMvcResultMatchers.jsonPath("$.content.user.id").exists())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.content.user.username").exists())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.content.user.username").value(userToUpdate.getUsername()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.content.user.email").exists())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.content.user.role").exists());
    }

    @Test
    @Rollback(true)
    public void generateNewSessionIDForUser_success() throws Exception {
        User user = UserTestUtils.generateUsers(1).get(0);
        when(userService.getUserById(user.getId())).thenReturn(user);
        UserPassword userPassword = new UserPassword(SecurityUtilities.hashingPassword(user.getPassword()));
        when(userPasswordService.getUserById(user.getId())).thenReturn(userPassword);
        UserResetPassword newUserResetPassword = new UserResetPassword(user);
        newUserResetPassword.setOldPassword(SecurityUtilities.hashingPassword(user.getPassword()));
        newUserResetPassword.setTempPassword(SecurityUtilities.hashingPassword(user.getPassword()));
        when(userResetPasswordService.createOrUpdateUserResetPassword(newUserResetPassword)).thenReturn(newUserResetPassword);
        mockMvc.perform(MockMvcRequestBuilders.post("/rest/user/reset/new")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(TestUtils.convertObjectToJsonBytes(user)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.userResetSession").exists());
    }
}

