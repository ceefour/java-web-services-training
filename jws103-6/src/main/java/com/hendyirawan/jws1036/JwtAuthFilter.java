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
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Token for hendy:
 * eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJoZW5keSJ9.67HHM7EOdT-gyetGhgqY74oxbM3_EncIUI_nJooYbcM
 *
 * Token for denny:
 * eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkZW5ueSJ9.o-PAdC7gKC0sebeh7Ij-NPSpUF1hs7FWPgM2Z_vG0_E
 */
@Component
public class JwtAuthFilter implements Filter {
    private static final Logger LOG = LoggerFactory.getLogger(JwtAuthFilter.class);

//    @Autowired
//    private ObjectMapper mapper;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            boolean allowed = true;
            HttpServletRequest httpReq = (HttpServletRequest) request;
            HttpServletResponse httpResp = (HttpServletResponse) response;
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
            if (allowed) {
                chain.doFilter(request, response);
            } else {
                httpResp.setStatus(403);
                httpResp.setHeader("Content-Type", "application/json");
                httpResp.getWriter().write(
                        "{\"error\": \"Unauthorized\", \"message\": \"Access denied\"}");
//                mapper.writeValue(httpResp.getWriter(),
//                        new Error("Unauthorized", "Access denied"));
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
