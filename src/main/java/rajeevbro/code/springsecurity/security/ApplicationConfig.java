package rajeevbro.code.springsecurity.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;
import rajeevbro.code.springsecurity.entity.UserInfo;
import rajeevbro.code.springsecurity.repository.UserAuthRepository;

import java.util.Optional;


@Configuration

@RequiredArgsConstructor
public class ApplicationConfig {


    @Autowired
    private UserAuthRepository userAuthRepository;

    @Bean
    public UserDetailsService userDetailsService()
    {
        return  (String userName)->{
            Optional<UserInfo> userDb = userAuthRepository.findByuserName(userName);

            if(userDb.isEmpty()){
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"User name not found / unauthorized");
            }

            return  userDb.map(UserDetailUserService::new).orElseThrow(RuntimeException::new);


        };
    }
    

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    
}
