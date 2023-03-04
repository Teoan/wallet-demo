package com.teoan.walletdemo.mapstruct;

import com.teoan.walletdemo.domain.UserDO;
import com.teoan.walletdemo.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * @author Teoan
 * @description 用户类映射
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapStruct extends BaseMapping<UserDO, UserDTO> {
}
