package com.zcj.demo.configurer;

import com.alibaba.fastjson.JSON;
import com.zcj.demo.core.Result;
import com.zcj.demo.core.ResultCode;
import com.zcj.demo.core.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.PathMatcher;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.support.CompositeUriComponentsContributor;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
import org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter;
import org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.resource.ResourceUrlProvider;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 10062376
 * @Date: 2019/3/8 13:54
 * @Description:springBoot的web配置
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public WebConfig() {
        super();
    }

    @Bean
    Interceptor interceptor() {
        return new Interceptor();
    }

    //拦截器配置 拦截所有device/的请求 除了注册请求之外
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor()).addPathPatterns("/device/**").excludePathPatterns("device/register");
    }



    /**
     *  统一异常处理器
     *  1.ServiceException 业务失败异常
     *  2.NoHandlerFoundException 接口不存在
     *  3.ServletException
     *  4.其他异常 接口内部错误抛出的异常
     * @param exceptionResolvers
     */
    @Override
    protected void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(new HandlerExceptionResolver() {
            @Override
            public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
                Result result = new Result();
                if (ex instanceof ServiceException) {//业务失败异常 如“账号密码错误”
                    result.setCode(ResultCode.FAIL).setMessage(ex.getMessage());
                    logger.info(ex.getMessage());
                } else if (ex instanceof NoHandlerFoundException) {
                    result.setCode(ResultCode.NOT_FOUND).setMessage("接口 [" + request.getRequestURI() + "] 不存在");
                } else if (ex instanceof ServletException){
                    result.setCode(ResultCode.FAIL).setMessage(ex.getMessage());
                }else {
                    result.setCode(ResultCode.INTERNAL_SERVER_ERROR).setMessage("接口 ["+request.getRequestURI()+"] 内部错误，请联系管理员");
                    String message;
                    if (handler instanceof HandlerMethod){
                        HandlerMethod handlerMethod = (HandlerMethod) handler;
                        message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s",
                                request.getRequestURI(),
                                handlerMethod.getBean().getClass().getName(),
                                handlerMethod.getMethod().getName(),
                                ex.getMessage());
                    }else{
                        message = ex.getMessage();
                    }
                    logger.error(message,ex);
                }
                response.setCharacterEncoding("UTF-8");
                response.setHeader("Content-type", "application/json;charset=UTF-8");
                response.setStatus(200);
                try {
                    response.getWriter().write(JSON.toJSONString(result));
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
                return new ModelAndView();
            }
        });
    }

    //解决跨域问题
    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");    }
    //
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        super.setApplicationContext(applicationContext);
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        super.setServletContext(servletContext);
    }

    @Override
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        return super.requestMappingHandlerMapping();
    }

    @Override
    protected RequestMappingHandlerMapping createRequestMappingHandlerMapping() {
        return super.createRequestMappingHandlerMapping();
    }

    @Override
    protected PathMatchConfigurer getPathMatchConfigurer() {
        return super.getPathMatchConfigurer();
    }

    @Override
    protected void configurePathMatch(PathMatchConfigurer configurer) {
        super.configurePathMatch(configurer);
    }

    @Override
    public PathMatcher mvcPathMatcher() {
        return super.mvcPathMatcher();
    }

    @Override
    public UrlPathHelper mvcUrlPathHelper() {
        return super.mvcUrlPathHelper();
    }

    @Override
    public ContentNegotiationManager mvcContentNegotiationManager() {
        return super.mvcContentNegotiationManager();
    }

    @Override
    protected Map<String, MediaType> getDefaultMediaTypes() {
        return super.getDefaultMediaTypes();
    }

    @Override
    protected void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        super.configureContentNegotiation(configurer);
    }

    @Override
    public HandlerMapping viewControllerHandlerMapping() {
        return super.viewControllerHandlerMapping();
    }

    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);
    }

    @Override
    public BeanNameUrlHandlerMapping beanNameHandlerMapping() {
        return super.beanNameHandlerMapping();
    }

    @Override
    public HandlerMapping resourceHandlerMapping() {
        return super.resourceHandlerMapping();
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
    }

    @Override
    public ResourceUrlProvider mvcResourceUrlProvider() {
        return super.mvcResourceUrlProvider();
    }

    @Override
    public HandlerMapping defaultServletHandlerMapping() {
        return super.defaultServletHandlerMapping();
    }

    @Override
    protected void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        super.configureDefaultServletHandling(configurer);
    }

    @Override
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
        return super.requestMappingHandlerAdapter();
    }

    @Override
    protected RequestMappingHandlerAdapter createRequestMappingHandlerAdapter() {
        return super.createRequestMappingHandlerAdapter();
    }

    @Override
    protected ConfigurableWebBindingInitializer getConfigurableWebBindingInitializer() {
        return super.getConfigurableWebBindingInitializer();
    }

    @Override
    protected MessageCodesResolver getMessageCodesResolver() {
        return super.getMessageCodesResolver();
    }

    @Override
    protected void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        super.configureAsyncSupport(configurer);
    }

    @Override
    public FormattingConversionService mvcConversionService() {
        return super.mvcConversionService();
    }

    @Override
    protected void addFormatters(FormatterRegistry registry) {
        super.addFormatters(registry);
    }

    @Override
    public Validator mvcValidator() {
        return super.mvcValidator();
    }

    @Override
    protected Validator getValidator() {
        return super.getValidator();
    }

    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
    }

    @Override
    protected void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
        super.addReturnValueHandlers(returnValueHandlers);
    }

    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
    }

    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.extendMessageConverters(converters);
    }

    @Override
    public CompositeUriComponentsContributor mvcUriComponentsContributor() {
        return super.mvcUriComponentsContributor();
    }

    @Override
    public HttpRequestHandlerAdapter httpRequestHandlerAdapter() {
        return super.httpRequestHandlerAdapter();
    }

    @Override
    public SimpleControllerHandlerAdapter simpleControllerHandlerAdapter() {
        return super.simpleControllerHandlerAdapter();
    }

    @Override
    public HandlerExceptionResolver handlerExceptionResolver() {
        return super.handlerExceptionResolver();
    }


    @Override
    protected void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        super.extendHandlerExceptionResolvers(exceptionResolvers);
    }

    @Override
    protected ExceptionHandlerExceptionResolver createExceptionHandlerExceptionResolver() {
        return super.createExceptionHandlerExceptionResolver();
    }

    @Override
    public ViewResolver mvcViewResolver() {
        return super.mvcViewResolver();
    }

    @Override
    protected void configureViewResolvers(ViewResolverRegistry registry) {
        super.configureViewResolvers(registry);
    }


    @Override
    public HandlerMappingIntrospector mvcHandlerMappingIntrospector() {
        return super.mvcHandlerMappingIntrospector();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
