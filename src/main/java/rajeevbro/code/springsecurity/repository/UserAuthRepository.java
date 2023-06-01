package rajeevbro.code.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rajeevbro.code.springsecurity.entity.UserInfo;

import java.util.Optional;


@Repository
public interface UserAuthRepository extends JpaRepository<UserInfo,Long> {

    public Optional<UserInfo> findByuserName(String userName);
}
