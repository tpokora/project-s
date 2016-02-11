package com.tpokora.projects.common.utils;

import org.apache.log4j.Logger;
import org.springframework.util.DigestUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Tomek on 2016-01-17.
 */
public class Utilites {

    private static final Logger logger = Logger.getLogger(Utilites.class);

    public static String strToMD5(String str) {
        InputStream inputStream = new ByteArrayInputStream(str.getBytes());
        String output = "";
        try {
            output = DigestUtils.md5DigestAsHex(inputStream);
        } catch (IOException e) {
//            e.printStackTrace();
            logger.error("Null string");
        }

        return output;
    }
}
