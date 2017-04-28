package com.asa.core.aspect;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.gson.Gson;

/**
 * 
* @ClassName: LogAspect 
* @Description: 日志记录AOP实现 
* @author shaojian.yu
* @date 2014年11月3日 下午1:51:59 
*
 */
@Aspect
public class LogAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String requestPath = null ; // 请求地址
    private String userName = null ; // 用户名
    private String args = null; //方法里的参数
    private Map<?,?> inputParamMap = null ; // 传入参数
    private Map<String, Object> outputParamMap = null; // 存放输出结果
    private long startTimeMillis = 0; // 开始时间
    private long endTimeMillis = 0; // 结束时间

    /**
     * 
     * @Title：doBeforeInServiceLayer
     * @Description: 方法调用前触发 
     *  记录开始时间 
     * @author shaojian.yu 
     * @date 2014年11月2日 下午4:45:53
     * @param joinPoint
     */
    @Before("execution(* isa.qa.boquma..*.controller..*.*(..))")
    public void doBeforeInServiceLayer(JoinPoint joinPoint) {
        startTimeMillis = System.currentTimeMillis(); // 记录方法开始执行的时间
    }

    /**
     * 
     * @Title：doAfterInServiceLayer
     * @Description: 方法调用后触发 
     *  记录结束时间
     * @author shaojian.yu 
     * @date 2014年11月2日 下午4:46:21
     * @param joinPoint
     */
    @After("execution(* isa.qa.boquma..*.controller..*.*(..))")
    public void doAfterInServiceLayer(JoinPoint joinPoint) {
        endTimeMillis = System.currentTimeMillis(); // 记录方法执行完成的时间
        this.printOptLog();
    }

    /**
     * 
     * @Title：doAround
     * @Description: 环绕触发 
     * @author shaojian.yu 
     * @date 2014年11月3日 下午1:58:45
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("execution(* isa.qa.boquma..*.controller..*.*(..))")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        /**
         * 1.获取request信息
         * 2.根据request获取session
         * 3.从session中取出登录用户信息
         */
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes)ra;
        HttpServletRequest request = sra.getRequest();
        // 从session中获取用户信息
       /* String loginInfo = (String) session.getAttribute("username");
        if(loginInfo != null && !"".equals(loginInfo)){
            userName = operLoginModel.getLogin_Name();
        }else{
            userName = "用户未登录" ;
        }*/
        // 获取输入参数
        inputParamMap = request.getParameterMap();
        // 获取请求地址
        requestPath = request.getRequestURI();
        
        args = Arrays.toString(pjp.getArgs());
        
        // 执行完方法的返回值：调用proceed()方法，就会触发切入点方法执行
        outputParamMap = new HashMap<String, Object>();
        Object result = pjp.proceed();// result的值就是被拦截方法的返回值
        outputParamMap.put("result", result);
        
        return result;
    }

    /**
     * 
     * @Title：printOptLog
     * @Description: 输出日志 
     * @author shaojian.yu 
     * @date 2014年11月2日 下午4:47:09
     */
    private void printOptLog() {
        Gson gson = new Gson(); // 需要用到google的gson解析包
        String optTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTimeMillis);
        logger.info(//"\n user："+userName+
                "\n url："+requestPath
                +"\n args："+args
                +"\n param："+gson.toJson(inputParamMap)+";"
                +"\n result："+gson.toJson(outputParamMap));
    }
}

