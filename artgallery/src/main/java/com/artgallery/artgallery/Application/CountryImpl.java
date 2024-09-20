package com.artgallery.artgallery.Application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artgallery.artgallery.Domain.Country.Country;
import com.artgallery.artgallery.Domain.Country.ICountry;
import com.artgallery.artgallery.Infraestructure.Country.CountryRepository;

@Service
public class CountryImpl implements ICountry {

    @Autowired 
    private CountryRepository repository;

    @Override
    public Country crear(Country country) {
        return repository.save(country);
    }

}
