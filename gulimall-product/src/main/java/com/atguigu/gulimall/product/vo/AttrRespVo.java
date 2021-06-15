package com.atguigu.gulimall.product.vo;

import lombok.Data;

/**
 * @author Created by Fangzj
 * @data 2021/6/15 17:04
 **/
@Data
public class AttrRespVo extends AttrVo {
    /**
     * 			"catelogName": "手机/数码/手机", //所属分类名字
     * 			"groupName": "主体", //所属分组名字
     */
    private String catelogName;
    private String groupName;

    private Long[] catelogPath;
}