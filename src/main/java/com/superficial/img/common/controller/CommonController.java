package com.superficial.img.common.controller;

import com.google.zxing.WriterException;
import com.superficial.img.common.service.CommonService;
import com.superficial.img.common.tool.CommonUtil;
import com.superficial.img.common.tool.EmailTool;
import com.superficial.img.common.tool.JwtTool;
import com.superficial.img.common.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class CommonController {

    @Autowired
    private CommonService commonService;

    @RequestMapping("/page/test/token.htm")
    public String getTokenPage() throws IOException {
        return "/page/test/token";
    }


    @RequestMapping("/api/test/getToken")
    @ResponseBody
    public ResultVO getToken(Long userAgentId,String dataId,String loginName,String tokenVersion,String userType,String stationId,String linkName) throws IOException {
        try {
            Map<String,String> map =new HashMap<>();
            map.put("token", "");
            return  ResultVO.newSuccess("获取token成功",map);
        }catch (Exception e){
            log.error("获取banner验证码出现异常:",e);
            return ResultVO.newError("获取banner验证码出现异常:"+e.getMessage());
        }
    }


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

    /**
     *
     */
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

    /**
     *  测试创建带有中心图片的经营简码
     */
    @PostMapping("/api/post/getCenterQRCode")
    @ResponseBody
    public ResultVO getCenterQRCodeByPost(String code) {
        try {
            if (CommonUtil.isEmpty(code)) {
                code = "这是一个带icon图片的二维码";
            }
            log.info("生成二维码:{}", code);
            Map<String, Object> base64 = commonService.createBannerCodeToStr(code);
            return ResultVO.newSuccess(base64);
        } catch (Exception e) {
            log.error("获取banner验证码出现异常:", e);
            return ResultVO.newError("异常");
        }

    }

    /**
     *   测试发送邮件
     */
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
