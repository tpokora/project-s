package com.tpokora.projects.common.model;

import org.hibernate.SessionFactory;

/**
 * Created by pokor on 19.03.2016.
 */
public class TableDetailsProvider {

    public TableDetailsProvider() {}

    public static TableDetails getTableDetails(Class clazz, SessionFactory sessionFactory) {
        TableDetails tableDetails = new TableDetails();
        tableDetails.setName(sessionFactory.getClassMetadata(clazz).getEntityName());
        tableDetails.setColumns(sessionFactory.getClassMetadata(clazz).getPropertyNames(),
                sessionFactory.getClassMetadata(clazz).getPropertyTypes());
        return tableDetails;
    }
}
