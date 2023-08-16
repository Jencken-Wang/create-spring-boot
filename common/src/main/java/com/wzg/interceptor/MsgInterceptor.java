package com.wzg.interceptor;


import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Component
public class MsgInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        PrintWriter writer = null;
        try {
            System.out.println("interceptor preHandle");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            String token = request.getHeader("token");
            if (StrUtil.isBlank(token) && !"111".equals(token)) {
                System.out.println("require token is invalid");
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("state", false);
                jsonObject.put("msg", "require token is invalid");
                writer = response.getWriter();
                writer.append(jsonObject.toString());
                return false;
            }

            return true;
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("interceptor posthandle");
    }

    /**
     * This implementation is empty.
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) throws Exception {
        System.out.println("interceptor aftercompletion");
    }

    /**
     * This implementation is empty.
     */
    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response,
                                               Object handler) throws Exception {
    }

}
