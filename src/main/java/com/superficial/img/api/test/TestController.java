package com.superficial.img.api.test;

import com.superficial.img.common.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
