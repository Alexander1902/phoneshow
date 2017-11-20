package com.phoneshow.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.phoneshow.service.OfficeConverterService;
import com.phoneshow.util.MyDateUtil;
import com.phoneshow.util.WebUtill;
@Controller
@RequestMapping("/upload")
public class UploadController {
	
	Logger logger=Logger.getLogger(UploadController.class);
	
	@Autowired
	private OfficeConverterService officeConverterService;
	/**上传文档，保存文档，文件名传给servce层
	 * @param upload
	 * @param request
	 * @return
	 */
	@RequestMapping("/upload.do")
	public String getExcel(@RequestParam(name="upload")MultipartFile upload,HttpServletRequest request){
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String file="/office";
		String realPath = request.getServletContext().getRealPath(file);
		File officefile = new File(realPath);
		String title = WebUtill.getParameter("title", request);
		if(!officefile.exists()){
			officefile.mkdirs();
		}
		String expandname = upload.getOriginalFilename().substring(upload.getOriginalFilename().indexOf('.'));
		logger.info(expandname+"扩展名");
		String fileName = MyDateUtil.DateAndTime()+expandname;
		File save = new File(officefile, fileName);
		InputStream input = null;
		OutputStream output = null;
		byte[] buff = new byte[512];
		try {
			input = upload.getInputStream();
			output = new FileOutputStream(save);
			int ch = 0;
			while((ch=input.read(buff))!=-1){
				output.write(buff, 0, ch);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				output.flush();
				input.close();
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		try {
			int officeConverter = officeConverterService.officeConverter(realPath+File.separator, fileName,expandname,title);
			if (officeConverter==1) {
				request.setAttribute("stute", "1");
			}else{
				request.setAttribute("stute", "2");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "show";
	}
}
