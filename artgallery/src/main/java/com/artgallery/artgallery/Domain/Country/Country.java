package com.artgallery.artgallery.Domain.Country;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Column;

@Entity
@Getter
@Setter 
@NoArgsConstructor
@AllArgsConstructor
public class Country {

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id; 

    @NotBlank
    @NotEmpty
    @NotNull
    @Column(columnDefinition = "varchar(50)" , name = "nombre") 
    private String Nombre;

    




    

    



}
