package com.artgallery.artgallery.jwt;



import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.*;


@Service
public class JwtService {

    private static final String SECRET_KEY = "iusgbdiohas80192987eouqw878193e8uiubweoihfiasoiefhi7q38r"; // especificamos una secret key a gusto 

    public String getToken(UserDetails user) {
        // usaremos hashmap en lo claims para pasar info adicional en el token
        return getToken(new HashMap<>() , user);

    }

    private String getToken(Map<String,Object> extraClaims, UserDetails user) {

        return Jwts 
            .builder()
            .setClaims(extraClaims)
            .setSubject(user.getUsername()) // tomo el nombre del usuario cmo sujeto
            .setIssuedAt(new Date(System.currentTimeMillis())) // tomo la fecha de creacion
            .setExpiration(new Date(System.currentTimeMillis()+1000*60*24)) // asigno una fecha de expiracion un dia despues de la fecha de creacion    
            .signWith(getKey(), SignatureAlgorithm.HS256) // esta es la firma , tengo u objeto de tipo key y el algoritmo de encriptacion 
            .compact(); // crea el objeto y lo serializa
    }


    
    private Key getKey() {
        byte [] keyBytes = Decoders.BASE64.decode(SECRET_KEY); // decodificamos nuestra secret key

        return Keys.hmacShaKeyFor(keyBytes) ; // premite crear una instacia de la secret key
    }

    public String getUsernameFromToken(String token) {
        // vamos a get claim y le pasamos el tokem y el claims en particular , en este caso esta en el subject el username
        return getClaim(token, Claims::getSubject);
    }


    // aca se verifica si el token es valido pero se debe tener en cuenta la fecha de expiracion
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        // si el nombre es el mimos que el de userdetails y el token no ha expirado retornara el username
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token)) ;

        

    }

    private Claims getAllClaims (String token) {
        return Jwts 
            .parserBuilder()
            .setSigningKey(getKey()) // esta es la clave secreta
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    // este es un metodo generico
    public <T> T getClaim(String token , Function<Claims,T> claimsResolver) {
        // obtener todos los claims
        final Claims claims=getAllClaims(token);

        // aplicamos todos los claims 
        return claimsResolver.apply(claims);
    }


    private Date getExpiration (String token) {
        return getClaim(token, Claims::getExpiration);
    }

    private Boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }

}
