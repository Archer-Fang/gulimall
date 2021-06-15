package com.atguigu.gulimall.product.vo;

/**
 * @author Created by Fangzj
 * @data 2021/6/15 17:03
 **/

import com.atguigu.gulimall.product.entity.AttrEntity;
import lombok.Data;

@Data
public class AttrVo extends AttrEntity {
    private Long attrGroupId;
}