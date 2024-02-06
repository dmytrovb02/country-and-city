package com.techtask.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;

@TestConfiguration
public class PostgreSqlContainer extends PostgreSQLContainer<PostgreSqlContainer> {
    private static final String DB_IMAGE = "postgres:latest";
    private static PostgreSqlContainer postgresContainer;

    private PostgreSqlContainer() {
        super(DB_IMAGE);
    }

    public static synchronized PostgreSqlContainer getInstance() {
        if (postgresContainer == null) {
            postgresContainer = new PostgreSqlContainer();
        }
        return postgresContainer;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("TEST_DB_URL", postgresContainer.getJdbcUrl());
        System.setProperty("TEST_DB_USERNAME", postgresContainer.getUsername());
        System.setProperty("TEST_DB_PASSWORD", postgresContainer.getPassword());
    }

    @Override
    public void stop() {
        super.stop();
    }
}
