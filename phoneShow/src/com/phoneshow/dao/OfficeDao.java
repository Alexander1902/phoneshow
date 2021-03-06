package com.phoneshow.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface OfficeDao {
	public int insertOffice(Map<String, Object> map);
	public List<Map<String, String>> selectOffice(Map<String,Object> map);
	public Integer selectCount(Map<String, Object> map);
	public void deleteById(String id);
	public Map<String, Object> getOfficeById(String id);
	public int updateOffice(Map<String, Object> map);
	public List<Map<String, Object>> getAllId();
	public List<Map<String, Object>> getNameLike(@Param("name")String name);
	public Integer getNameCount(@Param("name")String name);
}
