package com.atguigu.gulimall.gulimallsearch;

import com.atguigu.gulimall.gulimallsearch.config.GuliESConfig;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.search.aggregations.metrics.Avg;
import org.elasticsearch.search.aggregations.metrics.AvgAggregationBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
//import org.elasticsearch.search.aggregations.metrics.Avg;
//import org.elasticsearch.search.aggregations.metrics.AvgAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GulimallSearchApplicationTests {
    @Resource
    RestHighLevelClient client;
    @Test
    public void contextLoads() {
        System.out.println("********************************************client********************************************");
        System.out.println(client);
    }

    /**
     * ????????????:???bank?????????address?????????mill????????????????????????????????????????????????????????????
     * @throws IOException
     */
    @Test
    public void searchData() throws IOException {
        //1. ??????????????????
        SearchRequest searchRequest = new SearchRequest();

        //1.1???????????????
        searchRequest.indices("bank");
        //1.2?????????????????????
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchQuery("address","Mill"));

        //1.2.1)??????????????????????????????
        TermsAggregationBuilder ageAgg=AggregationBuilders.terms("ageAgg").field("age").size(2);
        sourceBuilder.aggregation(ageAgg);

        //1.2.2)??????????????????
        AvgAggregationBuilder ageAvg = AggregationBuilders.avg("ageAvg").field("age");
        sourceBuilder.aggregation(ageAvg);
        //1.2.3)??????????????????
        AvgAggregationBuilder balanceAvg = AggregationBuilders.avg("balanceAvg").field("balance");
        sourceBuilder.aggregation(balanceAvg);

        System.out.println("???????????????"+sourceBuilder);
        searchRequest.source(sourceBuilder);
        //2. ????????????
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println("???????????????"+searchResponse);

        //3. ????????????????????????Bean
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit searchHit : searchHits) {
            String sourceAsString = searchHit.getSourceAsString();
            Account account = JSON.parseObject(sourceAsString, Account.class);
            System.out.println(account);

        }

        //4. ??????????????????
        Aggregations aggregations = searchResponse.getAggregations();

        Terms ageAgg1 = aggregations.get("ageAgg");

        for (Terms.Bucket bucket : ageAgg1.getBuckets()) {
            String keyAsString = bucket.getKeyAsString();
            System.out.println("?????????"+keyAsString+" ==> "+bucket.getDocCount());
        }
        Avg ageAvg1 = aggregations.get("ageAvg");
        System.out.println("???????????????"+ageAvg1.getValue());

        Avg balanceAvg1 = aggregations.get("balanceAvg");
        System.out.println("???????????????"+balanceAvg1.getValue());


    }


    /**
     *
     * @throws IOException
     */
    @Test
    public void searchState() throws IOException {
        //1. ??????????????????
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//        sourceBuilder.query(QueryBuilders.termQuery("state", "AK"));
//        sourceBuilder.from(0);
//        sourceBuilder.size(5);
//        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

//        sourceBuilder.from(0);
//        sourceBuilder.size(1);
//        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
//        sourceBuilder.query(QueryBuilders.matchQuery("state", "AK"));

        QueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("state", "AK")
                .fuzziness(Fuzziness.AUTO)
                .prefixLength(3)
                .maxExpansions(10);
        sourceBuilder.query(matchQueryBuilder);

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("bank");
        searchRequest.source(sourceBuilder);
        //2. ????????????
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(searchResponse);

    }
    /**
     * ???????????????????????????ES
     * @throws IOException
     */
    @Test
    public void indexData() throws IOException {
        IndexRequest indexRequest = new IndexRequest ("users");

        User user = new User();
        user.setUserName("??????");
        user.setAge(20);
        user.setGender("???");
        String jsonString = JSON.toJSONString(user);
        //????????????????????????
        indexRequest.source(jsonString, XContentType.JSON);
        //?????????????????????????????????
        IndexResponse index = client.index(indexRequest, GuliESConfig.COMMON_OPTIONS);

        System.out.println(index);

    }




    @Data
    class User{
        private String userName;
        private String gender;
        private Integer age;
    }

    @Data
    static class Account {

        private int account_number;
        private int balance;
        private String firstname;
        private String lastname;
        private int age;
        private String gender;
        private String address;
        private String employer;
        private String email;
        private String city;
        private String state;

    }

}
