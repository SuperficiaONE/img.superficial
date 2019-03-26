package com.superficial.img.esSearch.service.impl;

import com.superficial.img.common.config.EsSearch;
import com.superficial.img.esSearch.service.EsSearchService;
import io.searchbox.core.SearchResult;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class EsSearchServiceImpl implements EsSearchService {
    @Autowired
    private EsSearch esSearch;

    @Override
    public  Object getRs() throws Exception {
        //聚合分类
        BoolQueryBuilder mainBuilder = QueryBuilders.boolQuery();
      mainBuilder.should(QueryBuilders.matchQuery("prod_name", "氨").boost(5L));
      //  mainBuilder.must(QueryBuilders.matchAllQuery());
        //查找
        //排序
        // 分页
        SearchResult search = esSearch.search("b2b", "prod", mainBuilder, null, null, 0, 10);
        return esSearch.fillSearchResult(search);
    }
}
