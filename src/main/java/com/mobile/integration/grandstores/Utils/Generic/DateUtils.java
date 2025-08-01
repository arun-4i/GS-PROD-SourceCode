package com.mobile.integration.grandstores.Utils.Generic;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

import java.sql.Date;

import java.sql.Timestamp;

@Component
public class DateUtils {
 
    
    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
 
    private final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     

    public java.sql.Date parseSQLDate(String date) {
        try {
            return new Date(DATE_FORMAT.parse(date).getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }
     
    public java.sql.Timestamp parseSQLTimestamp(String timestamp) {
        try {
            return new Timestamp(DATE_TIME_FORMAT.parse(timestamp).getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }





    // private java.util.Date parseDate(String date) {
    //     try {
    //         return DATE_FORMAT.parse(date);
    //     } catch (ParseException e) {
    //         throw new IllegalArgumentException(e);
    //     }
    // }
     
    // private java.util.Date parseTimestamp(String timestamp) {
    //     try {
    //         return DATE_TIME_FORMAT.parse(timestamp);
    //     } catch (ParseException e) {
    //         throw new IllegalArgumentException(e);
    //     }
    // }

}