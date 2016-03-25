package com.tpokora.projects.admin.utils;

import com.tpokora.projects.common.model.TableDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pokor on 21.03.2016.
 */
public class DatabaseTestUtils {

    public static List<TableDetails> generateTableDetailsList(int amount) {
        ArrayList<TableDetails> list = new ArrayList<TableDetails>();

        for(int i = 0; i < amount; i++) {
            list.add(generateTableDetails(i));
        }

        return list;
    }

    public static TableDetails generateTableDetails(int index) {
        TableDetails tableDetails = new TableDetails();
        tableDetails.setName("users");
        tableDetails.addColumn("username", "string");
        tableDetails.addColumn("password", "string");
        return tableDetails;
    }
}
