package com.atguigu.gulimall.product.vo;

/**
 * @author Created by Fangzj
 * @data 2021/6/15 21:33
 **/
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MemberPrice {

    private Long id;
    private String name;
    private BigDecimal price;


}