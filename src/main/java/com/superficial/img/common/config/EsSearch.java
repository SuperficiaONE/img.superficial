package com.superficial.img.common.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.*;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;
import io.searchbox.indices.mapping.GetMapping;
import io.searchbox.indices.mapping.PutMapping;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EsSearch {

  @Autowired
  private EsFactory esFactory;

  private Logger log = LoggerFactory.getLogger(this.getClass());
  private static final int defaultFrom = 0;
  private static final int defaultSize = 10;

  private static final String resultList = "resultList";
  private static final String totalSize = "totalSize";
  private static final String aggs = "aggs";

  /**
   * 创建索引
   */
  public boolean createIndex(String indexName) throws Exception {
    JestClient jestClient = esFactory.getJestClient();
    JestResult jr = jestClient.execute(new CreateIndex.Builder(indexName).build());
    return jr.isSucceeded();
  }

  /**
   * Put映射
   */
  public boolean createIndexMapping(String indexName, String typeName, String source)
      throws Exception {
    JestClient jestClient = esFactory.getJestClient();
    PutMapping putMapping = new PutMapping.Builder(indexName, typeName, source).build();
    JestResult jr = jestClient.execute(putMapping);
    return jr.isSucceeded();
  }

  /**
   * Get映射
   */
  public String getIndexMapping(String indexName, String typeName) throws Exception {
    JestClient jestClient = esFactory.getJestClient();
    GetMapping getMapping = new GetMapping.Builder().addIndex(indexName).addType(typeName).build();
    JestResult jr = jestClient.execute(getMapping);
    return jr.getJsonString();
  }

  /**
   * 索引文档
   */
  public boolean index(String indexName, String typeName, List<Object> objs) throws Exception {
    JestClient jestClient = esFactory.getJestClient();
    Bulk.Builder bulk = new Bulk.Builder().defaultIndex(indexName).defaultType(typeName);
    for (Object obj : objs) {
      Index index = new Index.Builder(obj).build();
      bulk.addAction(index);
    }
    BulkResult br = jestClient.execute(bulk.build());
    return br.isSucceeded();
  }

  /**
   * 搜索文档
   */
  public SearchResult search(String indexName, String typeName, String query) throws Exception {
    JestClient jestClient = esFactory.getJestClient();
    Search search = new Search.Builder(query)
        .addIndex(indexName)
        .addType(typeName)
        .build();
    return jestClient.execute(search);
  }

  /**
   * 搜索文档
   */
  public SearchResult search(String indexName, String typeName,
      SearchSourceBuilder searchSourceBuilder) throws Exception {
    String searchBuilderString = searchSourceBuilder.toString();
    log.info("indexName:" + indexName + ",typeName:" + typeName + "-" + searchBuilderString);
    return search(indexName, typeName, searchBuilderString);
  }


  /**
   * 搜索文档Rest
   */
  public SearchResult search(String indexName, String typeName, QueryBuilder queryBuilder,
      SortBuilder[] sortBuilders, AggregationBuilder[] aggregationBuilders, int from, int size)
      throws Exception {
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    if (null != queryBuilder) {
      searchSourceBuilder.query(queryBuilder);
    }
    if (null != sortBuilders && sortBuilders.length > 0) {
      for (SortBuilder sortBuilder : sortBuilders) {
        searchSourceBuilder.sort(sortBuilder);
      }
    }
    if (null != aggregationBuilders && aggregationBuilders.length > 0) {
      for (AggregationBuilder aggregationBuilder : aggregationBuilders) {
        searchSourceBuilder.aggregation(aggregationBuilder);
      }
    }
    searchSourceBuilder.size(size);
    searchSourceBuilder.from(from);
    return search(indexName, typeName, searchSourceBuilder);
  }

  public SearchResult search(String indexName, String typeName, QueryBuilder queryBuilder,
      SortBuilder[] sortBuilders, AggregationBuilder[] aggregationBuilders) throws Exception {
    return search(indexName, typeName, queryBuilder, sortBuilders, aggregationBuilders, defaultFrom,
        defaultSize);
  }

  /**
   * Count文档
   */
  public Double count(String indexName, String typeName, String query) throws Exception {
    JestClient jestClient = esFactory.getJestClient();
    Count count = new Count.Builder()
        .addIndex(indexName)
        .addType(typeName)
        .query(query)
        .build();
    CountResult results = jestClient.execute(count);
    return results.getCount();
  }

  /**
   * Get文档
   */
  public JestResult get(String indexName, String typeName, String id) throws Exception {
    JestClient jestClient = esFactory.getJestClient();
    Get get = new Get.Builder(indexName, id).type(typeName).build();
    return jestClient.execute(get);
  }

  /**
   * Delete索引
   */
  public boolean delete(String indexName) throws Exception {
    JestClient jestClient = esFactory.getJestClient();
    JestResult jr = jestClient.execute(new DeleteIndex.Builder(indexName).build());
    return jr.isSucceeded();
  }

  /**
   * Delete文档
   */
  public boolean delete(String indexName, String typeName, String id) throws Exception {
    JestClient jestClient = esFactory.getJestClient();
    DocumentResult dr = jestClient
        .execute(new Delete.Builder(id).index(indexName).type(typeName).build());
    return dr.isSucceeded();
  }

  public List<Map<String, Object>> fillSearchResult(SearchResult result) {
    List<Map<String, Object>> resultList = new ArrayList<>();
    fillResultList(resultList, result);
    return resultList;
  }

  public Map<String, Object> fillSearchResultMap(SearchResult result) {
    Map<String, Object> resultMap = new HashMap<>();
    List<Map<String, Object>> resultList = new ArrayList<>();
    fillResultList(resultList, result);
    resultMap.put(this.resultList, resultList);
    resultMap.put(this.totalSize, result.getTotal());
    resultMap.put(this.aggs, result.getAggregations());
//        List<TermsAggregation.Entry> teamBucketIt = result.getAggregations().getTermsAggregation("aggInfo").getBuckets();
//        if(!CommonUtil.isEmpty(teamBucketIt)){
//            for(TermsAggregation.Entry entry:teamBucketIt){
//               String key = entry.getKeyAsString();
//               long count = entry.getCount();
//               System.out.println(key+"数量"+count);
//            }
//        }
//        resultMap.put(CommonUtil.maxScore,result.getMaxScore());
    return resultMap;
  }

  public void fillResultList(List<Map<String, Object>> resultList, SearchResult result) {
    if (null == resultList) {
      resultList = new ArrayList<>();
    }
    List<SearchResult.Hit<Object, Void>> hits= null;
    try{
      hits = result.getHits(Object.class);
    }catch(Exception e){
      if(e instanceof NullPointerException){
        log.error("es没有查询到数据");
      }
    }
    if (null != hits && hits.size() > 0) {
      for (SearchResult.Hit<Object, Void> hit : hits) {
        Object object = hit.source;
        String objString = JSONObject.toJSONString(object);
        Map<String, Object> objectMap = JSON.parseObject(objString);
        resultList.add(objectMap);
      }
    }

  }

  public List<Map<String, Object>> fillSearchResult(String indexName, String typeName,
      QueryBuilder queryBuilder, SortBuilder[] sortBuilders,
      AggregationBuilder[] aggregationBuilders) throws Exception {
    SearchResult searchResult = search(indexName, typeName, queryBuilder, sortBuilders,
        aggregationBuilders);
    return fillSearchResult(searchResult);
  }

  public List<Map<String, Object>> fillSearchResult(String indexName, String typeName,
      QueryBuilder queryBuilder) throws Exception {
    return fillSearchResult(indexName, typeName, queryBuilder, null, null);
  }

  public List<Map<String, Object>> fillSearchResult(String indexName, String typeName,
      QueryBuilder queryBuilder, SortBuilder[] sortBuilders,
      AggregationBuilder[] aggregationBuilders, int from, int size) throws Exception {
    SearchResult searchResult = search(indexName, typeName, queryBuilder, sortBuilders,
        aggregationBuilders, from, size);
    return fillSearchResult(searchResult);
  }

  public Map<String, Object> fillSearchResultMap(String indexName, String typeName,
      QueryBuilder queryBuilder, SortBuilder[] sortBuilders,
      AggregationBuilder[] aggregationBuilders, int from, int size) throws Exception {
    SearchResult searchResult = search(indexName, typeName, queryBuilder, sortBuilders,
        aggregationBuilders, from, size);
    return fillSearchResultMap(searchResult);
  }

  /*public void addIndex(String indexName, String typeName) {
    try {
      JestClient client = esFactory.getJestClient();
      client.execute(new DeleteIndex.Builder(indexName).build());
      client.execute(new CreateIndex.Builder(indexName).build());
      Bulk.Builder bulk = new Bulk.Builder().defaultIndex(indexName).defaultType(typeName);
      String sql = "SELECT BRANCHID,DELETEFLAG,NOTE,PRODID,PRODNO,PRODBARCODE,PRODNAME,PRODLOCALNAME,PRODENGLISHNAME,PRODMEMORYCODE,PRODDOSAGEFORMNO,PRODDOSAGEFORMNOTEXT,PACKAGEUNIT,BIGPACKAGEQUANTITY,MIDPACKAGEQUANTITY,CHINESEDRUGYIELDLY,MANUFACTURE,MANUFACTUREABBREVIATION,MANUMEMORYCODE,PRODSPECIFICATION,PRESCRIPTIONCLASS,PRESCRIPTIONCLASSTEXT,PRODVALIDTIME FROM tb_merchandise limit 0,1205";
      List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
      for (Map<String, Object> map : list) {
        Index index = new Index.Builder(map).build();
        bulk.addAction(index);
      }
//            for (int i = 0; i < 1000; i++) {
//                User user = new User();
//                user.setId(i);
//                user.setName("测试" + i);
//                user.setBirth(new Date());
//                String info = "1001";
//                if (i % 100 == 0) {
//                    info = "";
//                    for (int j = 0; j < (i + 1); j++) {
//                        info += ("100" + j + " ");
//                    }
//                }
//                user.setInfo(info);
//                // Index index = new
//                // Index.Builder(user).index(indexName).type("typetest").build();
//                // client.execute(index);
//                Index index = new Index.Builder(user).build();
//                bulk.addAction(index);
//            }
      BulkResult br = client.execute(bulk.build());
      System.out.println(br.isSucceeded());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }*/
//
//    public void test(String indexName, String typeName) {
//        try {
//            JestClient client = esFactory.getJestClient();
//            client.execute(new DeleteIndex.Builder(indexName).build());
//            client.execute(new CreateIndex.Builder(indexName).build());
//            // add doc
//            Bulk.Builder bulk = new Bulk.Builder().defaultIndex(indexName).defaultType(typeName);
//            for (int i = 0; i < 1000; i++) {
//                User user = new User();
//                user.setId(i);
//                user.setName("测试" + i);
//                user.setBirth(new Date());
//                String info = "1001";
//                if (i % 100 == 0) {
//                    info = "";
//                    for (int j = 0; j < (i + 1); j++) {
//                        info += ("100" + j + " ");
//                    }
//                }
//                user.setInfo(info);
//                // Index index = new
//                // Index.Builder(user).index(indexName).type("typetest").build();
//                // client.execute(index);
//                Index index = new Index.Builder(user).build();
//                bulk.addAction(index);
//            }
//            BulkResult br = client.execute(bulk.build());
//            System.out.println(br.isSucceeded());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
