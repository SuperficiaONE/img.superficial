package com.superficial.img.api.esSearch.service.impl;

import com.superficial.img.common.config.EsSearch;
import com.superficial.img.api.esSearch.service.EsSearchService;
import io.searchbox.core.SearchResult;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class EsSearchServiceImpl implements EsSearchService {
    @Autowired
    private EsSearch esSearch;

    @Override
    public Object getRs(Integer type,Integer from ,Integer size) throws Exception {
        //查找
        BoolQueryBuilder mainBuilder = QueryBuilders.boolQuery();
        mainBuilder.filter(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("prod_name","复方")));
        // 排序

        SortBuilder sortBuilder = SortBuilders.fieldSort("create_at").order(SortOrder.ASC);
        SortBuilder[] sortBuilders = new SortBuilder[1];

        switch (type) {
            case 1:
                sortBuilders[0] = sortBuilder;
                break;
            default:
                sortBuilders = null;

        }

        //  mainBuilder.must(QueryBuilders.matchAllQuery());
        //聚合
        // 分页
        SearchResult search = esSearch.search("b2b", "prod", mainBuilder, sortBuilders, null, (from-1)*size
                , size);
        Map<String,Object> map = new HashMap<>(4);
        map.put("size",size);
        map.put("curr",from);
        map.put("total",search.getTotal());
        map.put("list",esSearch.fillSearchResult(search));
        return map ;
    }
}
