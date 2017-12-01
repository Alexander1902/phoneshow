package com.phoneshow.service;

import java.util.List;
import java.util.Map;

import com.phoneshow.entity.User;


public interface UserService {
	/**
	 * 查询用户信息
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getUserInfo();
	/**
	 * 删除用户信息
	 * @param id
	 * @return
	 */
	public Integer deleteById(String id);
	/**
	 * 添加用户信息
	 * @param id
	 * @return
	 */
	public Map<String, Object> insertUser(String username,String password);
	/**
	 * 根据id修改用户密码
	 * @param
	 */
	public void updatePassword(String password);
	/**
	 * 用户登录验证
	 * @param
	 */
	User checkLogin(User user);
	
}
