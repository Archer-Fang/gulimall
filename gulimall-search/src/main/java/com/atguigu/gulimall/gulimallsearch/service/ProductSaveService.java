package com.atguigu.gulimall.gulimallsearch.service;

import com.atguigu.common.to.es.SkuEsModel;

import java.io.IOException;
import java.util.List;

/**
 * @author Created by Fangzj
 * @data 2021/6/19 21:13
 **/
public interface ProductSaveService {
    boolean productStatusUp(List<SkuEsModel> skuEsModels) throws IOException;

}
