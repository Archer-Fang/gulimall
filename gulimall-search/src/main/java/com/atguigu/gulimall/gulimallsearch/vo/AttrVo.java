package com.atguigu.gulimall.gulimallsearch.vo;

/**
 * @author Created by Fangzj
 * @data 2021/6/19 22:01
 **/
import lombok.Data;

import java.util.List;

/**
 * 查询的属性信息
 */
@Data
public class AttrVo {
    private Long attrId;

    private String attrName;

    private List<String> attrValue;
}