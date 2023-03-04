package com.teoan.walletdemo.repository;


import com.teoan.walletdemo.domain.WalletLogDO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Teoan
 * @description
 */
@Repository
public interface WalletLogRepository extends CrudRepository<WalletLogDO,Integer> {


    List<WalletLogDO> findAllByUserIdOrderByHandelTimeDesc(Integer userId);


}
