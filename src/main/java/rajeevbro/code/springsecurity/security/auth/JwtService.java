package rajeevbro.code.springsecurity.security.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {



    @Value("${spring.jwt.jwtExpirationTime}")
    private  long EXPIRATION_TIME ;


    @Value("${spring.jwt.jwtSecretKey}")
    private  String SECRET_KEY;

    public String generateToken(String userName){
        Map<String,Object> claims = new HashMap<>();

        return createToken(userName, claims);
    }
    public String createToken(String userName, Map<String,Object> claims)
    {
        System.out.println("MUJI"+userName +SECRET_KEY);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    private Key getSignKey() {
        byte[] key = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(key);

    }


    public String extractUserName(String token)
    {
        return extractClaim(token,Claims::getSubject);
    }

    public boolean isTOkenValid(String token, String userName)
    {
        if(userName.equals(extractUserName(token))
                && (new Date(System.currentTimeMillis()).before(extractClaim(token,Claims::getExpiration))))
        {
            return true;
        }
        return false;
    };


    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver)
    {
        Claims claim = extractAllClaims(token);
        return claimsResolver.apply(claim);
    }


    public Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }
}
