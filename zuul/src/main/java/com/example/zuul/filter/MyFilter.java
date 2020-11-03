package com.example.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 服务过滤
 * zuul不仅只是路由，并且还能过滤，做一些安全验证。
 * @Author LinJia
 * @Date 2020/8/12
 **/
@Component
public class MyFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(MyFilter.class);

    /**
     * @Description:返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型
     * pre：路由之前
     * routing：路由之时
     * post： 路由之后
     * error：发送错误调用
     * @Author LinJia
     * @Date 2020/8/12 11:17
     * @Param []
     * @return java.lang.String
     **/
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * @Description:过滤的顺序
     * @Author LinJia
     * @Date 2020/8/12 11:17
     * @Param []
     * @return int
     **/
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * @Description:这里可以写逻辑判断，是否要过滤，本文true,永远过滤
     * @Author LinJia
     * @Date 2020/8/12 11:17
     * @Param []
     * @return boolean
     **/
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * @Description:过滤器的具体逻辑。可用很复杂，包括查sql，nosql去判断该请求到底有没有权限访问
     * @Author LinJia
     * @Date 2020/8/12 11:18
     * @Param []
     * @return java.lang.Object
     **/
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
        //判断访问参数是否有token
        Object accessToken = request.getParameter("token");
        if(accessToken == null) {
            log.warn("token is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            try {
                ctx.getResponse().getWriter().write("token is empty");
            }catch (Exception e){

            }
            return null;
        }
        log.info("ok");
        return null;
    }
}
