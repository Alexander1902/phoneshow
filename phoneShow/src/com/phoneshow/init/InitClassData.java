package com.phoneshow.init;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.phoneshow.cache.CacheClass;
import com.phoneshow.dao.ClassNameDao;

public class InitClassData {
	@Autowired
	private ClassNameDao classNameDao;
	
	public void init(){
		getClassData();
		System.out.println("初始化分类完成");
	}
	
	public void getClassData(){
		List<Map<String, Object>> allClassName = classNameDao.getAllClassName();
		CacheClass.getInstance().setClazz(allClassName);
		/*List<Map<String, Object>> clazz = CacheClass.getInstance().getClazz();
		for(Map<String, Object> map:clazz){
			for(Map.Entry<String, Object> entry:map.entrySet()){
				System.out.println("key:"+entry.getKey()+",value:"+entry.getValue());
			}
		}*/
	}
	
}
