package com.phoneshow.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phoneshow.cache.CacheClass;
import com.phoneshow.dao.ClassNameDao;
import com.phoneshow.service.ClassService;
@Service
public class ClassServiceImpl implements ClassService {
	@Autowired
	private ClassNameDao classNameDao;
	
	@Override
	public List<Map<String, Object>> getAllClass() {
		List<Map<String, Object>> clazz = CacheClass.getInstance().getClazz();
		return clazz;
	}

	@Override
	public Map<String, Object> getClassById(String id) {
		Map<String, Object> classById = CacheClass.getInstance().getClassById(id);
		if(classById!=null){
			return classById;
		}else {
			return null;
		}
	}

	@Override
	public void deleteById(String id) {
		id=id.trim();
		classNameDao.deleteById(id);
		flush();
	}

	@Override
	public int addClass(String name) {
		Integer count = getCount(name);
		if(count==0){
			classNameDao.addClass(name);
			flush();
			return 1;
		}
		return 2; 
	}

	@Override
	public void updateClass(String id, String name) {
		classNameDao.updateClass(id.trim(), name.trim());
		flush();
	}

	@Override
	public Integer getCount(String name) {
		Integer count = classNameDao.getCount(name.trim());
		return count;
	}

	@Override
	public void flush() {
		List<Map<String, Object>> allClassName = classNameDao.getAllClassName();
		CacheClass.getInstance().setClazz(null);
		CacheClass.getInstance().setClazz(allClassName);
		
	}
 
}
