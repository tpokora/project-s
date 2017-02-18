package com.tpokora.projects.common;

import com.tpokora.projects.config.AppTestConfig;
import com.tpokora.projects.config.DatabaseTestConfiguration;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by pokor on 26.01.2017.
 */
@ContextConfiguration(classes =  {
        AppTestConfig.class,
        DatabaseTestConfiguration.class
})
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:properties/loc.properties")
@Ignore
public class AbstractTest {
}
