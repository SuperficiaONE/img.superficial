package com.superficial.img.common.tool;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;


@Component
public class HttpUtils {

  public static final String TOKEN_KEY = "TOKEN";
  public static final String TOKEN_HEADER = "auth";
  public static final Integer COOKIE_EXPIRE_TIME=24*3600;

  public static HttpServletRequest currentRequest() {
    try {
      return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
          .getRequest();
    } catch (Exception e) {
      return null;
    }
  }


  public static HttpServletResponse currentResponse() {
    try {
      return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
          .getResponse();
    } catch (Exception e) {
      return null;
    }
  }

  public static String currentRemoteIp() {
    HttpServletRequest request = currentRequest();
    if (request == null) {
      return "";
    }

    if (request.getHeader("x-forwarded-for") != null) {
      return request.getHeader("x-forwarded-for");
    }
    return request.getRemoteAddr();
  }
  public static String currentHost() {
    HttpServletRequest request = currentRequest();
    if (request == null) {
      return "";
    }

    String host = request.getHeader("X-Forwarded-Host");
    if (!CommonUtil.isEmpty(host)) {
      return host;
    }
    return request.getHeader("Host");
  }
  public static String currentUrl() {
    HttpServletRequest request = currentRequest();
    if (request == null) {
      return "";
    }
    return request.getScheme() + "://" + currentHost() + request.getRequestURI();
  }

  public static void writeToken(String token) {
    HttpServletResponse response = HttpUtils.currentResponse();
    response.addHeader(TOKEN_HEADER,token);
    Cookie cookie = new Cookie(TOKEN_KEY, token);
    cookie.setPath("/");
    writeCookie(cookie);
  }
  /**
   * 请求是否需要被拦截
   */
  public static boolean isFilterPass(HttpServletRequest httpRequest) {
    String thisUrl = httpRequest.getRequestURI();
    return (thisUrl.startsWith("/static/") ||
        thisUrl.startsWith("/manage/") ||
        thisUrl.startsWith("/druid/") ||
        thisUrl.startsWith("/igh2/") ||
        thisUrl.endsWith(".css") || thisUrl.endsWith(".jpg") || thisUrl.endsWith(".jpeg") ||
        thisUrl.endsWith(".html") || thisUrl.endsWith(".js") || thisUrl.endsWith(".png") ||
        thisUrl.endsWith(".gif") || thisUrl.endsWith(".map"));
  }
  /**
   * 得到当前request请求的所有cookie
   */
  public static Cookie[] getCookies() {
    HttpServletRequest request = currentRequest();
    return request == null ? null : request.getCookies();
  }

  /**
   * 根据cookie名字取得cookie
   */
  public static Cookie getCookie(String name) {
    Cookie[] cookies = getCookies();
    if (cookies != null && cookies.length > 0) {
      for (int i = 0; i < cookies.length; i++) {
        Cookie cookie = cookies[i];
        String cookName = cookie.getName();
        if (cookName != null && cookName.equals(name)) {
          return cookie;
        }
      }
    }
    return null;
  }

  public static String getCookieValue(String name) {
    Cookie cookie = getCookie(name);
    if (cookie == null) {
      return null;
    }
    return cookie.getValue();
  }

  /**
   * 将cookie写入客户端
   */
  public static void writeCookie(Cookie cookie) {
    if (cookie == null) {
      return;
    }
    HttpServletResponse response = currentResponse();
    // 单位是秒
    cookie.setMaxAge(COOKIE_EXPIRE_TIME);
    if (response != null) {
      response.addCookie(cookie);
    }
  }

  public static void removeCookie(String cookieName, String path) {

    HttpServletResponse response = currentResponse();
    HttpServletRequest request = currentRequest();

    Cookie[] cookies = request.getCookies();
    if (cookies == null || cookies.length == 0) {
      return;
    }

    for (int i = 0; i < cookies.length; i++) {
      Cookie cookie = cookies[i];
      if (cookie.getName().equals(cookieName)) {
        cookie.setMaxAge(0);
        cookie.setPath(path);
        response.addCookie(cookie);
        break;
      }
    }

  }

  public static String urlEncoding(String value) {
    try {
      byte[] bs = Base64.encodeBase64URLSafe(value.getBytes("UTF-8"));
      return new String(bs, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException("encode error.", e);
    }
  }
  public  static  HttpServletResponse getByImageHeader(){
    HttpServletResponse response = currentResponse();
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
    response.setContentType("image/jpeg");
    return response;
  }
  public static String  getToken(){
    HttpServletRequest request = currentRequest();
    if(CommonUtil.isEmpty(request)){
      return "";
    }
    String header = request.getHeader(TOKEN_HEADER);
    if(!CommonUtil.isEmpty(header)){
      return header;
    }
    String token = getCookieValue(TOKEN_KEY);
    if(CommonUtil.isEmpty(token)){
      return "";
    }
    return token;
  }
}
