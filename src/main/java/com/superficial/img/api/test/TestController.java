package com.superficial.img.api.test;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.Sheet;
import com.google.common.collect.Lists;
import com.superficial.img.common.test.ExcelListener;
import com.superficial.img.common.test.ProdscopeNoConfig;
import com.superficial.img.common.tool.FileTool;
import com.superficial.img.common.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class TestController {

    @Autowired
    private  TestService testService;
    @RequestMapping("/test/map")
    public ResultVO testMap(){
        Thread myThread = new ConcurrentMapThread();
        Thread myThread1 = new ConcurrentMapThread();
        Thread thread = new Thread(myThread);
        Thread thread1 = new Thread(myThread1);
        thread.start();
        thread1.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("===============");
        TestService.printConcurrentMap();
        return ResultVO.newSuccess("执行成功");
    }



    @GetMapping("/api/test/getDelete")
    public ResultVO getDeleteSql(){
        try {
            //这个存放删除经营简码配置表的sql
            List<String > list = Lists.newArrayList();
            //这个存放删除经营简码配置申请表的sql
            List<String > list2 = Lists.newArrayList();
            //获取需要处理的数据
            String path = FileTool.getResourcesPath()+"11.xls";
            List<ProdscopeNoConfig> prodscopeNoConfigs = Lists.newArrayList();
            InputStream inputStream = new FileInputStream(path );
            ExcelListener excelListener = new ExcelListener();
            ExcelReader excelReader = new ExcelReader(inputStream,null ,excelListener);
            excelReader.read(new Sheet(0,1,ProdscopeNoConfig.class));
            prodscopeNoConfigs = excelListener.getDatas();
            Map<String, List<ProdscopeNoConfig>> collect = prodscopeNoConfigs.stream().collect(Collectors.groupingBy(ProdscopeNoConfig::getDataId));
            for ( List<ProdscopeNoConfig> l : collect.values()) {
                if(l.size()==3){
                    log.info("data_id:"+l.get(0).getDataId()+"  集合："+l);
                    for (ProdscopeNoConfig p:l) {
                        if(p.getProdscopenoId().equals("005002")||p.getProdscopenoId().equals("005001")){
                            list.add("delete from tb_prodscopeno_config where data_id='"+p.getDataId()+"'"
                            +" and prodscopeno_id='"+p.getProdscopenoId()+"';");
                            list2.add("delete from tb_prodscopeno_config_apply where data_id='"+p.getCustApplyId()+"'"
                                    +" and prodscopeno_id='"+p.getProdscopenoId()+"';");
                        }
                    }
                }
            }
            list.addAll(list2);

            return ResultVO.newSuccess(list);

        }catch (Exception e){
            log.error("出现异常",e);
            return ResultVO.newError(e.getMessage());
        }
    }
}
