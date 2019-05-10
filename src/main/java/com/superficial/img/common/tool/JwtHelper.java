package com.superficial.img.common.tool;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import lombok.val;
import org.joda.time.DateTime;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class JwtHelper {


    private final static   String  LOGIN_NAME="0";
    private final static   String  secret="2";

    private  final static Map<String, Object> HEADER = new ImmutableMap.Builder<String, Object>()
            .build();



    public static String getToken(){
        return HttpUtils.getToken();
    }

    public static String getLoginName(){
         String token=getToken();
        if (Strings.isNullOrEmpty(token)) {
            return "system";
        }
        Map<String, Claim> m = JWT.decode(token).getClaims();
        if (CommonUtil.isEmpty(m.get(LOGIN_NAME)) ) {
            return "";
        }
        return m.get(LOGIN_NAME).asString();
    }

    public static String sign(String loginName, int expireOfMinutes) {

        DateTime now = DateTime.now();

        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withHeader(HEADER)
                .withClaim(LOGIN_NAME, loginName)
                .withIssuedAt(now.toDate())
                .withExpiresAt(now.plusMinutes(expireOfMinutes).toDate()).sign(algorithm);

    }
    }
