package com.superficial.img.common.tool;

import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

public class CommonUtil {
    private CommonUtil(){

    }
    public static Boolean isEmpty(String str){
        return StringUtils.isEmpty(str);
    }

    public static Boolean isBlank(String str){
        return StringUtils.isBlank(str);
    }

    public static Boolean isNotBlank(String str){
        return StringUtils.isNotBlank(str);
    }

    public static Boolean isEmpty(Object o){
        return o == null;
    }

    public static Boolean isEmpty(Collection collection){
        if(collection==null){
            return true;
        }
        return collection.isEmpty();
    }

}
