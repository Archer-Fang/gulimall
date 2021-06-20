package com.atguigu.gulimall.product.vo;

/**
 * @author Created by Fangzj
 * @data 2021/6/19 20:58
 **/
import lombok.Data;

@Data
public class SkuHasStockVo {
    private Long skuId;
    private Boolean hasStock;
}