package com.artgallery.artgallery.country.infrastructure;


import org.springframework.data.jpa.repository.JpaRepository;

import com.artgallery.artgallery.country.domain.Country;

public interface CountryRepository extends JpaRepository <Country , Long> {

}
