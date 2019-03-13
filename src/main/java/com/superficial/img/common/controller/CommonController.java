package com.superficial.img.common.controller;

import com.google.zxing.WriterException;
import com.superficial.img.common.tool.QrCodeCreateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Slf4j
@Controller
public class CommonController {
    @RequestMapping("/common.htm")
    public String getIndex(){
        log.info("进入首页");
        return "/common/common";
    }

    @RequestMapping("/common/getQRCode")
    @ResponseBody
    public void getCode(String code , HttpServletResponse response) throws IOException, WriterException {
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        log.info("生成二维码:{}",code);
        QrCodeCreateUtil.createQrCode(response.getOutputStream(),code,200,"jpeg");

    }
}
