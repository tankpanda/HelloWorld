package com.hhd.es.service;

import com.google.gson.Gson;
import com.hhd.es.bean.InventoryMat;
import com.hhd.es.mapper.InventoryMatMapper;
import com.hhd.es.repository.InventoryMatRepository;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContent;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.FuzzyQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.elasticsearch.action.support.WriteRequest.RefreshPolicy.IMMEDIATE;

/**
 *
 * @author hengda.hu
 * @date 2020/11/24 15:37
 */
@Service
public class InventoryMatService {
    @Resource
    private InventoryMatMapper inventoryMatMapper;
    @Resource
    private RestHighLevelClient restHighLevelClient;
    @Resource
    private InventoryMatRepository inventoryMatRepository;

    private final String INDEX_NAME = "inventory_mat";

    public void save2Es() throws Exception {
        List<InventoryMat> inventoryMats = inventoryMatMapper.listAll();
        GetIndexRequest  getIndexRequest  = new GetIndexRequest(INDEX_NAME);
        boolean exists = restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        if (!exists) {
            CreateIndexRequest createIndexRequest = new CreateIndexRequest(INDEX_NAME);
            CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
            System.out.println(createIndexResponse);
        }

//        IndexRequest indexRequest = new IndexRequest(INDEX_NAME);
//        indexRequest.timeout(TimeValue.timeValueSeconds(1L));
//        inventoryMats.forEach(inventoryMat -> {
//            indexRequest.source(new Gson().toJson(inventoryMat), XContentType.JSON);
//            try {
//                IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });

//        GetResponse getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout(TimeValue.timeValueMinutes(1L));
        Gson gson = new Gson();
        inventoryMats.forEach(inventoryMat -> {
            bulkRequest.add(new IndexRequest(INDEX_NAME).id(inventoryMat.getId().toString()).source(gson.toJson(inventoryMat), XContentType.JSON));
        });
        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulkResponse.hasFailures());
    }

    public InventoryMat get(Long id) throws Exception {
        SearchRequest searchRequest = new SearchRequest(INDEX_NAME);
        SearchSourceBuilder ssb = new SearchSourceBuilder();
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("id", id);
        ssb.query(termQueryBuilder);
        ssb.timeout(TimeValue.timeValueMinutes(1L));
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHit hit = searchResponse.getHits().getAt(0);
        return new Gson().fromJson(hit.getSourceAsString(), InventoryMat.class);
    }

    public void delIndex() throws Exception {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("inventory_mat_with_repository");
        AcknowledgedResponse acknowledgedResponse = restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        System.out.println(acknowledgedResponse.isAcknowledged());
    }

    public InventoryMat getWithRepository(Long id) throws Exception {
        return inventoryMatRepository.findById(id).orElse(null);
    }

    public void saveWithRepository() throws Exception {
        inventoryMatRepository.saveAll(inventoryMatMapper.listAll());
    }

    public boolean existIndex(String index) throws Exception {
        return restHighLevelClient.indices().exists(new GetIndexRequest(index), RequestOptions.DEFAULT);
    }

}
