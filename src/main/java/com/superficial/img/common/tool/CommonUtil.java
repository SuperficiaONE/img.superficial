package com.superficial.img.common.tool;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {
    private CommonUtil(){

    }
    public static Boolean isEmpty(String str){
        return StringUtils.isEmpty(str)||StringUtils.isBlank(str);
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
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
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

    public static Map ConvertObjToMap(Object obj){
        Map<String,Object> reMap = new HashMap<String,Object>();
        if (obj == null) {
            return null;
        }
        Field[] fields = obj.getClass().getDeclaredFields();
        try {
            for(int i=0;i<fields.length;i++){
                try {
                    Field f = obj.getClass().getDeclaredField(fields[i].getName());
                    f.setAccessible(true);
                    Object o = f.get(obj);
                    reMap.put(fields[i].getName(), o);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return reMap;
    }

   public static  String getDataPath(Date date){
        if (date==null){
            return "";
        }
        SimpleDateFormat smf = new SimpleDateFormat("yyyy/MM/dd/");
        return  smf.format(date);
   }
    public static int asInteger(Object o) {
        return asInteger(o, 0);
    }

    public static int asInteger(Object o, int df) {
        if (o == null) {
            return df;
        }
        if (o instanceof Integer) {
            return (Integer) o;
        }
        if (o instanceof Byte) {
            return (Byte) o;
        }
        if (o instanceof Short) {
            return (Short) o;
        }
        if (o instanceof Double) {
            return ((Double) o).intValue();
        }
        if (o instanceof Float) {
            return ((Float) o).intValue();
        }
        if (o instanceof BigInteger) {
            return ((BigInteger) o).intValue();
        }
        if (o instanceof BigDecimal) {
            return ((BigDecimal) o).intValue();
        }
        try {
            return Integer.parseInt(o.toString());
        } catch (Exception e) {
            return df;
        }
    }

    public static long asLong(Object o) {
        return asLong(o, 0L);
    }

    public static long asLong(Object o, long df) {
        if (o == null) {
            return df;
        }
        if (o instanceof Long) {
            return (Long) o;
        }
        if (o instanceof Integer) {
            return (Integer) o;
        }
        if (o instanceof Byte) {
            return (Byte) o;
        }
        if (o instanceof Short) {
            return (Short) o;
        }
        if (o instanceof Double) {
            return ((Double) o).longValue();
        }
        if (o instanceof Float) {
            return ((Float) o).longValue();
        }
        if (o instanceof BigInteger) {
            return ((BigInteger) o).longValue();
        }
        if (o instanceof BigDecimal) {
            return ((BigDecimal) o).longValue();
        }
        try {
            return Long.parseLong(o.toString());
        } catch (Exception e) {
            return df;
        }
    }

    public static String asString(Object o) {
        return asString(o, "");
    }

    public static String asString(Object o, String df) {
        try {
            return o.toString();
        } catch (Exception e) {
            return df;
        }
    }

    public static short asShort(Object o) {
        return asShort(o, (short) 0);
    }

    public static short asShort(Object o, short df) {
        if (o == null) {
            return df;
        }
        if (o instanceof Byte) {
            return (Byte) o;
        }
        if (o instanceof Short) {
            return (Short) o;
        }
        if (o instanceof BigInteger) {
            return ((BigInteger) o).shortValue();
        }
        if (o instanceof BigDecimal) {
            return ((BigDecimal) o).shortValue();
        }
        try {
            return Short.parseShort(o.toString());
        } catch (Exception e) {
            return df;
        }
    }

    public static boolean asBoolean(Object o) {
        return asBoolean(o, false);
    }

    public static boolean asBoolean(Object o, boolean df) {
        if (o == null) {
            return df;
        }
        if (o instanceof Boolean) {
            return (Boolean) o;
        }
        if (o instanceof Short || o instanceof Long || o instanceof Byte || o instanceof Integer) {
            return (asInteger(o, 0) != 0);
        }
        if (o instanceof String) {
            String v = asString(o);
            return (v.equalsIgnoreCase("true") || v.equalsIgnoreCase("yes"));
        }
        try {
            return Boolean.parseBoolean(o.toString());
        } catch (Exception e) {
            return df;
        }
    }

    public static float asFloat(Object o) {
        return asFloat(o, 0F);
    }

    public static float asFloat(Object o, float df) {
        if (o == null) {
            return df;
        }
        if (o instanceof Float) {
            return (Float) o;
        }
        if (o instanceof Short || o instanceof Long || o instanceof Byte || o instanceof Integer) {
            return asLong(o, 0);
        }
        try {
            return Float.parseFloat(o.toString());
        } catch (Exception e) {
            return df;
        }
    }

    public static double asDouble(Object o) {
        return asDouble(o, 0D);
    }

    public static double asDouble(Object o, double df) {
        if (o == null) {
            return df;
        }
        if (o instanceof Double) {
            return (Double) o;
        }
        if (o instanceof Float) {
            return (Float) o;
        }
        if (o instanceof Short || o instanceof Long || o instanceof Byte || o instanceof Integer) {
            return asLong(o, 0);
        }
        if (o instanceof BigDecimal) {
            return ((BigDecimal) o).doubleValue();
        }
        try {
            return Double.parseDouble(o.toString());
        } catch (Exception e) {
            return df;
        }
    }

    public static BigDecimal asDecimal(Object o) {
        return asDecimal(o, BigDecimal.ZERO);
    }

    public static BigDecimal asDecimal(Object o, BigDecimal df) {
        if (o == null) {
            return df;
        }
        if (o instanceof BigDecimal) {
            return (BigDecimal) o;
        }
        if (o instanceof Long || o instanceof Integer || o instanceof Byte || o instanceof Short) {
            return BigDecimal.valueOf(asLong(o));
        }
        if (o instanceof Float || o instanceof Double) {
            return BigDecimal.valueOf(asDouble(o));
        }
        try {
            return BigDecimal.valueOf(asDouble(o));
        } catch (Exception e) {
            return df;
        }
    }

    public static Date asDate(Object o) {
        return asDate(o, new Date());
    }


    public static Date asDate(Object o, Date df) {
        if (o == null) {
            return df;
        }
        if (o instanceof Date) {
            return (Date) o;
        }
        if (o instanceof Long) {
            return new Date((Long) o);
        }
        if (o instanceof Integer || (o instanceof String && ((String) o).length() == 8)) {
            String v = o.toString();
            if (v.length() == 8) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(
                        Integer.parseInt(v.substring(0, 4)),
                        Integer.parseInt(v.substring(5, 6)) - 1,
                        Integer.parseInt(v.substring(7, 8)));
                return calendar.getTime();
            }
        }
        try {
            return new DateTime(o.toString()).toDate();
        } catch (Exception e) {
            return df;
        }
    }

    public static   BigDecimal getDays(Date startDate,Date endDate){
        if(CommonUtil.isEmpty(startDate)|| CommonUtil.isEmpty(endDate)){
            return BigDecimal.ZERO;
        }
        long startDateTime = startDate.getTime();
        long endDateTime = endDate.getTime();
        BigDecimal bigDecimal = BigDecimal.valueOf(endDateTime - startDateTime);
        BigDecimal divide = bigDecimal.divide(new BigDecimal(24 * 1000 * 3600),2,BigDecimal.ROUND_HALF_UP);
        return divide;
    }
    public  static  Integer getPages(Integer count , Integer pageSize){
        if(CommonUtil.isEmpty(count)|| count<=0|| CommonUtil.isEmpty(pageSize)||pageSize<=0){
            return 0;
        }
        return count/pageSize+(count%pageSize==0?0:1);
    }

}
