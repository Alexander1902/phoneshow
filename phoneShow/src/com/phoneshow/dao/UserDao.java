package com.phoneshow.dao;

import java.util.List;
import java.util.Map;

public interface UserDao {
	//�����û�
	public int insertUser(Map<String, Object> map);
	//��ѯ�û���Ϣ
	public List<Map<String, Object>> selectUser();
	//ɾ���û�
	public Map<String, Object> deleteById(String id);
	//����id�޸��û�����
	public int updatePassword(Map<String, Object> map);
	//��֤��½
	public void checkLogin(String username,String password);
}
