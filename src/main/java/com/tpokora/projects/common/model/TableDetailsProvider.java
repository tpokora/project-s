package com.tpokora.projects.common.model;

import com.tpokora.projects.user.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pokor on 19.03.2016.
 */
@Repository("tableDetails")
public class TableDetailsProvider {

    @Autowired
    @Qualifier(value = "sessionFactory")
    static SessionFactory sessionFactory;

    public TableDetailsProvider(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static List<TableDetails> getAllTablesDetails() {
        List<TableDetails> tablesList = new ArrayList<TableDetails>();
        TableDetails tableDetails = new TableDetails();
        tableDetails.setName(sessionFactory.getClassMetadata(User.class).getEntityName());
        tableDetails.setColumns(sessionFactory.getClassMetadata(User.class).getPropertyNames(),
                sessionFactory.getClassMetadata(User.class).getPropertyTypes());
        tablesList.add(tableDetails);
        return tablesList;
    }

    public static TableDetails getTableDetails(Class clazz) {
        TableDetails tableDetails = new TableDetails();
        tableDetails.setName(sessionFactory.getClassMetadata(clazz).getEntityName());
        tableDetails.setColumns(sessionFactory.getClassMetadata(clazz).getPropertyNames(),
                sessionFactory.getClassMetadata(clazz).getPropertyTypes());
        return tableDetails;
    }
}
