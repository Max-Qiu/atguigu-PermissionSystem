package com.maxqiu.demo.common;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

import com.alibaba.fastjson2.JSON;

/**
 * @author Max_Qiu
 */
public class ResponseUtil {
    public static void out(HttpServletResponse response, Result<Object> r) {
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json; charset=utf-8");
        try {
            PrintWriter writer = response.getWriter();
            writer.print(JSON.toJSONString(r));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
