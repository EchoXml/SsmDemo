package com.echo.web;



import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.echo.dto.Result;
import com.echo.entity.UserInfo;
import com.echo.enums.RegisterStateEnum;
import com.echo.service.UserInfoService;
import com.echo.util.CommonUtil;
import com.google.gson.Gson;


@Controller
@RequestMapping("/user")
@SessionAttributes("loginUser")
public class UserInfoController {
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserInfoService userInfoService;

	/**
	 * 跳转登录界面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model){
		// list.jsp + model = ModelAndView
        return "login";// WEB-INF/jsp/"list".jsp
	}
	
	/**
	 * 执行登录操作
	 * @param userInfo
	 * @param model
	 * @param response
	 */
	@RequestMapping(value="/ajax/login.do",method=RequestMethod.POST)
	public void doLogin(UserInfo userInfo,Model model,HttpServletResponse response){
		logger.info(userInfo.getUsername()+"\t"+userInfo.getPassword());
		UserInfo loginUser=userInfoService.login(userInfo);
		response.setCharacterEncoding("UTF-8");
	
		String result="";
		PrintWriter out=null;
		try {
			out=response.getWriter();
			if (loginUser!=null) {
				model.addAttribute("loginUser",loginUser);
				result="true";
			}else{
				result="fasle";
			}
			out.print(result);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (out!=null) {
				out.flush();
				out.close();
			}
		}
		
		
	}
	
	
	/**
	 * 注销操作
	 */
	@RequestMapping(value="/logout.do")
	public String doLogout(HttpServletRequest request){
		HttpSession session=request.getSession();
		UserInfo userInfo2=(UserInfo) session.getAttribute("loginUser");
		
		logger.info("Session中的用户信息："+userInfo2);
		session.invalidate();
		//session.invalidate();
		
		return "login";
	}
	/**
	 * 跳转注册界面
	 * @return
	 */
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public String toRegister(){
		return "register";
	}
	
	@RequestMapping(value="/ajax/register.do",method={RequestMethod.GET,RequestMethod.POST})
	public void doRegister(HttpServletResponse response,UserInfo userInfo) {
		Result<RegisterStateEnum> result=userInfoService.register(userInfo);
		CommonUtil.setAjaxDataToResponse(response, new Gson().toJson(result));
	}
	
	/**
	 * 校验用户名是否存在
	 * @param response
	 * @param username
	 */
	@RequestMapping(value="/ajax/checkUsername",method=RequestMethod.GET)
	public void checkUsername(HttpServletResponse response,String username){
		String result=userInfoService.checkUserNameExsit(username);
		CommonUtil.setAjaxDataToResponse(response, result);
	}
	
	@RequestMapping("/test")
	public String test(){
		return "test";
	}
	
	
}
