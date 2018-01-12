package br.com.welson.agenda.model;

import java.io.Closeable;
import java.sql.*;

public class Sql implements AutoCloseable {

    private Connection connection;
    private PreparedStatement preparedStatement;

    public Sql() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=agenda;user=welson;password=123456789");
    }

    public void command(String command, String... paramters) throws SQLException {
        createStatement(command, paramters);
        preparedStatement.execute();
    }

    public ResultSet query(String command, String ... paramters) throws SQLException {
        createStatement(command, paramters);
        return preparedStatement.executeQuery();
    }

    private void createStatement(String command, String ... paramters) throws SQLException {
        preparedStatement = connection.prepareStatement(command);
        setParams(paramters);
    }

    private void setParams(String ... params) throws SQLException {
        for(int i = 1; i <= params.length; i++) {
            preparedStatement.setString(i, params[i-1]);
        }
    }

    @Override
    public void close() throws Exception {
        if(connection != null && !connection.isClosed()) {
            connection.close();
        }
        if(preparedStatement != null && !preparedStatement.isClosed()) {
            preparedStatement.close();
        }
    }
}
