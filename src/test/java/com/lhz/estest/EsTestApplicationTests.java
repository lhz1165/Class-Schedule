package com.lhz.estest;

import com.lhz.estest.dao.ElasticRepository;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.ScriptService;
import org.elasticsearch.script.mustache.SearchTemplateRequestBuilder;
import org.elasticsearch.search.SearchHit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.Date;


import  org.elasticsearch.action.search.SearchType;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class EsTestApplicationTests {


    @Autowired
    private TransportClient client;

    @Test
    void contextLoads() throws IOException {
        client.prepareIndex("twitter", "_doc", "1")
                .setSource(XContentFactory.jsonBuilder()
                        .startObject()
                        .field("user", "kimchy")
                        .field("postDate", new Date())
                        .field("message", "trying out Elasticsearch")
                        .endObject()
                )
                .get();
    }
    @Test
    void search() throws IOException{
        // 这只查询的字段和字段值，这里会进行分词查询，相当于match
        QueryBuilder builder = QueryBuilders.matchQuery("user","kimchy");
//        SearchResponse searchResponse = client.prepareSearch("twitter")
//                .setSearchType(SearchType.QUERY_THEN_FETCH)
//                .setQuery(builder)
//                .setSize(10)
//                .get();
        SearchRequestBuilder srb = client.prepareSearch("twitter")
                .setSearchType(SearchType.QUERY_THEN_FETCH)
                .setQuery(builder)
                .setSize(10);
        SearchResponse searchResponse = srb.execute().actionGet();
        SearchHit[] searchHits = searchResponse.getHits().getHits();
        for(SearchHit searchHit : searchHits){
            System.out.println(searchHit.getSourceAsString());
        }
    }

    @Test
    public void test6() throws Exception{
        XContentBuilder doc = XContentFactory.jsonBuilder().startObject()
                .field("user", "adsa kimchy hello")
                .field("postDate", new Date())
                .field("message", "trying out")
                .endObject();
        IndexResponse response = client.prepareIndex("twitter", "_doc","3").setSource(doc).get();
        System.out.println(response.status());
    }


}
