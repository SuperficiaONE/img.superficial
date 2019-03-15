package com.superficial.img.common.service.impl;

import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeReader;
import com.superficial.img.common.service.CommonService;
import com.superficial.img.common.tool.FileTool;
import com.superficial.img.common.tool.HttpUtils;
import com.superficial.img.common.tool.QrCodeCreateUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
}
