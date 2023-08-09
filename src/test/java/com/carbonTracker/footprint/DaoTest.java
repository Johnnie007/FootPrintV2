package com.carbonTracker.footprint;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.*;

import static org.mockito.Mockito.mock;

public class DaoTest {
    private Connection connection = mock();
    private DataSource dataSource = mock();
    private Statement statement = mock();
    private PreparedStatement preparedStatement = mock();
    private ResultSet resultSet = mock();
    private CallableStatement callableStatement = mock();
    private JdbcTemplate template = new JdbcTemplate(this.dataSource);

    @BeforeEach
    public void setup() throws Exception{

    }


}
