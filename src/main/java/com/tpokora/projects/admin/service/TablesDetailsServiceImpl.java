package com.tpokora.projects.admin.service;

import com.tpokora.projects.common.model.TableDetails;
import com.tpokora.projects.user.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pokor on 20.03.2016.
 */
public class TablesDetailsServiceImpl implements TablesDetailsService {

    @Autowired
    UserDAO userDAO;

    @Override
    public List<TableDetails> getAllTablesIDetails() {
        ArrayList<TableDetails> tableDetailsList = new ArrayList<TableDetails>();
        tableDetailsList.add(userDAO.getTableDetails());
        return tableDetailsList;
    }
}
