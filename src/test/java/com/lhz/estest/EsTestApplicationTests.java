package com.lhz.estest;

import com.lhz.estest.dao.ElasticRepository;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.Date;



@ExtendWith(SpringExtension.class)
@SpringBootTest
class EsTestApplicationTests {
    @Autowired
    private ElasticRepository elasticRepository;

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

}
