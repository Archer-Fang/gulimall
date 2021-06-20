package com.atguigu.gulimall.gulimallsearch.vo;

/**
 * @author Created by Fangzj
 * @data 2021/6/19 22:01
 **/
import lombok.Data;

/**
 * 查询的品牌信息
 */
@Data
public class BrandVo {

    private Long brandId;
    private String brandName;
    private String brandImg;
}
