package com.qgg.zuulclient.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 自定义过滤器，拦截验证签名
 */
@Component
public class MyFilter extends ZuulFilter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 指定该Filter的类型
     * ERROR_TYPE = "error";
     * POST_TYPE = "post";
     * PRE_TYPE = "pre";
     * ROUTE_TYPE = "route";
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 执行顺序，越小越先执行
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 指定需要执行该Filter的规则
     * 返回true则执行run()
     * 返回false则不执行run()
     */
    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String requestUrl = request.getRequestURL().toString();

        // 请求URL内不包含login或join则需要经过该过滤器，即执行run()
        return !requestUrl.contains("login") && !requestUrl.contains("join");
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        Object taken = request.getParameter("token");
        if (taken == null) {
            logger.warn("token is null");
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(401);

            context.setResponseBody("签名不能为空xx");

            //设置返回内容类型及编码
            context.getResponse().setContentType("text/html;charset=UTF-8");

            /*try {
                HttpServletResponse response = context.getResponse();
                PrintWriter writer = response.getWriter();
                writer.write("签名不能为空");
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }*/
        }



        return null;
    }
}
