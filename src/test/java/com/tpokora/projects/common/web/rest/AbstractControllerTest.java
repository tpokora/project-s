package com.tpokora.projects.common.web.rest;

import com.tpokora.projects.common.web.RESTResponseWrapper;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Created by pokor on 05.03.2016.
 */
public abstract class AbstractControllerTest {

    protected MockMvc mockMvc;

    @Autowired
    @Mock
    protected RESTResponseWrapper restResponse;
}
