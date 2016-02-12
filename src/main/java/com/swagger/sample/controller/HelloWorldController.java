package com.swagger.sample.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by aherrin on 2/10/16.
 */
@RestController
@RequestMapping("hello")
public class HelloWorldController {

    @RequestMapping(value="", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Hello World Message", notes = "This is for any notes about the end point.")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Failure")})
    public String getMessage() {
        return "Psych! Hello World is over used!";
    }
}
