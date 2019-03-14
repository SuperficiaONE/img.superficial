package com.superficial.img.common.controller;

import com.google.zxing.WriterException;
import com.superficial.img.common.tool.CommonUtil;
import com.google.zxing.qrcode.QRCodeReader;
import com.superficial.img.common.tool.QrCodeCreateUtil;
import lombok.extern.slf4j.Slf4j;
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
    @RequestMapping("/common.htm")
    public String getIndex() {
        log.info("进入首页");
        return "/common/common";
    }

    @RequestMapping("/common/getQRCode")
    @ResponseBody
    public void getCode(String code, HttpServletResponse response) throws IOException, WriterException {
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        log.info("生成二维码:{}", code);
        if (CommonUtil.isEmpty(code)) {
            code = "http://www.baidu.com";
        }
        QrCodeCreateUtil.createQrCode(response.getOutputStream(), code, 200, "jpeg");

    }

    @RequestMapping("/common/getCenterQRCode")
    @ResponseBody
    public void getCenterQRCode(String code, HttpServletResponse response) throws IOException, WriterException {
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        log.info("生成二维码:{}", code);
        if (CommonUtil.isEmpty(code)) {
            code = "这是一个带icon图片的二维码";
        }
        //QrCodeCreateUtil.createQrCode(response.getOutputStream(),code,200,"jpeg");

       // String path = "";
       // File directory = new File("");
        // 参数为空
       String path = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
       path = path.substring(1)+"../resources/static/home/center.jpg";

        BufferedImage bufferedImage = QrCodeCreateUtil.genBarcode(code, 200, 200, path);
        ImageIO.write(bufferedImage,"JPEG",response.getOutputStream());


    }
}
