package com.hendyirawan.jws1037;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.io.UnsupportedEncodingException;

public class CreateTokenApp {

    public static void main(String[] args) throws UnsupportedEncodingException {
        Algorithm algorithm = Algorithm.HMAC256("secret");
        String token = JWT.create()
                .withSubject("denny")
                .sign(algorithm);
        System.out.println(token);
    }

}
