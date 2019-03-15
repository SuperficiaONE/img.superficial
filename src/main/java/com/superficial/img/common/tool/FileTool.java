package com.superficial.img.common.tool;

public class FileTool {
    public static String getResourcesPath(){
        String path = FileTool.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        return path.substring(1)+"../resources/";
    }
}
