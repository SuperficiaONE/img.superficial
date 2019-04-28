package com.superficial.img.api.upload.controller;

import com.superficial.img.common.tool.FileTool;
import com.superficial.img.common.vo.LayResultVO;
import com.superficial.img.common.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class UploadController {
    @PostMapping("/api/img/upload")
    public Object uploadImg(@RequestParam(name = "file") MultipartFile file){
        try {
            String filePath=FileTool.uploadFile(file);
            Map<String,Object> map = new HashMap<>(2);
            map.put("link","/"+filePath);
            map.put("title","图片");
            return map;
        }catch (Exception e){
            log.error("上传图片发生了异常",e);
            return  LayResultVO.newError(e.getMessage());
        }
    }

    @PostMapping("/api/layImg/upload")
    public LayResultVO uploadLayUIImg(@RequestParam(name = "file") MultipartFile file){
        try {
            String filePath=FileTool.uploadFile(file);
            Map<String,Object> map = new HashMap<>(2);
            map.put("src","/"+filePath);
            map.put("title","图片");
            return LayResultVO.newSuccess("上传成功",map);
        }catch (Exception e){
            log.error("上传图片发生了异常",e);
            return  LayResultVO.newError(e.getMessage());
        }
    }
}
