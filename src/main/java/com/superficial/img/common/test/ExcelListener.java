package com.superficial.img.common.test;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExcelListener  extends AnalysisEventListener<ProdscopeNoConfig> {
    @Override
    public void invoke(ProdscopeNoConfig data, AnalysisContext context) {
        prodscopeNoConfigs.add(data) ;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
    public List<ProdscopeNoConfig> getDatas() {
        return prodscopeNoConfigs;
    }
    private  List<ProdscopeNoConfig> prodscopeNoConfigs =  Collections.synchronizedList(new ArrayList<>());
}
