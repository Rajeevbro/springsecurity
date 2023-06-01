package rajeevbro.code.springsecurity.security;

import com.fasterxml.jackson.databind.util.JSONPObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;
import rajeevbro.code.springsecurity.entity.ErrorMessage;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
@Component
@ResponseBody
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence
            (HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException
    {


        ErrorMessage message = ErrorMessage.builder()
                .http(HttpStatus.UNAUTHORIZED)
                .message("UnAuthorized").build();

    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    response.getWriter().write(message.toString());



//     response.setStatus(HttpStatus.UNAUTHORIZED.value());
//     response.setContentType(APPLICATION_JSON_VALUE);
//        response.getWriter().write(message.toString());




    }
}
