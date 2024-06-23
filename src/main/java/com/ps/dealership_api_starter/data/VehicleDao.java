package com.ps.dealership_api_starter.data;

import com.ps.dealership_api_starter.data.mysql.MySqlDaoBase;
import com.ps.dealership_api_starter.models.Vehicle;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;



@Component
public class VehicleDao extends MySqlDaoBase {
    public VehicleDao(DataSource dataSource) {
        super(dataSource);
    }

    public List<Vehicle> searchByPrice (double min, double max) {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles WHERE price BETWEEN ? AND ?";

        try (Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, min);
            statement.setDouble(2, max);
            ResultSet row = statement.executeQuery();

            while (row.next())
            {
                Vehicle vehicle = new Vehicle();
                vehicles.add(vehicle);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return vehicles;
    }

    public List<Vehicle> searchByMakeModel (String make, String model) {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles WHERE make = ? AND model = ?";

        try (Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, make);
            statement.setString(2, model);
            ResultSet row = statement.executeQuery();

            while (row.next())
            {
                Vehicle vehicle = new Vehicle();
                vehicles.add(vehicle);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return vehicles;
    }
    public List<Vehicle> searchByYear (int minYear, int maxYear) {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles WHERE year BETWEEN ? AND ?";

        try (Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, minYear);
            statement.setDouble(2, maxYear);
            ResultSet row = statement.executeQuery();

            while (row.next())
            {
                Vehicle vehicle = new Vehicle();
                vehicles.add(vehicle);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return vehicles;
    }
    public List<Vehicle> searchByColor (String color) {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles WHERE color = ?";

        try (Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, color);
            ResultSet row = statement.executeQuery();

            while (row.next())
            {
                Vehicle vehicle = new Vehicle();
                vehicles.add(vehicle);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return vehicles;
    }
    public List<Vehicle> searchbyMileage (int minMile, int maxMile) {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles WHERE odometer BETWEEN ? AND ?";

        try (Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, minMile);
            statement.setInt(2, maxMile);
            ResultSet row = statement.executeQuery();

            while (row.next())
            {
                Vehicle vehicle = new Vehicle();
                vehicles.add(vehicle);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return vehicles;
    }
    public List<Vehicle> searchByType (String type) {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles WHERE vehicle_type = ?";

        try (Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, type);
            ResultSet row = statement.executeQuery();

            while (row.next())
            {
                Vehicle vehicle = new Vehicle();
                vehicles.add(vehicle);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return vehicles;
    }
    public void deleteVehicle(int vin){
        try(
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "DELETE FROM vehicles WHERE vin = ?"
                );
        ){
            preparedStatement.setInt(1, vin);
            preparedStatement.executeUpdate();
        } catch (SQLException sql){
            sql.printStackTrace();
        }
    }
    public int createVehicle(Vehicle vehicle) {
        int generatedId = -1;
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO vehicles(vin, year, make, model, vehicle_type, color, odometer, price, SOLD) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS
                );
        ) {
            preparedStatement.setInt(1, vehicle.getVin());
            preparedStatement.setInt(2, vehicle.getYear());
            preparedStatement.setString(3, vehicle.getMake());
            preparedStatement.setString(4, vehicle.getModel());
            preparedStatement.setString(5, vehicle.getVehicleType());
            preparedStatement.setString(6, vehicle.getColor());
            preparedStatement.setInt(7, vehicle.getOdometer());
            preparedStatement.setDouble(8, vehicle.getPrice());
            preparedStatement.setBoolean(9, vehicle.isSold());
            preparedStatement.executeUpdate();

            try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                while (keys.next()) {
                    generatedId = keys.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return generatedId;
    }
}
