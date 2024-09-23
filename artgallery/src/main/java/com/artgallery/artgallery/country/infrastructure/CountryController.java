package com.artgallery.artgallery.country.infrastructure;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artgallery.artgallery.country.application.ICountry;
import com.artgallery.artgallery.country.domain.Country;
import com.artgallery.artgallery.utils.FieldValidation;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/country")
public class CountryController {

    @Autowired 
    private ICountry service;

    // http://localhost:8090/country/create

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody Country country, BindingResult result) {
        if (result.hasFieldErrors()) {
            return FieldValidation.validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(country));
    }

}
