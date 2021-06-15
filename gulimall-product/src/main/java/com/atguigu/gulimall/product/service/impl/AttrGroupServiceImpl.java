package com.atguigu.gulimall.product.service.impl;

import com.alibaba.nacos.client.utils.StringUtils;
import com.atguigu.gulimall.product.entity.AttrEntity;
import com.atguigu.gulimall.product.service.AttrService;
import com.atguigu.gulimall.product.vo.AttrGroupWithAttrVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;

import com.atguigu.gulimall.product.dao.AttrGroupDao;
import com.atguigu.gulimall.product.entity.AttrGroupEntity;
import com.atguigu.gulimall.product.service.AttrGroupService;

import javax.annotation.Resource;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {
    @Resource
    AttrService attrService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<AttrGroupEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Long cateLogId) {

        String queryKey = (String) params.get("key");

        QueryWrapper<AttrGroupEntity> queryWrapper = new QueryWrapper<AttrGroupEntity>();
        //catelog_id == 0时，按照attr_group_id和attr_group_name进行模糊查询，否则要带上catelog_id
        if(cateLogId != 0){
            queryWrapper.eq("catelog_id", cateLogId);
        }
        //select * from pms_attr_group WHERE catelog_id = 1 AND (attr_group_id =key or attr_group_name LIKE '%key%');
        if (StringUtils.isNotEmpty(queryKey)) {

            queryWrapper.and((param) -> {
                param.eq("attr_group_id", queryKey)
                        .or()
                        .like("attr_group_name", queryKey);
            });
        }

        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

    @Override
    public List<AttrGroupWithAttrVo> getAttrGroupWithAttrByCatelogId(Long catId) {
        List<AttrGroupEntity> attrGroupEntities = baseMapper.selectList(new QueryWrapper<AttrGroupEntity>().eq("catelog_id", catId));
        List<AttrGroupWithAttrVo> collect = attrGroupEntities.stream().map(group -> {
                    AttrGroupWithAttrVo vo = new AttrGroupWithAttrVo();
                    BeanUtils.copyProperties(group, vo);
                    List<AttrEntity> relationAttr = attrService.getRelationAttr(group.getAttrGroupId());
                    vo.setAttrs(relationAttr);
                    return vo;
                }
        ).collect(Collectors.toList());
        return collect;
    }
}