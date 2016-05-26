package com.tpokora.projects.common.service;

import com.tpokora.projects.config.AppTestConfig;
import com.tpokora.projects.config.DatabaseTestConfiguration;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by pokor on 26.03.2016.
 */
@ContextConfiguration(classes = {
        AppTestConfig.class,
        DatabaseTestConfiguration.class
})
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class AbstractServiceTest {
}
