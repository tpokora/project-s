package com.tpokora.projects.user.web.rest;

import com.tpokora.projects.articles.model.Article;
import com.tpokora.projects.articles.utils.ArticleTestUtils;
import com.tpokora.projects.common.errors.AbstractError;
import com.tpokora.projects.common.utils.TestUtils;
import com.tpokora.projects.common.web.rest.AbstractControllerTest;
import com.tpokora.projects.user.model.User;
import com.tpokora.projects.user.service.UserService;
import com.tpokora.projects.user.utils.UserTestUtils;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
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

    @Mock
    AbstractError userError;

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void getAllUsers_usersExists() throws Exception {
        when(userService.getAllUsers()).thenReturn(UserTestUtils.generateUsers(3));
        mockMvc.perform(MockMvcRequestBuilders.get("/rest/user/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(
                        MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.users").isArray());
    }

    // TODO: Fix user not exists test
    @Ignore
    @Test
    public void getAllUsers_usersNotExists() throws Exception {
        when(userService.getAllUsers()).thenReturn(UserTestUtils.generateUsers(0));
        mockMvc.perform(MockMvcRequestBuilders.get("/rest/user/list"))
                .andExpect(MockMvcResultMatchers.status().is(200));
//                .andExpect(
//                        MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)
//                );
    }

    @Test
    public void getUserById_1_userExists() throws Exception {
        when(userService.getUserById(1)).thenReturn(UserTestUtils.generateUsers(1).get(0));
        mockMvc.perform(MockMvcRequestBuilders.get("/rest/user/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(
                        MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.users[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.users[0].username").value("user1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.users[0].email").value("email@email.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.users[0].role").value("ROLE_USER"));
    }

    @Test
    public void getUserByUsername_user1_userExists() throws Exception {
        when(userService.getUserById(1)).thenReturn(UserTestUtils.generateUsers(1).get(0));
        mockMvc.perform(MockMvcRequestBuilders.get("/rest/user/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(
                        MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.users[0].username").value("user1"));
    }


    // TODO: fix test to get 404 and error in response
    @Ignore
    @Test
    public void getUserById_2_userNotExists() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/rest/user/2"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(
                        MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors").isArray());
    }

    @Test
    @Rollback(true)
    public void createNewUser_success() throws Exception {
        User newUser = UserTestUtils.generateUsers(1).get(0);
        mockMvc.perform(MockMvcRequestBuilders.post("/rest/user/new")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtils.convertObjectToJsonBytes(newUser)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.users").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.users[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.users[0].username").value(newUser.getUsername()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.users[0].email").value(newUser.getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.users[0].role").value("ROLE_USER"));
    }

    // TODO: Error in test No value at JSON path "$.content.users[0].username", exception: Property ['id'] not found in path $['content']['users'][0]
    @Ignore
    @Test
    @Rollback(true)
    public void updateUser_success() throws Exception {
        User userToUpdate = UserTestUtils.generateUsers(1).get(0);
        when(userService.createOrUpdateUser(userToUpdate)).thenReturn(userToUpdate);
        userToUpdate.setId(1);
        userToUpdate.setUsername("UPDATED_USERNAME");
        userToUpdate.setPassword("UPDATED_PASSWORD");
        userToUpdate.setEmail("UPDATED_EMAIL");
        userToUpdate.setRole("UPDATED_ROLE");
        mockMvc.perform(MockMvcRequestBuilders.put("/rest/user/1/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.convertObjectToJsonBytes(userToUpdate)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.users").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.users[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.users[0].username").value(userToUpdate.getUsername()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.users[0].password").value(userToUpdate.getPassword()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.users[0].email").value(userToUpdate.getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.users[0].role").value("UPDATED_ROLE"));
    }

    @Test
    @Rollback(true)
    public void removeUser_success() throws Exception {
        mockMvc.perform((MockMvcRequestBuilders.delete("/rest/user/1/delete")))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Test to get user with article
     * @throws Exception
     *
     * TODO unignore when articles presentation fix
     */
    @Ignore
    @Test
    @Rollback(true)
    public void getUserById_1_userWithArticle() throws Exception {
        User user = UserTestUtils.generateUsers(1).get(0);
        user.setArticles((List<Article>) ArticleTestUtils.generateArticles(1, user.getId()));
        when(userService.getUserById(user.getId())).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.get("/rest/user/" + user.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.users[0].id").value(user.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.users[0].articles").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.users[0].articles[0].id").value(user.getArticles().get(0).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.users[0].articles[0].title").value(user.getArticles().get(0).getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.users[0].articles[0].content").value(user.getArticles().get(0).getContent()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.users[0].articles[0].createTime").value(user.getArticles().get(0).getCreateTime().getTime()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.users[0].articles[0].user.id").value(user.getId()));
    }

}
