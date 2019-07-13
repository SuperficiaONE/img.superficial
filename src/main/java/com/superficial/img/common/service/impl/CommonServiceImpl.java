package com.superficial.img.common.service.impl;

import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeReader;
import com.superficial.img.common.service.CommonService;
import com.superficial.img.common.tool.FileTool;
import com.superficial.img.common.tool.HttpUtils;
import com.superficial.img.common.tool.QrCodeCreateUtil;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CommonServiceImpl implements CommonService {
    @Value("${IMG.QRCENTER_PATH}")
    public  String QRCENTER_PATH;

    @Override
    public void createQRCode(String code) throws IOException {
        HttpServletResponse response = HttpUtils.getByImageHeader();
        QrCodeCreateUtil.createQrCode(response.getOutputStream(), code, 200, "jpeg");
    }

    @Override
    public void createBannerCode(String code) throws IOException, WriterException {
        HttpServletResponse response = HttpUtils.getByImageHeader();
        String resourcesPath = FileTool.getResourcesPath();
        String path = resourcesPath+QRCENTER_PATH;
        QrCodeCreateUtil.createBannerCode(response.getOutputStream(),code,200,path);
    }

    @Override
    public Map<String, Object> createBannerCodeToStr(String code) throws IOException, WriterException {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        String resourcesPath = FileTool.getResourcesPath();
        String path = resourcesPath+QRCENTER_PATH;
        QrCodeCreateUtil.createBannerCode(byteArrayOutputStream, code, 200, path);
        Map<String ,Object> map = new HashMap<>();
        map.put("imgBase64", Base64.encodeBase64String(byteArrayOutputStream.toByteArray()));
        return map;
    }
}
