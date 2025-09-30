package com.theduo.storefront.user.repo;

import com.theduo.storefront.user.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsByEmail(String email);

    @EntityGraph(attributePaths = "groupType")
    Optional<User> findUserByEmail(String email);
}
