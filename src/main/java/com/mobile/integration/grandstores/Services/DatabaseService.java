package com.mobile.integration.grandstores.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean checkConnection() {
        try {
            // Test the connection by running a simple query
            jdbcTemplate.queryForObject("SELECT 1 FROM DUAL", Integer.class);
            return true;  // Connection successful
        } catch (Exception e) {
            return false; // Connection failed
        }
    }
}
