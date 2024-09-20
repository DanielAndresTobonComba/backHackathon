package com.artgallery.artgallery.Infraestructure.Country;


import org.springframework.data.jpa.repository.JpaRepository;

import com.artgallery.artgallery.Domain.Country.Country;

public interface CountryRepository extends JpaRepository <Country , Long> {

}
