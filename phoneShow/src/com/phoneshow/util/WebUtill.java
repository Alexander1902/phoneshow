package com.phoneshow.util;

import javax.servlet.http.HttpServletRequest;

public class WebUtill {
	public static String getParameter(String parameter,HttpServletRequest request){
		String param="";
		if(request.getParameter(parameter)!=null){
			param = request.getParameter(parameter);
		}
		return param.trim();
	}
}
