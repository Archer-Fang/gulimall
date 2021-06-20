package com.atguigu.gulimall.gulimallsearch.feign;
import com.atguigu.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @author Created by Fangzj
 * @data 2021/6/20 10:59
 **/
@FeignClient("gulimall-product")
public interface ProductFeignService {
    /**
     * 信息
     * 功能：查询属性详情
     * API：https://easydoc.xyz/doc/75716633/ZUqEdvA4/7C3tMIuF
     */
    @RequestMapping("/product/attr/info/{attrId}")
    public R info(@PathVariable("attrId") Long attrId);
}
