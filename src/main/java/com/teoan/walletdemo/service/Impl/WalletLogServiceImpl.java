package com.teoan.walletdemo.service.Impl;

import com.teoan.walletdemo.dto.WalletLogDTO;
import com.teoan.walletdemo.mapstruct.WalletLogMapStruct;
import com.teoan.walletdemo.repository.WalletLogRepository;
import com.teoan.walletdemo.service.WalletLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WalletLogServiceImpl implements WalletLogService {

    @Resource
    WalletLogRepository repository;

    @Resource
    WalletLogMapStruct mapStruct;


    @Override
    public List<WalletLogDTO> getUserWalletLog(Integer userId) {
        return mapStruct.sourceToTarget(repository.findAllByUserIdOrderByHandelTimeDesc(userId)) ;
    }
}
