package com.swagger.sample.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.gradle.Person;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("welcome")
public class SwaggerController {
	
 
    @RequestMapping(value="welcomeMessage", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Swagger welcome message", notes = "This is for any notes about the end point.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Person.class),
            @ApiResponse(code = 500, message = "Failure")})
    public String getMessage() {
        return "Welcome to this sample Swagger app!";
    }

    @RequestMapping(value="welcomeMessage/{message}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Swagger welcome message", notes = "This is for any notes about the end point.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Person.class),
            @ApiResponse(code = 500, message = "Failure")})
    public String returnMessage(@PathVariable String message) {
        return "Your Message: " + message;
    }

    @RequestMapping(value="person", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Swagger welcome message", notes = "This is for any notes about the end point.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Person.class),
            @ApiResponse(code = 500, message = "Failure")})
    public String createPerson(@RequestBody Person person) {

        return person.toString();
    }
 
}

