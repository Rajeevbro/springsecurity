package rajeevbro.code.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rajeevbro.code.springsecurity.dto.AuthenticatedResponseDto;
import rajeevbro.code.springsecurity.dto.userAuthenticationDto;
import rajeevbro.code.springsecurity.entity.UserInfo;
import rajeevbro.code.springsecurity.repository.UserAuthRepository;
import rajeevbro.code.springsecurity.security.auth.JwtService;

import java.util.List;

@Service
public class UserAccessServiceImpl implements UserAccessService {

    @Autowired
    private UserAuthRepository userAuthRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;
    @Override
    public AuthenticatedResponseDto registerUser(userAuthenticationDto userAuthenticationDto)
    {
       UserInfo user = new UserInfo();
       user.setUserName(userAuthenticationDto.getUserName());
       user.setPassword(passwordEncoder.encode(userAuthenticationDto.getPassword()));
       user.setRole(userAuthenticationDto.getRole());

       userAuthRepository.save(user);

        System.out.println(user.getUserName());


       String token = jwtService.generateToken(user.getUserName());
        System.out.println(token);

       AuthenticatedResponseDto response  = new AuthenticatedResponseDto();
       response.setToken(token);
       return response;




    }

    @Override
    public List<UserInfo> findAllUser() {
        return userAuthRepository.findAll();
    }
}
