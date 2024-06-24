package com.ps.dealership_api_starter.data;

import com.ps.dealership_api_starter.data.mysql.MySqlDaoBase;
import com.ps.dealership_api_starter.models.LeaseContract;
import com.ps.dealership_api_starter.models.Vehicle;

import javax.sql.DataSource;
import java.sql.*;

public class LeaseDao extends MySqlDaoBase {
    public LeaseDao(DataSource dataSource) {
        super(dataSource);
    }
    public LeaseContract createLease(LeaseContract leaseContract) {
        int generatedId = -1;
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO lease_contracts(contract_id, contract_date, customer_name, customer_email, vin, sales_tax, recording_fee, processing_fee, total_price, monthly_payment) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS
                );
        ) {
            preparedStatement.setInt(1, leaseContract.getContractId());
            preparedStatement.setString(2, leaseContract.getContractDate());
            preparedStatement.setString(3, leaseContract.getCustomerName());
            preparedStatement.setString(4, leaseContract.getCustomerEmail());
            preparedStatement.setInt(5, leaseContract.getVin());
            preparedStatement.setDouble(6, leaseContract.getSalesTax());
            preparedStatement.setDouble(7, leaseContract.getRecordingFee());
            preparedStatement.setDouble(8, leaseContract.getProcessingFee());
            preparedStatement.setDouble(9, leaseContract.getTotalPrice());
            preparedStatement.setDouble(10, leaseContract.getMonthlyPayment());

            preparedStatement.executeUpdate();

            try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                while (keys.next()) {
                    generatedId = keys.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return leaseContract;
    }
    public void deleteLease(int id){
        try(
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "DELETE FROM lease_contracts WHERE contract_id = ?"
                );
        ){
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException sql){
            sql.printStackTrace();
        }
    }
}
