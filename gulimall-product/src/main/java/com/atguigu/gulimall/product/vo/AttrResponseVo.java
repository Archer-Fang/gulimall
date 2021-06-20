package com.atguigu.gulimall.product.vo;

/**
 * @author Created by Fangzj
 * @data 2021/6/20 11:14
 **/

import lombok.Data;

@Data
public class AttrResponseVo extends AttrVo{

    /**
     * 所属分类名字，如："手机/数码/手机"
     */
    private String catelogName;
    /**
     * 所属分组名字
     */
    private String groupName;


    //分类完整路
    private Long[] catelogPath;


}