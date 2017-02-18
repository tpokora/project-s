package com.tpokora.projects.home.web.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.tpokora.projects.common.web.rest.AbstractControllerTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Created by pokor on 05.03.2016.
 */
public class HomeControllerTest extends AbstractControllerTest {

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new HomeController()).build();
    }

    @Test
    public void getHome_ok() throws Exception {

        mockMvc.perform(get("/rest/home"))
                .andExpect(status().isOk())
                .andExpect(
                        content().contentType(MediaType.APPLICATION_JSON_UTF8)
                );
//                .andExpect(jsonPath("$.title").isString())
//                .andExpect(jsonPath("$.header").isString());
//                .andExpect(jsonPath("$.content").value(str));
    }
}
