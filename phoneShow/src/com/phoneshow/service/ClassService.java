package com.phoneshow.service;

import java.util.List;
import java.util.Map;


public interface ClassService {
	public List<Map<String, Object>> getAllClass();
	public Map<String, Object> getClassById(String id);
	public void deleteById(String id);
	public int addClass(String name);
	public void updateClass(String id,String name);
	public Integer getCount(String name);
	public void flush();
}
