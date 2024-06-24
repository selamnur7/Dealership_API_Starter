package com.ps.dealership_api_starter.controllers;

import com.ps.dealership_api_starter.data.SalesDao;
import com.ps.dealership_api_starter.models.LeaseContract;
import com.ps.dealership_api_starter.models.SalesContract;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

public class SalesController {
    private SalesDao salesDao;
    @PostMapping()
    public SalesContract createSale(@RequestBody SalesContract salesContract)
    {
        try
        {
            return salesDao.createSale(salesContract);
        }
        catch(Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }
    @DeleteMapping("{id}")
    public void deleteSale(@PathVariable int id)
    {
        try
        {
            salesDao.deleteSale(id);



        }
        catch(Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }
}
