package com.tpokora.projects.admin.web;

import com.tpokora.projects.admin.service.TablesDetailsService;
import com.tpokora.projects.admin.utils.DatabaseTestUtils;
import com.tpokora.projects.common.web.rest.AbstractControllerTest;
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

import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Created by pokor on 19.03.2016.
 */
public class DatabaseControllerTest extends AbstractControllerTest {

    private static final Logger logger = Logger.getLogger(DatabaseControllerTest.class);

    @Mock
    TablesDetailsService tablesDetailsService;

    @InjectMocks
    DatabaseController databaseController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(databaseController).build();
    }

    @Test
    public void getAllTables_tablesExists() throws Exception {
        when(tablesDetailsService.getAllTablesIDetails()).thenReturn(DatabaseTestUtils.generateTableDetailsList(3));

        mockMvc.perform(MockMvcRequestBuilders.get("/rest/admin/database/table/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.tables").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.tables").isArray());
    }

    @Test
    public void checkTableColumns_columnFields() throws Exception {
        when(tablesDetailsService.getAllTablesIDetails()).thenReturn(DatabaseTestUtils.generateTableDetailsList(1));

        mockMvc.perform(MockMvcRequestBuilders.get("/rest/admin/database/table/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.tables").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.tables").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.tables[0]").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.tables[0].columns").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.tables[0].columns[0].name").value("username"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.tables[0].columns[0].type").value("string"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.tables[0].columns[1].name").value("password"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.tables[0].columns[1].type").value("string"));
    }
}
