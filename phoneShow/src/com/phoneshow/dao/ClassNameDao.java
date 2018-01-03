package com.phoneshow.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ClassNameDao {
	public List<Map<String, Object>> getAllClassName();

	public List<Map<String, Object>> getClassById(@Param("id") String id);

	public void deleteById(@Param("id") String id);

	public void addClass(@Param("name") String name);

	public void updateClass(@Param("id") String id, @Param("name") String name);
	
	public Integer getCount(@Param("name")String name);
}
