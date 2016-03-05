package com.tpokora.projects.common.utils;

import com.tpokora.projects.common.web.RESTResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by pokor on 05.03.2016.
 */
public class TestUtils {

    protected static RESTResponseWrapper restResponse = new RESTResponseWrapper();

    public static ResponseEntity<RESTResponseWrapper> generateResponseWrapper() {
        return new ResponseEntity<RESTResponseWrapper>(restResponse, HttpStatus.OK);
    }

}
