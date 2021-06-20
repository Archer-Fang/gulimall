package com.atguigu.gulimall.gulimallsearch.controller;

/**
 * @author Created by Fangzj
 * @data 2021/6/20 9:51
 **/
import com.atguigu.gulimall.gulimallsearch.service.MallSearchService;
import com.atguigu.gulimall.gulimallsearch.vo.SearchParam;
import com.atguigu.gulimall.gulimallsearch.vo.SearchReult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

@Controller
public class SearchController {

    @Resource
    MallSearchService mallSearchService;

    @GetMapping("/list.html")
    public String listPage(SearchParam param, Model model){
        //1. 根据页面传递过来的查询参数，到ES中检索商品
        SearchReult result=mallSearchService.search(param);
        model.addAttribute("result",result);
        return "list";
    }
}