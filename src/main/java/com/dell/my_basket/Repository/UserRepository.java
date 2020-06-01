package com.dell.my_basket.Repository;

import com.dell.my_basket.Models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,String> {
    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
