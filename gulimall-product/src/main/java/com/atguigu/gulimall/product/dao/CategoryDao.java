package com.atguigu.gulimall.product.dao;

import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author fzj
 * @email 1091053002@qq.com
 * @date 2021-06-02 20:38:24
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
