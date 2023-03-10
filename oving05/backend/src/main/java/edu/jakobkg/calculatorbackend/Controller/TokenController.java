package edu.jakobkg.calculatorbackend.Controller;

import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import ch.qos.logback.classic.Logger;
import edu.jakobkg.calculatorbackend.DAO.DBWrapper;
import edu.jakobkg.calculatorbackend.Model.LoginRequest;

import java.time.Duration;
import java.time.Instant;

@RestController
@RequestMapping(value = "/token")
@EnableAutoConfiguration
@CrossOrigin
public class TokenController {
    public static final String secret = "kjempehemmelig";

    private static final String issuer_id = "JAKOBS KALKULATOR";
    private static final Duration TOKEN_VALIDITY = Duration.ofMinutes(5);

    Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @PostMapping(value = "")
    @ResponseStatus(value = HttpStatus.CREATED)
    public String getToken(final @RequestBody LoginRequest request) {
        DBWrapper db;
        try {
            db = new DBWrapper(DBWrapper.createConnection());

            if (db.checkCreds(request.username(), request.password())) {
                logger.info("Genererer token for " + request.username());
                return TokenController.generateToken(request.username());
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.info("Kunne ikke generere token for "
                + request.username()
                + ", bruker ikke funnet eller feil passord angitt");
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }

    private static String generateToken(final String username) {
        final Instant now = Instant.now();
        final Algorithm algorithm = Algorithm.HMAC512(TokenController.secret);

        String result = JWT.create()
                .withSubject(username)
                .withIssuer(TokenController.issuer_id)
                .withIssuedAt(now)
                .withExpiresAt(now.plus(TokenController.TOKEN_VALIDITY))
                .sign(algorithm);

        return result;
    }
}
