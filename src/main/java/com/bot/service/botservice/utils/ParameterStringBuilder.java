package com.bot.service.botservice.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class ParameterStringBuilder {
    public static String getParamsString(Map<String, String> params) throws UnsupportedEncodingException {
    StringBuilder result = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"))
                    .append("=")
                    .append(URLEncoder.encode(entry.getValue(), "UTF-8"))
                    .append("&");
        }
        String queryString = result.toString();
    return queryString.length() > 0
            ? queryString.substring(0, queryString.length() - 1):queryString;
    }
}