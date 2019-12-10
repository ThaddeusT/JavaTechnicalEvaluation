package com.thaddeustuck.bookstorevaluationservice.Valuation.controller;

import com.thaddeustuck.bookstorevaluationservice.Valuation.models.Valuation;
import com.thaddeustuck.bookstorevaluationservice.Valuation.repository.ValuationRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Api(value="Book Store Inventory Valuation Service")
public class ValuationController {
    private final ValuationRepository valuationRepository = new ValuationRepository();

    @ApiOperation(value = "View the valuation of the inventory and the valuation of each item in the inventory")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved valuation of the inventory"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/valuation")
    public Valuation getAllItemValuation(){
        Valuation valuation = valuationRepository.calculateAllItemValuation();
        return valuation;
    }
}