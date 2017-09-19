package com.hendyirawan.jws1036;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class JwtAuthFilter implements Filter {
    private static final Logger LOG = LoggerFactory.getLogger(JwtAuthFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        boolean allowed = true;
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpReq = (HttpServletRequest) request;
            if (!"OPTIONS".equals(httpReq.getMethod())) {
                String authorization = httpReq.getHeader("Authorization");
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

                allowed = "hendy".equals(userId); // any custom logic here
            }
        }
        if (allowed) {
            chain.doFilter(request, response);
        } else {
            throw new AccessDeniedException("Access denied");
        }
    }

    @Override
    public void destroy() {
    }
}
