package com.phoneshow.service;

import java.util.List;
import java.util.Map;

import com.phoneshow.entity.User;


public interface UserService {
	/**
	 * ��ѯ�û���Ϣ
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getUserInfo();
	/**
	 * ɾ���û���Ϣ
	 * @param id
	 * @return
	 */
	public Integer deleteById(String id);
	/**
	 * ����û���Ϣ
	 * @param id
	 * @return
	 */
	public Map<String, Object> insertUser(String username,String password);
	/**
	 * ����id�޸��û�����
	 * @param
	 */
	public void updatePassword(String password);
	/**
	 * �û���¼��֤
	 * @param
	 */
	User checkLogin(User user);
	
}
