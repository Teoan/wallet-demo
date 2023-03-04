package com.teoan.walletdemo.domain;


import com.teoan.walletdemo.enums.HandelEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "WALLET_LOG")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalletLogDO {

    /**
     * 操作日志标识ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id ;



    /**
     * 用户id
     */
    @Column(name = "user_id", nullable = false)
    public Integer userId;

    /**
     * 操作类型
     */

    @Column(name = "handel_type")
    public HandelEnum handelEnum;

    /**
     * 操作金额
     */
    @Column(name = "amount")
    public Integer amount;

    /**
     * 操作时间
     */
    @Column(name = "handel_time")
    public Date handelTime;

}
