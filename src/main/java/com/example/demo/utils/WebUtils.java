package com.example.demo.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class WebUtils {
    public static String buildUrlForPaging(HttpServletRequest request, String baseUrl) {
        String urlQuery = request.getQueryString();
        if (StringUtils.isEmpty(urlQuery)) {
            return baseUrl;
        }
        return baseUrl + "?" + urlQuery.replaceAll("&page=\\d+", StringUtils.EMPTY)
                .replaceAll("page=\\d+", StringUtils.EMPTY);
    }
}
