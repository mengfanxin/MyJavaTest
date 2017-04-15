package org.fkit.hrm.interceptor;

import org.fkit.hrm.domain.User;
import org.fkit.hrm.util.HrmConstants;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2017/4/15 0015.
 */
public class AuthorizedInterceptor implements HandlerInterceptor{

    //定义不需要拦截的请求
    private static final String[] IGNORE_URI = {"loginForm","login","/404.html"};

    @Override
    public boolean preHandle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object o) throws Exception {
        boolean flag = false;
        String servletPath = request.getServletPath();
        for (String s: IGNORE_URI){
            if (servletPath.contains(s)){
                flag = true;
                break;
            }
        }

        if(!flag){
            User user = (User) request.getSession().getAttribute(HrmConstants.USER_SESSION);
            if(user == null){
                request.setAttribute("message","请先登录再访问网站");
                request.getRequestDispatcher(HrmConstants.LOGIN).forward(request,response);
                return flag;
            }else {
                flag = true;
            }
        }


        return flag;
    }

    @Override
    public void postHandle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler, Exception e) throws Exception {

    }
}
