package com.tpokora.projects.admin.service;

import com.tpokora.projects.common.model.TableDetails;
import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by pokor on 20.03.2016.
 */
@Service("tablesDetailsService")
public class TablesDetailsServiceImpl implements TablesDetailsService {

    @Autowired
    SessionFactory sessionFactory;

    public TablesDetailsServiceImpl() {
    }

    @Override
    public List<TableDetails> getAllTablesIDetails() {
        ArrayList<TableDetails> tableDetailsList = new ArrayList<TableDetails>();

        Map<String, ClassMetadata> classMetaDataMap = sessionFactory.getAllClassMetadata();

        for (Map.Entry<String, ClassMetadata> metaDataMap : classMetaDataMap.entrySet()) {
            ClassMetadata classMetadata = metaDataMap.getValue();
            AbstractEntityPersister abstractEntityPersister = (AbstractEntityPersister) classMetadata;
            TableDetails tableDetails = new TableDetails();
            tableDetails.setName(abstractEntityPersister.getTableName());
            tableDetails.setColumns(abstractEntityPersister.getPropertyNames(), abstractEntityPersister.getPropertyTypes());
        }

        return tableDetailsList;
    }
}
