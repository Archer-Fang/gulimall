package com.atguigu.gulimall.ware.vo;

/**
 * @author Created by Fangzj
 * @data 2021/6/16 16:45
 **/
import lombok.Data;

@Data
public class PurchaseFinishItem {
    private Long itemId;
    private Integer status;
    private String reason;
}