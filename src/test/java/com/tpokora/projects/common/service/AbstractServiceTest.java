package com.tpokora.projects.common.service;

import com.tpokora.projects.config.AppConfig;
import com.tpokora.projects.config.DatabaseConfiguration;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by pokor on 26.03.2016.
 */
@ContextConfiguration(classes = {
        AppConfig.class,
        DatabaseConfiguration.class
})
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class AbstractServiceTest {
}
