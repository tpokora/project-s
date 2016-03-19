package com.tpokora.projects.common.web;

import com.tpokora.projects.admin.web.DatabaseController;
import com.tpokora.projects.common.web.rest.AbstractControllerTest;
import com.tpokora.projects.user.dao.UserDAO;
import com.tpokora.projects.user.utils.UserTestUtils;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;

/**
 * Created by pokor on 19.03.2016.
 */
public class DatabaseControllerTest extends AbstractControllerTest {

    private static final Logger logger = Logger.getLogger(DatabaseControllerTest.class);

    @Mock
    UserDAO userDao;

    @InjectMocks
    DatabaseController databaseController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(databaseController).build();
    }

    @Test
    public void getAllTables_tablesExists() throws Exception {
        when(userDao.getTableDetails()).thenReturn(UserTestUtils.generateTableDetails());

        mockMvc.perform(MockMvcRequestBuilders.get("/rest/admin/database/table/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.tables").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.tables[0]").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.tables[0].name").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.tables[0].name").value("USERS"));
    }
}
