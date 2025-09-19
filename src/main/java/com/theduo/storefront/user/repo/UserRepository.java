package com.theduo.storefront.user.repo;

import com.theduo.storefront.user.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
