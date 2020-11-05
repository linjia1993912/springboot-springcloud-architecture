package com.example.zuul.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 服务过滤
 * zuul不仅只是路由，并且还能过滤，做一些安全验证。
 *
 * 还可以用Zuul的前置过滤器来实现令牌桶限流
 *
 * @Author LinJia
 * @Date 2020/8/12
 **/
@Component
@Slf4j
public class MyFilter extends ZuulFilter {

    @Value("${server.port}")
    String port;

    // 定义一个令牌桶，每秒产生2个令牌，即每秒最多处理2个请求
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(2);

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
        return FilterConstants.PRE_TYPE;
    }

    /**
     * @Description:过滤的顺序,越小越先执行，这里第一个执行
     * 当然不是只真正第一个 在Zuul内置中有其他过滤器会先执行
     * 那是写死的 比如 SERVLET_DETECTION_FILTER_ORDER = -3
     * @Author LinJia
     * @Date 2020/8/12 11:17
     * @Param []
     * @return int
     **/
    @Override
    public int filterOrder() {
        //SEND_RESPONSE_FILTER_ORDER 是最后一个过滤器
        //return FilterConstants.SEND_RESPONSE_FILTER_ORDER - 1;
        return -5;
    }

    /**
     * @Description:什么时候该进行过滤,这里可以写逻辑判断,这样我们就可以过滤掉一些不符合规定的请求等等,比如限流
     * @Author LinJia
     * @Date 2020/8/12 11:17
     * @Param []
     * @return boolean
     **/
    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        if(!RATE_LIMITER.tryAcquire()) {
            log.warn("访问量超载");
            // 指定当前请求未通过过滤
            context.setSendZuulResponse(false);
            // 向客户端返回响应码429，请求数量过多
            context.setResponseStatusCode(429);
            return false;
        }
        return true;
    }

    /**
     * @Description: 如果过滤器允许通过则怎么进行处理
     * 过滤器的具体逻辑。过滤时执行的策略，可以验证请求参数
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
        log.info("访问Zuul网关端口为："+port);
        return null;
    }
}
