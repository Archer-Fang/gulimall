package com.atguigu.gulimall.gulimallsearch.service;

import com.atguigu.gulimall.gulimallsearch.vo.SearchParam;
import com.atguigu.gulimall.gulimallsearch.vo.SearchReult;

/**
 * @author Created by Fangzj
 * @data 2021/6/20 9:52
 **/
public interface MallSearchService {
    SearchReult search(SearchParam param);

}
