package com.phoneshow.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface OfficeConverterService {
	public int officeConverter(String filepath,String fileName,String expandname,String title);
	public List<Map<String, String>> getOffice(Map<String, Object> map);
	public Integer getCount(Map<String, Object> map);
	public Integer deleteById(String id,String path);
	public Map<String, Object> getOfficeById(String id);
	public Map<String, Object> insertNotice(String content,String title);
	public void updateOffice(String title,String id);
}
