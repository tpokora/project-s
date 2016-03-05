package com.tpokora.projects.home.web.rest;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Created by pokor on 05.03.2016.
 */
public class HomeControllerTest {

    private final MockMvc mockMvc = standaloneSetup(new HomeController()).build();

    @Test
    public void validate_home() throws Exception {
        String str = "Template Spring Application";

        mockMvc.perform(get("/rest/home"))
                .andExpect(status().isOk())
                .andExpect(
                        content().contentType(MediaType.APPLICATION_JSON_UTF8)
                )
                .andExpect(jsonPath("$.title").value(str))
                .andExpect(jsonPath("$.header").value(str))
                .andExpect(jsonPath("$.content").value(str));
    }
}
