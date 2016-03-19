package com.tpokora.projects.admin.web;

import com.tpokora.projects.common.model.TableDetails;
import com.tpokora.projects.common.model.TableDetailsProvider;
import com.tpokora.projects.common.web.RESTResponseWrapper;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pokor on 19.03.2016.
 */
@RestController
@RequestMapping("/rest/admin/database")
public class DatabaseController {

    private static final Logger logger = Logger.getLogger(DatabaseController.class);

    private static RESTResponseWrapper restResponse = new RESTResponseWrapper();

    private List<TableDetails> tableDetailsList = new ArrayList<TableDetails>();


    @RequestMapping(value = "/table/list", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<RESTResponseWrapper> getTables() {
        restResponse.clearResponse();
        logger.info("Looking for tables...");

        tableDetailsList = TableDetailsProvider.getAllTablesDetails();
        restResponse.addContent("tables", tableDetailsList);
        return new ResponseEntity<RESTResponseWrapper>(restResponse, HttpStatus.OK);
    }


}
