package com.tpokora.projects.common.utils;

import java.util.UUID;

/**
 * Created by pokor on 26.10.2016.
 */
public class SessionIdentifierGenerator {

    /**
     * Generate random session id of length 36
     * @return
     */
    public static String generateSessionID() {
        return UUID.randomUUID().toString();
    }
}
