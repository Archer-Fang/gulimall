package com.atguigu.gulimall.ware.vo;

/**
 * @author Created by Fangzj
 * @data 2021/6/19 20:49
 **/
import lombok.Data;

@Data
public class SkuHasStockVo {
    private Long skuId;
    private Boolean hasStock;
}