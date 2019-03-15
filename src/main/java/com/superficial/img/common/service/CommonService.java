package com.superficial.img.common.service;

import com.google.zxing.WriterException;

import java.io.IOException;

public interface CommonService {


    void createQRCode(String code) throws IOException;

    void createBannerCode(String code) throws IOException, WriterException;
}
