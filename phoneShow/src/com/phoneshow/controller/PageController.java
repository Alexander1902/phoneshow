package com.phoneshow.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.phoneshow.service.OfficeConverterService;
import com.phoneshow.util.MyDateUtil;
import com.phoneshow.util.PageUtill;
import com.phoneshow.util.WebUtill;
import com.phoneshow.util.WordToHtml;

@Controller
@RequestMapping("/page")
public class PageController {

	Logger logger = Logger.getLogger(PageController.class);

	@Autowired
	private OfficeConverterService officeConverterService;

	/**
	 * 跳页
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/{page}.do")
	public String page(@PathVariable String page) {
		logger.info("跳到" + page + ".jsp");
		return page;
	}

	/**
	 * 文档管理，分页，查询 Administrator TODO
	 * 获得参数{"type":type,"title":title,"start":start,"end":end}
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
		//解决乱码
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

		Map<String, Object> datamap = new HashMap<String, Object>();// 页码参数+条件参数
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
		logger.info("datacount:" + datacount + ",取到数据量：" + office.size());

		Integer pageCount = PageUtill.getPageCount(datacount, pagesize);
		Map<String, Object> hash = new HashMap<>();
		hash.put("pagecount", pageCount);
		hash.put("office", office);
		hash.put("datacount", datacount);
		hash.put("pageno", pageno);
		return hash;
	}

	/**
	 * 删除数据
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
			map.put("erorr", "删除失败");
			return map;
		}
		Integer deleteById = officeConverterService.deleteById(id, realPath);
		if (deleteById == 1) {
			// 计算是否要减页数
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
			map.put("erorr", "删除失败");
		}
		return map;
	}

	/**
	 * 添加公告
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
	 * TODO 修改title 返回json stute的1成功，2失败
	 */
	@RequestMapping("/update.do")
	@ResponseBody
	public Map<String, Object> updateOffice(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
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
}
