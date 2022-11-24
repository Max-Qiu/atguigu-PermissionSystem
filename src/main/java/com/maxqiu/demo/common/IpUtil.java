package com.maxqiu.demo.common;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取ip地址
 *
 * @author Max_Qiu
 */
public class IpUtil {
    public static String getIpAddress(HttpServletRequest servletRequest) {
        String ip = servletRequest.getHeader("x-forwarded-for");
        if (isNotTheIp(ip)) {
            if (isNotTheIp(ip)) {
                ip = servletRequest.getHeader("Proxy-Client-IP");
            }
            if (isNotTheIp(ip)) {
                ip = servletRequest.getHeader("WL-Proxy-Client-IP");
            }
            if (isNotTheIp(ip)) {
                ip = servletRequest.getHeader("HTTP_CLIENT_IP");
            }
            if (isNotTheIp(ip)) {
                ip = servletRequest.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (isNotTheIp(ip)) {
                ip = servletRequest.getRemoteAddr();
            }
        }
        // 处理多个IP的情况
        for (String s : ip.split(",")) {
            if (!("unknown".equalsIgnoreCase(s))) {
                return s;
            }
        }
        return ip;
    }

    /**
     * IP是否可用
     *
     * @param ip
     *            IP
     */
    private static boolean isNotTheIp(String ip) {
        return ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip);
    }
}
