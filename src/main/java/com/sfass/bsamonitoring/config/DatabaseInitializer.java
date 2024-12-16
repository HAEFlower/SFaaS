package com.sfass.bsamonitoring.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class DatabaseInitializer {

    private final DataSource dataSource;

    @PostConstruct
    public void initialize() {
        try (Connection connection = dataSource.getConnection()) {
            // 데이터베이스 존재 여부 확인
            boolean databaseExists = checkIfDatabaseExists(connection, "bsa_monitoring");

            if (!databaseExists) {
                log.info("Creating database bsa_monitoring...");
                // 데이터베이스 생성
                ScriptUtils.executeSqlScript(connection,
                        new ClassPathResource("sql/create-database.sql"));
            }

            // 테이블 존재 여부 확인
            boolean tablesExist = checkIfTablesExist(connection);

            if (!tablesExist) {
                log.info("Initializing database schema and data...");
                // 스키마 생성
                ScriptUtils.executeSqlScript(connection,
                        new ClassPathResource("sql/schema.sql"));
                // 초기 데이터 삽입
                ScriptUtils.executeSqlScript(connection,
                        new ClassPathResource("sql/data.sql"));
            }

        } catch (SQLException e) {
            log.error("Database initialization failed", e);
            throw new RuntimeException("Database initialization failed", e);
        }
    }

    private boolean checkIfDatabaseExists(Connection connection, String dbName)
            throws SQLException {
        try (ResultSet resultSet = connection.getMetaData().getCatalogs()) {
            while (resultSet.next()) {
                if (resultSet.getString(1).equals(dbName)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkIfTablesExist(Connection connection) throws SQLException {
        try (ResultSet tables = connection.getMetaData()
                .getTables(null, null, "part", null)) {
            return tables.next();
        }
    }
}