package com.teoan.walletdemo.domain;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * @author Teoan
 * @description 用户DO类
 * @date 2023/3/2 10:11
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "USER")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDO {


    /**
     * 用户唯一标识ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id ;

    /**
     * 用户名称
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * 余额
     */
    @Column(name = "balance")
    private Integer balance;


    /**
     * 省略其他用户相关的字段
     */

}
