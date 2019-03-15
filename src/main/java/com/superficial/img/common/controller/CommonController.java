package com.superficial.img.common.controller;

import cn.hutool.http.HttpUtil;
import com.google.zxing.WriterException;
import com.sun.deploy.net.HttpUtils;
import com.superficial.img.common.service.CommonService;
import com.superficial.img.common.tool.CommonUtil;
import com.google.zxing.qrcode.QRCodeReader;
import com.superficial.img.common.tool.QrCodeCreateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Slf4j
@Controller
public class CommonController {

    @Autowired
    private CommonService commonService;

    @RequestMapping("/common.htm")
    public String getIndex() {
        log.info("进入首页");
        return "/common/common";
    }

    @RequestMapping("/common/getQRCode")
    @ResponseBody
    public void getCode(String code) throws IOException {
        log.info("生成二维码:{}", code);
        if (CommonUtil.isEmpty(code)) {
            code = "http://www.baidu.com";
        }
       commonService.createQRCode(code);
    }

    @RequestMapping("/common/getCenterQRCode")
    @ResponseBody
    public void getCenterQRCode(String code, HttpServletResponse response) throws IOException, WriterException {
        if (CommonUtil.isEmpty(code)) {
            code = "这是一个带icon图片的二维码";
        }
        log.info("生成二维码:{}", code);
        commonService.createBannerCode(code);
    }
}
