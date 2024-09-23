package com.artgallery.artgallery.country.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artgallery.artgallery.country.application.ICountry;
import com.artgallery.artgallery.country.domain.Country;

@Service
public class CountryImpl implements ICountry {

    @Autowired 
    private CountryRepository repository;

    @Override
    public Country crear(Country country) {
        return repository.save(country);
    }

}
