package com.theduo.storefront.user.repo;

import com.theduo.storefront.user.entity.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Integer> {
}
