package com.cry.web_delivery.util;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtHelper {
    @Value("${custom.token.key}")
    private String secKey;

    private long expiration = 8 * 60 * 60 * 1000;


    Date date = new Date();// thời gian hiện tại
    long newExpriedMilis = date.getTime()+ expiration;//  hiện tại + hết hạn
    Date newExpriation = new Date(newExpriedMilis);// thời gian tương lai



    public String generaToken (String data){

        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secKey));
        String token = Jwts.builder()
                .signWith(key)
                .setSubject(data)
                .setExpiration(newExpriation)
                .compact();
        return token;
    }



    public String parseToken(String token ){
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secKey));
        String data = Jwts.parserBuilder().setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return data;
    }
}
