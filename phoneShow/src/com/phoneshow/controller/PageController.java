package com.phoneshow.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phoneshow.entity.User;
import com.phoneshow.service.OfficeConverterService;
import com.phoneshow.service.UserService;
import com.phoneshow.util.MyDateUtil;
import com.phoneshow.util.PageUtill;
import com.phoneshow.util.WebUtill;

import sun.security.action.PutAllAction;

@Controller
@RequestMapping("/page")
public class PageController {

	Logger logger = Logger.getLogger(PageController.class);

	@Autowired
	private OfficeConverterService officeConverterService;
	@Autowired
	private UserService userService;
	
	/**
	 * �˳�
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/logout.do")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("USER", null);
			return "login";
	}
	/**
	 * ��¼
	 * @param
	 */
	@RequestMapping("/login.do")
	@ResponseBody
	public Map<String, Object> login(HttpServletRequest request){
		//�����ݿ��в�ѯ
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		String username = WebUtill.getParameter("username", request);
		String password = WebUtill.getParameter("password", request);
		try {
			username=URLDecoder.decode(username, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		Map<String, Object> map = new HashMap<>();
		map.put("username", username);
		map.put("password", password);
		List<Map<String, Object>> userList = userService.checkLogin(map);
		Map<String, Object> result = new HashMap<>();
		if(userList.size()>0){
			HttpSession session = request.getSession();
			session.setAttribute("USER", userList.get(0));
			result.put("stute", 1);
		} else {
			result.put("stute", 2);
			result.put("erorr", "��½ʧ��");
		}
		logger.info("username:" + username);
		
		return result;
		
	}
	
	/**
	 * ��ѯ�û���Ϣ
	 * @param request
	 * @return
	 */
	@RequestMapping("/getUserInfo.do")
	@ResponseBody
	public List<Map<String, Object>> getUserInfoList(HttpServletRequest request){
		List<Map<String, Object>> userList = userService.getUserInfo();
		return userList;
	}
	/**
	 * ɾ���û���Ϣ
	 * @param request
	 * @return
	 */
	@RequestMapping("/delUserInfo.do")
	@ResponseBody
	public Map<String, Object> deleteUserById(HttpServletRequest request){
		String id = WebUtill.getParameter("id", request);
		Map<String, Object> map = new HashMap<>();
		if (id == null || id == "") {
			map.put("stute", 2);
			map.put("erorr", "ɾ���û�ʧ��");
		}
		Integer deleteById = userService.deleteById(id);
		if (deleteById == 1) {
			map.put("stute", 1);
		} else {
			map.put("stute", 2);
			map.put("erorr", "ɾ��ʧ��");
		}
		return map;
	}
	
	/**
	 * �����û���Ϣ
	 * @param
	 */
	@RequestMapping(value = "/userInfo.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> insertUserInfo(HttpServletRequest request) {
		
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		String username = WebUtill.getParameter("username", request);
		String password = WebUtill.getParameter("password", request);
		try {
			username=URLDecoder.decode(username, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		logger.info("username:" + username);
		Map<String, Object> resultmap = userService.insertUser(username, password);
		return resultmap;
	}
	
	/**
	 * ����id�޸��û�������Ϣ
	 */
	@RequestMapping("/updatePwd.do")
	@ResponseBody
	public Map<String, Object> updatePwdById(HttpServletRequest request) {
		
		String id = WebUtill.getParameter("id", request);
		String password = WebUtill.getParameter("password", request);
		
		Map<String,Object> map=new HashMap<>();
		try {
			userService.updatePassword(id, password);;
			map.put("stute", 1);
			map.put("id", id);
			map.put("password", password);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("stute", 2);
		}
		return map;
	}

	/**
	 * ��ҳ
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/{page}.do")
	public String page(@PathVariable String page,HttpServletRequest request) {
		logger.info("����" + page + ".jsp");
		HttpSession session = request.getSession();
		Object attribute = session.getAttribute("USER");
		if(attribute==null){
			System.out.println("�Ѿ��˳���¼~");
			return "login";
		}
		return page;
	}

	/**
	 * �ĵ�������ҳ����ѯ Administrator TODO
	 * ��ò���{"type":type,"title":title,"start":start,"end":end}
	 */
	@RequestMapping(value = "/getoffice.do")
	@ResponseBody
	public Map<String, Object> selectOffice(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		Integer pagesize = 10;
		int pageno = Integer.parseInt(WebUtill.getParameter("pageno", request), 10);
		pagesize = Integer.parseInt(WebUtill.getParameter("pagesize", request), 10);
		String type = WebUtill.getParameter("type", request);
		String title = WebUtill.getParameter("title", request);
		//�������
		try {
			title=URLDecoder.decode(title, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String start = WebUtill.getParameter("start", request);
		String end = WebUtill.getParameter("end", request);
		/*
		 * int pageno = Integer.parseInt((String)cond.get("pageno"),10);
		 * pagesize = Integer.parseInt((String)cond.get("pagesize"),10);
		 */
		logger.info("pageno:" + pageno + ",pagesize:" + pagesize + ",type:" + type + ",title:" + title + ",start:"
				+ start + ",end:" + end);

		Map<String, Object> datamap = new HashMap<String, Object>();// ҳ�����+��������
		datamap.put("pageno", PageUtill.getPageData(pageno, pagesize));
		datamap.put("pagesize", pagesize);
		datamap.put("type", type);
		datamap.put("title", "%" + title + "%");
		datamap.put("start",
				start != null && start != "" ? MyDateUtil.DateToString(MyDateUtil.StringDateToDate(start)) : "");
		datamap.put("end", end != null && end != "" ? MyDateUtil.DateToString(MyDateUtil.AddDay(MyDateUtil.StringDateToDate(end), 1)) : "");
		List<Map<String, String>> office = officeConverterService.getOffice(datamap);

		Map<String, Object> countmap = new HashMap<String, Object>();
		countmap.put("type", type);
		countmap.put("title", "%" + title + "%");
		countmap.put("start",
				start != null && start != "" ? MyDateUtil.DateToString(MyDateUtil.StringDateToDate(start)) : "");
		countmap.put("end", end != null && end != "" ? MyDateUtil.DateToString(MyDateUtil.AddDay(MyDateUtil.StringDateToDate(end), 1)) : "");
		Integer datacount = officeConverterService.getCount(countmap);
		logger.info("datacount:" + datacount + ",ȡ����������" + office.size());

		Integer pageCount = PageUtill.getPageCount(datacount, pagesize);
		Map<String, Object> hash = new HashMap<>();
		hash.put("pagecount", pageCount);
		hash.put("office", office);
		hash.put("datacount", datacount);
		hash.put("pageno", pageno);
		return hash;
	}

	/**
	 * ɾ������
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/delete.do")
	@ResponseBody
	public Map<String, Object> deleteOne(HttpServletRequest request) {
		String id = WebUtill.getParameter("id", request);
		int pageno = Integer.parseInt(WebUtill.getParameter("pageno", request), 10);
		int pagesize = Integer.parseInt(WebUtill.getParameter("pagesize", request), 10);
		int totalpage = Integer.parseInt(WebUtill.getParameter("totalpage", request), 10);
		String type = WebUtill.getParameter("type", request);
		String title = WebUtill.getParameter("title", request);
		String start = WebUtill.getParameter("start", request);
		String end = WebUtill.getParameter("end", request);
		logger.info("id:" + id + ",pageno:" + pageno + ",pagesize:" + pagesize + ",totalpage:" + totalpage);

		String file = "/office";
		String realPath = request.getServletContext().getRealPath(file);
		logger.info("realPath:" + realPath);

		Map<String, Object> map = new HashMap<>();
		if (id == null || id == "") {
			map.put("stute", 2);
			map.put("erorr", "ɾ��ʧ��");
			return map;
		}
		Integer deleteById = officeConverterService.deleteById(id, realPath);
		if (deleteById == 1) {
			// �����Ƿ�Ҫ��ҳ��
			Map<String, Object> countMap = new HashMap<>();
			countMap.put("type", type);
			countMap.put("title", title);
			countMap.put("start", start);
			countMap.put("end", end);
			Integer count = officeConverterService.getCount(countMap);
			Integer pageCount = PageUtill.getPageCount(count, pagesize);
			if (pageCount == totalpage) {
				map.put("pageno", pageno);
			} else {
				map.put("pageno", pageno - 1);
			}
			map.put("pagecount", pageCount);
			map.put("datacount", count);
			map.put("stute", 1);
		} else {
			map.put("stute", 2);
			map.put("erorr", "ɾ��ʧ��");
		}
		return map;
	}

	/**
	 * ��ӹ���
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/notice.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> insertNotice(HttpServletRequest request) {
		String content = WebUtill.getParameter("content", request);
		String title = WebUtill.getParameter("title", request);
		logger.info("content:" + content);
		Map<String, Object> resultmap = officeConverterService.insertNotice(content, title);
		return resultmap;
	}

	@RequestMapping("/getNotice.do")
	@ResponseBody
	public Map<String, Object> getNotice(HttpServletRequest request) {
		String id = WebUtill.getParameter("id", request);

		Map<String, Object> map = null;
		map = officeConverterService.getOfficeById(id);
		return map;
	}

	/**
	 * Administrator
	 * TODO �޸�title ����json stute��1�ɹ���2ʧ��
	 */
	@RequestMapping("/update.do")
	@ResponseBody
	public Map<String, Object> updateOffice(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String id = WebUtill.getParameter("id", request);
		String title = WebUtill.getParameter("title", request);
		try {
			title=URLDecoder.decode(title, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Map<String,Object> map=new HashMap<>();
		try {
			officeConverterService.updateOffice(title, id);
			map.put("stute", 1);
			map.put("id", id);
			map.put("title", title);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("stute", 2);
		}
		return map;
	}
	 @RequestMapping("/download")
	    public void download( HttpServletRequest request,
	            HttpServletResponse response) throws Exception {
	        logger.info("����download");
	        String realPath = request.getServletContext().getRealPath("/");
	        String id = WebUtill.getParameter("id", request);
	        logger.info("id"+id);
	        Map<String, Object> hs = officeConverterService.getOfficeById(id);
	        if(hs == null){
	        	throw new Exception("id �������");
	        }
	        String fileName =(String) hs.get("original_name");
	        if(fileName == "" || fileName == null){
	        	throw new Exception("Դ�ļ������ڣ���");
	        }
	        logger.info("fileName"+fileName);
	        String path =realPath+hs.get("server_path");
	        logger.info("path"+path);
	        //1.�����ļ�ContentType���ͣ��������ã����Զ��ж������ļ�����  
	        response.setContentType("multipart/form-data");
	        //2.�����ļ�ͷ
	        String userAgent = request.getHeader("User-Agent");
	        if (StringUtils.isBlank(userAgent)) {
	            fileName = URLEncoder.encode(fileName, "UTF-8");
	        } else {
	            if (userAgent.indexOf("MSIE") != -1) {
	                // IEʹ��URLEncoder
	                fileName = URLEncoder.encode(fileName, "UTF-8");
	            } else {
	                // FireFoxʹ��ISO-8859-1
	                fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
	            }
	        }
	        File file = new File(path);
	        response.reset();
	        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
	        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
	        response.setHeader("Pragma", "public");
	        response.addHeader("Content-Length", "" + file.length());
	        response.setDateHeader("Expires", (System.currentTimeMillis() + 1000));

	        OutputStream out = null;


	        InputStream inputStream = null;
	        try {
	            inputStream = new FileInputStream(file);


	            out = response.getOutputStream();


	            byte[] buffer = new byte[1024];
	            int count = 0;
	            while ((count = inputStream.read(buffer)) != -1) {
	                out.write(buffer, 0, count);
	            }

	          
	            out.close();
	            out.flush();


	        } catch (IOException e) {
	            logger.error("�ļ������쳣:{}", e);
	        } finally {
	            IOUtils.closeQuietly(inputStream);
	        }
	    }
	 @RequestMapping("/allid.do")
	 @ResponseBody
	 public List<Map<String, Object>> getAllId(){
		 List<Map<String, Object>> allId = officeConverterService.getAllId();
		 return allId;
	 }

}
