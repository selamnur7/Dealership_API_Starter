package com.ps.dealership_api_starter.data.mysql;

import com.ps.dealership_api_starter.models.Vehicle;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class MySqlDaoBase
{
    private DataSource dataSource;

    public MySqlDaoBase(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    protected Connection getConnection() throws SQLException
    {
        return dataSource.getConnection();
    }

}
