package com.ps.dealership_api_starter.data;

import com.ps.dealership_api_starter.data.mysql.MySqlDaoBase;
import com.ps.dealership_api_starter.models.LeaseContract;
import com.ps.dealership_api_starter.models.SalesContract;

import javax.sql.DataSource;
import java.sql.*;

public class SalesDao extends MySqlDaoBase {
    public SalesDao(DataSource dataSource) {
        super(dataSource);
    }
    public SalesContract createSale(SalesContract salesContract) {
        int generatedId = -1;
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO sales_contracts(contract_id, contract_date, customer_name, customer_email, vin, sales_tax, recording_fee, processing_fee, total_price,finance_option, monthly_payment) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS
                );
        ) {
            preparedStatement.setInt(1, salesContract.getContractId());
            preparedStatement.setString(2, salesContract.getContractDate());
            preparedStatement.setString(3, salesContract.getCustomerName());
            preparedStatement.setString(4, salesContract.getCustomerEmail());
            preparedStatement.setInt(5, salesContract.getVin());
            preparedStatement.setDouble(6, salesContract.getSalesTax());
            preparedStatement.setDouble(7, salesContract.getRecordingFee());
            preparedStatement.setDouble(8, salesContract.getProcessingFee());
            preparedStatement.setDouble(9, salesContract.getTotalPrice());
            preparedStatement.setString(10, salesContract.getFinanceOption());
            preparedStatement.setDouble(11, salesContract.getMonthlyPayment());

            preparedStatement.executeUpdate();

            try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                while (keys.next()) {
                    generatedId = keys.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return salesContract;
    }
    public void deleteSale(int id){
        try(
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "DELETE FROM sales_contracts WHERE contract_id = ?"
                );
        ){
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException sql){
            sql.printStackTrace();
        }
    }
}
