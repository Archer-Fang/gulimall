package com.atguigu.gulimall.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.atguigu.gulimall.product.entity.ProductAttrValueEntity;
import com.atguigu.gulimall.product.service.ProductAttrValueService;
import com.atguigu.gulimall.product.vo.AttrResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.atguigu.gulimall.product.entity.AttrEntity;
import com.atguigu.gulimall.product.service.AttrService;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.R;



/**
 * 商品属性
 *
 * @author fzj
 * @email 1091053002@qq.com
 * @date 2021-06-02 20:38:25
 */
@RestController
@RequestMapping("product/attr")
public class AttrController {
    @Autowired
    private AttrService attrService;
    @Autowired
    private ProductAttrValueService productAttrValueService;


    /**
     * 信息
     * 功能：查询属性详情
     * API：https://easydoc.xyz/doc/75716633/ZUqEdvA4/7C3tMIuF
     */
    @RequestMapping("/info/{attrId}")
    public R info(@PathVariable("attrId") Long attrId){
        AttrResponseVo responseVo=attrService.getAttrInfo(attrId);

        return R.ok().put("attr", responseVo);
    }

    /* 功能：根据spuId信息查询出对应的规格参数信息
       API：https://easydoc.xyz/doc/75716633/ZUqEdvA4/GhhJhkg7
     */
    @GetMapping("/base/listforspu/{spuId}")
    public R listForSpu(@PathVariable("spuId") Long spuId){
        List<ProductAttrValueEntity> entityList=productAttrValueService.baseAttrListForSpu(spuId);
        return  R.ok().put("data",entityList);
    };
    /**
     * 功能：修改商品规格
     * API：https://easydoc.xyz/doc/75716633/ZUqEdvA4/GhnJ0L85
     * @param spuId
     * @param entities
     * @return
     */
    @PostMapping("/update/{spuId}")
    public R update(@PathVariable("spuId") Long spuId,@RequestBody List<ProductAttrValueEntity> entities){
        productAttrValueService.updateSpuAttr(spuId,entities);
        return R.ok();
    }
    /**
     * 列表
     */
    @RequestMapping("/list")
        public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attrService.queryPage(params);

        return R.ok().put("page", page);
    }

    @RequestMapping("/{attrType}/list/{catelogId}")
    public R infoCatelog(@RequestParam Map<String, Object> params,
                         @PathVariable("catelogId") long catelogId,
                         @PathVariable("attrType") String attrType) {
        PageUtils page = attrService.queryPage(params,catelogId,attrType);

        return R.ok().put("page", page);
    }
//    /**
//     * 信息
//     */
//    @RequestMapping("/info/{attrId}")
//    public R info(@PathVariable("attrId") Long attrId){
//        AttrEntity attr = attrService.getById(attrId);
//
//        return R.ok().put("attr", attr);
//    }

    /**
     * 保存
     */
    @RequestMapping("/save")
        public R save(@RequestBody AttrEntity attr){
		attrService.save(attr);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
        public R update(@RequestBody AttrEntity attr){
		attrService.updateById(attr);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
        public R delete(@RequestBody Long[] attrIds){
		attrService.removeByIds(Arrays.asList(attrIds));

        return R.ok();
    }

}
