package com.atguigu.gulimall.product;

import com.atguigu.gulimall.product.entity.BrandEntity;
import com.atguigu.gulimall.product.service.BrandService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GulimallProductApplicationTests {
    @Autowired
    BrandService brandService;
    @Test
    void contextLoads() {
        //Insert
//        BrandEntity brandEntity = new BrandEntity();
//        brandEntity.setDescript("new os");
//        brandEntity.setName("Harmony");
//        brandService.save(brandEntity);
//        System.out.println("保存成功");

        //update
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setBrandId(6L);
        brandEntity.setDescript("修改");
        brandService.updateById(brandEntity);

    }

}
