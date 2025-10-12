package com.theduo.storefront.user.repo;

import com.theduo.storefront.user.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
