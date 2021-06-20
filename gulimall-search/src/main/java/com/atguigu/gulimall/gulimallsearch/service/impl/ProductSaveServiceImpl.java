package com.atguigu.gulimall.gulimallsearch.service.impl;

import com.alibaba.fastjson.JSON;
import com.atguigu.common.to.es.SkuEsModel;
import com.atguigu.gulimall.gulimallsearch.config.GuliESConfig;
import com.atguigu.gulimall.gulimallsearch.constant.EsConstant;
import com.atguigu.gulimall.gulimallsearch.service.ProductSaveService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Created by Fangzj
 * @data 2021/6/19 21:14
 **/
@Service
@Slf4j
public class ProductSaveServiceImpl implements ProductSaveService {
    @Resource
    RestHighLevelClient client;

    @Override
    public boolean productStatusUp(List<SkuEsModel> skuEsModels) throws IOException {
        //1.在es中建立索引，建立号映射关系（doc/json/product-mapping.json）

        //2. 在ES中保存这些数据
        BulkRequest bulkRequest = new BulkRequest();
        for (SkuEsModel skuEsModel : skuEsModels) {
            //构造保存请求
            IndexRequest indexRequest = new IndexRequest(EsConstant.PRODUCT_INDEX);
            indexRequest.id(skuEsModel.getSkuId().toString());
            String jsonString = JSON.toJSONString(skuEsModel);
            indexRequest.source(jsonString, XContentType.JSON);
            bulkRequest.add(indexRequest);
        }


        BulkResponse bulk = client.bulk(bulkRequest, GuliESConfig.COMMON_OPTIONS);

        // 如果批量错误
        boolean hasFailures = bulk.hasFailures();

        if(hasFailures){
            log.error("商品上架错误：{}",bulk.buildFailureMessage());
        }
//            List<String> collect = Arrays.stream(bulk.ge).map(item -> item.getId()).collect(Collectors.toList());


        return hasFailures;
    }
}
