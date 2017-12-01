package com.phoneshow.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phoneshow.dao.UserDao;
import com.phoneshow.service.UserService;

@Service
public class UserServiceImp implements UserService {
	Logger log = Logger.getLogger(UserServiceImp.class);
	
	@Autowired
	private UserDao userDao;
	
	//获取用户信息
	@Override
	public List<Map<String, Object>> getUserInfo() {
		List<Map<String, Object>> selectUserInfo = userDao.selectUser();
		Map<String, Object> pageMap = new HashMap<>();
		pageMap.put("selectUserInfo", selectUserInfo);
		return selectUserInfo;
	}
	
	//删除用户信息
	@Override
	public Integer deleteById(String id) {
		// TODO Auto-generated method stub
		if (id == null || id == "") {
			return 2;//
		}
		try {
			userDao.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return 2;//
		}
		return 1;//
	}
	
	//添加用户信息
	@Override
	public Map<String, Object> insertUser(String username,String password) {
			UUID randomUUID = UUID.randomUUID();
			String id = randomUUID.toString();
			Map<String, Object> result = new HashMap<>();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("username", username);
			map.put("password", password);
			map.put("flag", 3);
			try {
				log.info(map);
				userDao.insertUser(map);
				result.put("stute", 1);
			} catch (Exception e) {
				e.printStackTrace();
				result.put("stute", 2);
			}
			return result;
	}
	//根据id修改用户密码
	@Override
	public void updatePassword(String password) {
		Map<String, Object> map = new HashMap<>();
		map.put("password", password);
		int updatePassword = userDao.updatePassword(map);
		if(updatePassword>0){
			log.info("修改成功："+password);
		}else {
			log.info("修改失败！"+password);
		}
	}
	
}
