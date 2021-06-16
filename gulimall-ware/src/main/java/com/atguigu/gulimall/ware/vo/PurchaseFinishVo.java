package com.atguigu.gulimall.ware.vo;

/**
 * @author Created by Fangzj
 * @data 2021/6/16 16:44
 **/

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class PurchaseFinishVo {
    @NotNull
    private Long id;

    private List<PurchaseFinishItem> items;
}