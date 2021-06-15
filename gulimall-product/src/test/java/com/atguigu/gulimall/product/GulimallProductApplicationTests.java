package com.atguigu.gulimall.product;

import com.atguigu.gulimall.product.entity.BrandEntity;
import com.atguigu.gulimall.product.service.BrandService;
import com.atguigu.gulimall.product.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class GulimallProductApplicationTests {
    @Autowired
    BrandService brandService;
    @Autowired
    CategoryService categoryService;

    @Test
    public void testCategoryPath() {
        Long[] paths= categoryService.findCateLogPath(225L);
        log.info(String.valueOf(Arrays.asList(paths)));

    }
    @Test
    public void contextLoads() {
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
