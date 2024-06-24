package com.ps.dealership_api_starter.controllers;

import com.ps.dealership_api_starter.data.VehicleDao;
import com.ps.dealership_api_starter.models.Dealership;
import com.ps.dealership_api_starter.models.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("vehicles")
@CrossOrigin
public class VehicleController {
    private VehicleDao vehicleDao;
    @Autowired
    public VehicleController(VehicleDao vehicleDao) {
        this.vehicleDao = vehicleDao;
    }
    @GetMapping("")
    public List<Vehicle> searchByPrice(@RequestParam(name="min", required = false) double min,
                                @RequestParam(name="max", required = false) double max
    )
    {
        try
        {
            return vehicleDao.searchByPrice(min, max);
        }
        catch(Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }

    @GetMapping("makemodel")
    public List<Vehicle> searchByMakeModel(@RequestParam(name="make", required = false) String make,
                                   @RequestParam(name="model", required = false) String model
    )
    {
        try
        {
            return vehicleDao.searchByMakeModel(make, model);
        }
        catch(Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }

    @GetMapping("year")
    public List<Vehicle> searchByYear(@RequestParam(name="year", required = false) int minYear,
                                           @RequestParam(name="year", required = false) int maxYear
    )
    {
        try
        {
            return vehicleDao.searchByYear(minYear, maxYear);
        }
        catch(Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }
    @GetMapping("{color}")
    public List<Vehicle> searchByColor(@PathVariable String color )
    {
        try
        {
            return vehicleDao.searchByColor(color);

        }

        catch(Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }
    @GetMapping("{type}")
    public List<Vehicle> searchByType(@PathVariable String type )
    {
        try
        {
            return vehicleDao.searchByType(type);

        }

        catch(Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }
    @GetMapping("odometer")
    public List<Vehicle> searchbyMileage(@RequestParam(name="odometer", required = false) int minMile,
                                      @RequestParam(name="odometer", required = false) int maxMile
    )
    {
        try
        {
            return vehicleDao.searchbyMileage(minMile, maxMile);
        }
        catch(Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }
    @PostMapping()
    public Vehicle createVehicle(@RequestBody Vehicle vehicle)
    {
        try
        {
            return vehicleDao.createVehicle(vehicle);
        }
        catch(Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }
    @DeleteMapping("{vin}")
    public void deleteVehicle(@PathVariable int vin)
    {
        try
        {
            var product = vehicleDao.getByVin(vin);

            if(product == null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

            vehicleDao.deleteVehicle(vin);
        }
        catch(Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }
}
