package com.atguigu.gulimall.ware.dao;

import com.atguigu.gulimall.ware.entity.WareSkuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * εεεΊε­
 * 
 * @author fzj
 * @email 1091053002@qq.com
 * @date 2021-06-02 22:24:56
 */
@Mapper
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {

    void updateStock(Long skuId, Long wareId, Integer skuNum);

    Long getSkuStock(Long item);

}
