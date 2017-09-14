package com.hendyirawan.jws1037;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountriesController {

    private static final Logger LOG = LoggerFactory.getLogger(CountriesController.class);

    @Autowired
    private CountryRepository countryRepo;

    @GetMapping
    public List<Country> getAllCountries(@ModelAttribute("userId") String userId) {
        if (!"hendy".equals(userId)) {
            throw new AccessDeniedException(String.format(
                    "User '%s' does not have sufficient privileges",
                    userId));
        }
        return countryRepo.findAll();
    }

    @ModelAttribute("userId")
    public String getUserId(@RequestHeader("Authorization") String authorization)
            throws UnsupportedEncodingException {
        Preconditions.checkNotNull(authorization,
                "Authorization header is required");
        String[] splitted = authorization.split(" ");
        if (!"Bearer".equals(splitted[0])) {
            throw new AccessDeniedException("Authorization must be Bearer");
        }
        String token = splitted[1];

        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm)
                .build(); //Reusable verifier instance
        DecodedJWT jwt = verifier.verify(token);
        String userId = jwt.getSubject();
        LOG.info("User: {}", userId);

        return userId;
    }

}
