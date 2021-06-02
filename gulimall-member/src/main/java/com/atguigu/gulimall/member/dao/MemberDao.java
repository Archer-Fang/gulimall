package com.atguigu.gulimall.member.dao;

import com.atguigu.gulimall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author fzj
 * @email 1091053002@qq.com
 * @date 2021-06-02 22:21:42
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
