package com.zhku.controller;

import com.zhku.pojo.SysLog;
import com.zhku.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private SysLogService sysLogService;

    private Date visitTime;//开始时间
    private Class clazz;//使用的类
    private Method method;//访问的方法

//    前置通知    主要获取访问时间、访问的类、访问的方法

//    @Before("execution(* com.zhku.controller.*.*(..))")

    @Before("execution(* com.zhku.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        System.out.println("前置通知!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        visitTime = new Date();//当前时间就是开始时间
        clazz = jp.getTarget().getClass();//具体要访问的类
        String methodName = jp.getSignature().getName();//获取访问方法的名称
        Object[] args = jp.getArgs();//获取访问方法的参数

        //获取具体执行的Method对象
        if (args == null || args.length == 0) {// 无参数
            method = clazz.getMethod(methodName);//只能获取无参数的方法
        }else {
            // 有参数，就将args中所有元素遍历，获取对应的Class,装入到一个Class[]
//            Class[] classArgs = new Class[args.length];
//            for (int i = 0; i < args.length; i++) {
//                classArgs[i] = args[i].getClass();
//            }
//            method = clazz.getMethod(methodName, classArgs);
//            前面方法会造成int类型在遍历时变成Integer，在getMethod方法是会找不到带该参数类型的方法

            MethodSignature methodSignature = (MethodSignature) jp.getSignature();
            method = methodSignature.getMethod();
        }
    }

//    后置通知
    @After("execution(* com.zhku.controller.*.*(..))")
    public void doAfter(JoinPoint jp){
        long time = new Date().getTime()-visitTime.getTime();//获取访问的时长

        String url = "";
//        获取url
        if(clazz!=null&&method!=null&&clazz!=LogAop.class){

//            1.获取类上的@RequestMapping("/orders")
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if(classAnnotation!=null){
                String[] classValue = classAnnotation.value();

                //           2.获取方法上的@RequestMapping("/xxx")
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation!=null){
                    String[] methodValue = methodAnnotation.value();
                    url = classValue[0]+methodValue[0];
                }
            }

        }

//        获取访问的ip
        String ip = request.getRemoteAddr();

//        获取当前操作的用户
        SecurityContext context = SecurityContextHolder.getContext();
        User user = (User) context.getAuthentication().getPrincipal();
        String name = user.getUsername();

//        将相关信息封装到SysLog对象里面
        SysLog sysLog = new SysLog();
        sysLog.setIp(ip);
        sysLog.setMethod("[类名]"+clazz.getName()+"[方法名]"+method.getName());
        sysLog.setUrl(url);
        sysLog.setUsername(name);
        sysLog.setVisitTime(visitTime);
        sysLog.setExecutionTime(time);

//        调用service方法保存日志
        sysLogService.save(sysLog);
    }
}
