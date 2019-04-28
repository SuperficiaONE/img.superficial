package com.superficial.img.common.tool;

import com.baomidou.mybatisplus.toolkit.IdWorker;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;

public class FileTool {
    public static String getResourcesPath(){
        String path = FileTool.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        return path.substring(1)+"../resources/";
    }
    public  static String uploadFile(MultipartFile file){
        if(file==null){
            return null;
        }
        try {
            String basePath= getResourcesPath() ;
            String filePath ="static/uploadImg/"+CommonUtil.getDataPath(new Date());
            String dirPath = basePath+filePath;
            String fileName = IdWorker.getIdStr()+".png";
            filePath+=fileName;
            file.getInputStream();
            File dirFile = new File(dirPath);
            File baseFile = new File(dirPath+fileName);
           if(!dirFile.exists()){
               dirFile.mkdirs();
           }
           if(!baseFile.exists()){
               baseFile.createNewFile();
           }
           saveFileFromInputStream(file.getInputStream(),baseFile);
           return filePath;
        }catch (Exception e){
            return null;
        }
    }
    public static void saveFileFromInputStream(InputStream stream, File file) throws Exception {
        FileOutputStream fs = null;
        try {
            fs = new FileOutputStream(file);
            byte[] buffer = new byte[1024 * 1024];
            int byteread = 0;
            while ((byteread = stream.read(buffer)) != -1) {
                fs.write(buffer, 0, byteread);
                fs.flush();
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (fs != null) {
                fs.close();
            }
        }
    }
}
