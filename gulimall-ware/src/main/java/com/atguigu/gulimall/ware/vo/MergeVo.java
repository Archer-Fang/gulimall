package com.atguigu.gulimall.ware.vo;

/**
 * @author Created by Fangzj
 * @data 2021/6/16 16:28
 **/
import lombok.Data;

import java.util.List;

@Data
public class MergeVo {
    private Long purchaseId;
    private List<Long> items;
}