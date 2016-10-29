package com.tpokora.projects.common.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by pokor on 29.10.2016.
 */
public class SessionIdentifierGeneratorTest {

    @Test
    public void sessionIdentifierGenerator_newSession() {
        String sessionId = SessionIdentifierGenerator.generateSessionID();
        Assert.assertEquals(true, sessionId.length() == 36);
    }
}
