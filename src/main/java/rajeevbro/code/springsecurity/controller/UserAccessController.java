package rajeevbro.code.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import rajeevbro.code.springsecurity.dto.LoginDto;
import rajeevbro.code.springsecurity.dto.userAuthenticationDto;
import rajeevbro.code.springsecurity.dto.AuthenticatedResponseDto;
import rajeevbro.code.springsecurity.entity.UserInfo;
import rajeevbro.code.springsecurity.exception.UserNotFoundException;
import rajeevbro.code.springsecurity.repository.UserAuthRepository;
import rajeevbro.code.springsecurity.security.ApplicationConfig;
import rajeevbro.code.springsecurity.security.auth.JwtService;
import rajeevbro.code.springsecurity.service.UserAccessService;

import java.util.List;

@RestController
public class UserAccessController {

    @Autowired
    private UserAuthRepository userAuthRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
private UserAccessService userAccessService;

    @Autowired
private ApplicationConfig applicationConfig;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/auth/register")
    public AuthenticatedResponseDto registerUser(@RequestBody userAuthenticationDto userAuthenticationDto)
    {
        return  userAccessService.registerUser(userAuthenticationDto);

    }

    @GetMapping("/auth/users")
    public List<UserInfo> findAllUser()
    {
        return userAccessService.findAllUser();
    }


    @PostMapping("/auth/login")
    public AuthenticatedResponseDto loginUser(@RequestBody LoginDto loginDto) throws AuthenticationException {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUserName(),loginDto.getPassword()));

        if(authentication.isAuthenticated()){
            String token = jwtService.generateToken(loginDto.getUserName());
            AuthenticatedResponseDto authToken = new AuthenticatedResponseDto();
            authToken.setToken(token);
            return authToken;

        }
        else {

        throw new AuthenticationException("USerName or Password is incorrect") {
        };
        }
    }


}
