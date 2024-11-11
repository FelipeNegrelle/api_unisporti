package com.unisporti.api_unisporti.config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class JDBC {

    private static final String jdbcUrl;
    private static final String jdbcUser;
    private static final String jdbcPassword;

    static {
        try (InputStream input = JDBC.class.getClassLoader().getResourceAsStream("application.properties")) {
            Properties properties = new Properties();
            if (input != null) {
                properties.load(input);
                jdbcUrl = properties.getProperty("spring.datasource.url");
                jdbcUser = properties.getProperty("spring.datasource.username");
                jdbcPassword = properties.getProperty("spring.datasource.password");
            } else {
                throw new RuntimeException("Arquivo de propriedades não encontrado.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar propriedades do banco de dados.", e);
        }
    }

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
    }
}
