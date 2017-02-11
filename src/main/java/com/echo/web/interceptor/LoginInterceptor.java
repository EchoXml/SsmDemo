package com.echo.web.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.echo.entity.UserInfo;

public class LoginInterceptor implements HandlerInterceptor {
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception ex)
			throws Exception {
	
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView)
			throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		UserInfo userInfo=(UserInfo) request.getSession().getAttribute("loginUser");
		logger.info("用户请求的url为："+request.getRemoteAddr()+":"+request.getRemotePort()+"-----"+request.getServletPath());
		//注销操作
		if (userInfo==null) {
			if (request.getServletPath().endsWith("logout.do")) {
				request.getSession().invalidate();
				logger.info("exec logout....");
			}
			logger.info("请求被拦截，用户处于未登录状态");
			 response.setContentType("text/html");  
             response.setCharacterEncoding("utf-8");  
             PrintWriter out = response.getWriter();    
             StringBuilder builder = new StringBuilder();    
             builder.append("<script type=\"text/javascript\" charset=\"UTF-8\">");    
             builder.append("alert(\"请重新登陆！\");");    
             builder.append("window.location.href=\"/SsmDemo/user/login\";");    
             builder.append("</script>");    
             out.print(builder.toString());    
             out.close();    
             return false;  
		}else  {
//			if (request.getServletPath().endsWith("logout.do")) {
//				request.getSession().removeAttribute(paramString);
//				logger.info("exec logout....");
//				response.setContentType("text/html");  
//	             response.setCharacterEncoding("utf-8");  
//	             PrintWriter out = response.getWriter();    
//	             StringBuilder builder = new StringBuilder();    
//	             builder.append("<script type=\"text/javascript\" charset=\"UTF-8\">");    
//	             builder.append("alert(\"请重新登陆！\");");    
//	             builder.append("window.location.href=\"/SsmDemo/user/login\";");    
//	             builder.append("</script>");    
//	             out.print(builder.toString());    
//	             out.close();    
//	             return false;  
//			}else{
				logger.info("请求被放行，登录用户信息如下："+userInfo);
				return true;
//			}h
			 
		}
	
	}

}
