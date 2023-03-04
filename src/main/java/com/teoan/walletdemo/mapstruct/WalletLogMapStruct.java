package com.teoan.walletdemo.mapstruct;

import com.teoan.walletdemo.domain.WalletLogDO;
import com.teoan.walletdemo.dto.HandelWalletDTO;
import com.teoan.walletdemo.dto.WalletLogDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface WalletLogMapStruct extends BaseMapping<WalletLogDO, WalletLogDTO> {


    WalletLogDO handelWalletToLog(HandelWalletDTO handelWalletDTO);



}
