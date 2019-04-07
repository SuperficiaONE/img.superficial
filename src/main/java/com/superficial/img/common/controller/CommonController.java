package com.superficial.img.common.controller;

import com.google.zxing.WriterException;
import com.superficial.img.common.service.CommonService;
import com.superficial.img.common.tool.CommonUtil;
import com.superficial.img.common.tool.EmailTool;
import com.superficial.img.common.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.io.IOException;

@Slf4j
@Controller
public class CommonController {

    @Autowired
    private CommonService commonService;


    @RequestMapping("/api/common/getQRCode")
    @ResponseBody
    public void getCode(String code) throws IOException {

        try {
            log.info("生成二维码:{}", code);
            if (CommonUtil.isEmpty(code)) {
                code = "http://www.baidu.com";
            }
            commonService.createQRCode(code);
        }catch (Exception e){
            log.error("获取banner验证码出现异常:",e);

        }
    }

    @RequestMapping("/api/common/getCenterQRCode")
    @ResponseBody
    public void getCenterQRCode(String code) throws IOException, WriterException {
       try {
           if (CommonUtil.isEmpty(code)) {
               code = "这是一个带icon图片的二维码";
           }
           log.info("生成二维码:{}", code);
           commonService.createBannerCode(code);
       }catch (Exception e){
           log.error("获取banner验证码出现异常:",e);

       }

    }

    @RequestMapping("/api/common/sendEmail")
    @ResponseBody
    public ResultVO sendEmail() {
        try {
            String code  = "123567";
            EmailTool.sendMessage("快看 这就是生活的注册码","验证码时间限制10分钟抓紧：您的验证码是："+code);
        }catch (Exception e){
            log.error("发送邮件出现异常:",e);
            return ResultVO.newError(e.getMessage());
        }
       return ResultVO.newSuccess("发送成功");
    }
}
