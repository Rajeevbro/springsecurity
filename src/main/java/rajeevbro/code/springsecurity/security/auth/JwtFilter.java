package rajeevbro.code.springsecurity.security.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;
import rajeevbro.code.springsecurity.security.ApplicationConfig;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {



    @Autowired
    private JwtService jwtService;

    @Autowired
     private UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal
            (@NotNull  HttpServletRequest request,
             @NotNull  HttpServletResponse response,
            @NotNull FilterChain filterChain)
            throws ServletException, IOException
    {

        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String userName;

        System.out.println("I am running....");

        if( authHeader ==null || !authHeader.startsWith("Bearer "))
        {
           filterChain.doFilter(request,response);
          return;

        }

        jwtToken = authHeader.substring(7);

        System.out.println("Token is >>>"+ jwtToken);
        userName =jwtService.extractUserName(jwtToken);
        System.out.println("Username>>>" + userName);
        System.out.println(SecurityContextHolder.getContext().getAuthentication()==null);
        if(userName !=null && SecurityContextHolder.getContext().getAuthentication()==null)
        {

            System.out.println("Check Passes");
            UserDetails userDetails =userDetailsService.loadUserByUsername(userName);
           if (jwtService.isTOkenValid(jwtToken,userDetails.getUsername()))
           {
               System.out.println("valid");
               UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                       userDetails,null,userDetails.getAuthorities()
               );
               token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
               SecurityContextHolder.getContext().setAuthentication(token);

           }
           else {
               throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"token not valid");
           }

        }
        filterChain.doFilter(request,response);


    }
}
