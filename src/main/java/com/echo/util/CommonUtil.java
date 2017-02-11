package com.echo.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class CommonUtil {
	
	
	/**
	 * 通用的ajax调用方法
	 * @param response
	 * @param object 要返回的数据
	 */
	public static void setAjaxDataToResponse(HttpServletResponse response,Object result){
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=null;
		try {
			out=response.getWriter();
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

}
