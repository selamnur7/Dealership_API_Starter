package com.ps.dealership_api_starter.controllers;

import com.ps.dealership_api_starter.data.LeaseDao;
import com.ps.dealership_api_starter.models.Dealership;
import com.ps.dealership_api_starter.models.LeaseContract;
import com.ps.dealership_api_starter.models.SalesContract;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

public class LeaseController {
    private LeaseDao leaseDao;
    @PostMapping()
    public LeaseContract createLease(@RequestBody LeaseContract leaseContract)
    {
        try
        {
            return leaseDao.createLease(leaseContract);
        }
        catch(Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }
    @DeleteMapping("{id}")
    public void deleteLease(@PathVariable int id)
    {
        try
        {
            leaseDao.deleteLease(id);



        }
        catch(Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }
}
