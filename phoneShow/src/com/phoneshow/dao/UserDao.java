package com.phoneshow.dao;

import java.util.List;
import java.util.Map;

public interface UserDao {
	//插入用户
	public int insertUser(Map<String, Object> map);
	//查询用户信息
	public List<Map<String, Object>> selectUser();
	//删除用户
	public Map<String, Object> deleteById(String id);
	//根据id修改用户密码
	public int updatePassword(Map<String, Object> map);
	//验证登陆
	public void checkLogin(String username,String password);
}
