package com.userpassword.security.jwt;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import io.jsonwebtoken.Jwts;

@Service
public class JWTGeneration {

    private String secretkey;
    public JWTGeneration() throws NoSuchAlgorithmException {
        KeyGenerator keygen=KeyGenerator.getInstance("HmacSHA256");
        SecretKey sk=keygen.generateKey();
        secretkey= Base64.getEncoder().encodeToString(sk.getEncoded());
    }
    public String generatejwt(String username){
        Map<String, Object> claims=new HashMap<>();
        claims.put("mail","demo@mail.com");
        return Jwts.builder()
                .subject(username)
                .claims()
                .add(claims)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+60*60*100))
                .and()
                .signWith(getKey())
                .compact();
    }
    public Key getKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretkey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
