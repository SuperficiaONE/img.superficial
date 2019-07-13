package com.superficial.img.common.service;

import com.google.zxing.WriterException;

import java.io.IOException;
import java.util.Map;

public interface CommonService {


    void createQRCode(String code) throws IOException;

    void createBannerCode(String code) throws IOException, WriterException;

    Map<String, Object> createBannerCodeToStr(String code) throws IOException, WriterException;
}
