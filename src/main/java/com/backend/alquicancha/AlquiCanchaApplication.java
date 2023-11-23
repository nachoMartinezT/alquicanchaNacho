package com.backend.alquicancha;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.sql.SQLException;

@SpringBootApplication

public class AlquiCanchaApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(AlquiCanchaApplication.class);

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        SpringApplication.run(AlquiCanchaApplication.class, args);
        LOGGER.info("Running ...");
    }
}
