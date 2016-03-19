package com.tpokora.projects.common.web;

import com.tpokora.projects.admin.web.DatabaseController;
import com.tpokora.projects.common.model.TableDetailsProvider;
import com.tpokora.projects.common.web.rest.AbstractControllerTest;
import com.tpokora.projects.user.utils.UserTestUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.dialect.function.SQLFunction;
import org.hibernate.dialect.function.SQLFunctionRegistry;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.withSettings;

/**
 * Created by pokor on 19.03.2016.
 */
public class DatabaseControllerTest extends AbstractControllerTest {

    private static final Logger logger = Logger.getLogger(DatabaseControllerTest.class);

    @Autowired
    SessionFactory sessionFactory;

    SessionFactoryImplementor sessionFactoryImplementor;

    @Mock
    Session session;

    @Mock
    TableDetailsProvider tableDetailsProvider;

    @InjectMocks
    DatabaseController databaseController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        sessionFactory = mock(SessionFactory.class, withSettings().extraInterfaces(SessionFactoryImplementor.class));
        sessionFactoryImplementor = (SessionFactoryImplementor) sessionFactory;
        when(sessionFactory.openSession()).thenReturn(session);
        when(sessionFactoryImplementor.getSqlFunctionRegistry())
                .thenReturn(new SQLFunctionRegistry(new MySQL5Dialect(), new HashMap<String, SQLFunction>()));
        this.mockMvc = MockMvcBuilders.standaloneSetup(databaseController).build();
    }

    // TODO: Fix mocking test
    @Ignore
    @Test
    public void getAllTables_tablesExists() throws Exception {
        when(TableDetailsProvider.getAllTablesDetails()).thenReturn(UserTestUtils.generateTableDetailsList(3));

        mockMvc.perform(MockMvcRequestBuilders.get("/rest/admin/database/table/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.tables").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.tables").isArray())
                .andReturn().getResponse().getContentAsString();
    }
}
