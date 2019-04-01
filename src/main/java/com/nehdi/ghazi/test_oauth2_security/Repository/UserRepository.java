package com.nehdi.ghazi.test_oauth2_security.Repository;

import com.nehdi.ghazi.test_oauth2_security.Entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

  Optional<UserEntity> findByUsername(String username);

}
