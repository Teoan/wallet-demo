package com.teoan.walletdemo.repository;

import com.teoan.walletdemo.domain.UserDO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Teoan
 * @description
 */
@Repository
public interface UserRepository extends CrudRepository<UserDO,Integer> {
}
