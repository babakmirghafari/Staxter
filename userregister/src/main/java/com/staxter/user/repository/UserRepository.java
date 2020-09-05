package com.staxter.user.repository;

import com.staxter.user.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Repository interface using Spring Data CRUDRepository
 */
public interface UserRepository extends CrudRepository<User,Long> {

    /**
     * This method use when we want to query user with username
     * @param userName
     * @return
     */
    @Query("SELECT u FROM User u WHERE u.userName=?1")
    Optional<User> findUserByUserName(String userName);
}
