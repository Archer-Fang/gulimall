package com.atguigu.common.to;

/**
 * @author Created by Fangzj
 * @data 2021/6/15 22:06
 **/
import lombok.Data;

import java.math.BigDecimal;


@Data
public class MemberPrice {
    //会员等级ID
    private Long id;
    //会员等级名
    private String name;
    //会员价格
    private BigDecimal price;
}