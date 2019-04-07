package com.superficial.img.common.tool;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


    public  static ObjectMapper getObjectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
      //  SimpleDateFormat sdf = new SimpleDateFormat("yyyyHH");
     //   objectMapper.setDateFormat(sdf);
        SimpleModule longSimpleModule = new SimpleModule();
        longSimpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        longSimpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        objectMapper.registerModule(longSimpleModule);
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(MapperFeature.PROPAGATE_TRANSIENT_MARKER, true);
        objectMapper.writerWithDefaultPrettyPrinter();
        return objectMapper;
    }

    private static Pattern humpPattern = Pattern.compile("[A-Z]");
    private static Pattern linePattern = Pattern.compile("_(\\w)");

    /**
     * 下划线转驼峰
     * @param str
     * @return
     */
    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 驼峰转下划线
     * @param str
     * @return
     */
    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

}
