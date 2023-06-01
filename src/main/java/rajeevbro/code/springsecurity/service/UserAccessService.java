package rajeevbro.code.springsecurity.service;

import rajeevbro.code.springsecurity.dto.AuthenticatedResponseDto;
import rajeevbro.code.springsecurity.dto.userAuthenticationDto;
import rajeevbro.code.springsecurity.entity.UserInfo;

import java.util.List;

public interface UserAccessService {
    public AuthenticatedResponseDto registerUser(userAuthenticationDto userAuthenticationDto);

    List<UserInfo> findAllUser();
}
