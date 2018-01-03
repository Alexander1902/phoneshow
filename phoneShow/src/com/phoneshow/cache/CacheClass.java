package com.phoneshow.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Flush;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.phoneshow.dao.ClassNameDao;

/**
 * 内存中增删该分类
 * @author zsc
 *
 */ 
public class CacheClass {
	Logger log=Logger.getLogger(CacheClass.class);
	private List<Map<String, Object>> clazz;
	private static CacheClass cacheClass;
	
	private CacheClass(){}
	public static CacheClass getInstance(){
		if(cacheClass==null){
			cacheClass=new CacheClass();
		}
		return cacheClass;
	}

	public static void deleteById(String id){
		Object index = getIndex(id);
		if(index!=null){
			Integer indexs=(Integer)index;
			boolean remove = getInstance().getClazz().remove(indexs);
		}
	}
	
	/**
	 * 根据id拿到list的索引
	 * zsc
	 * TODO
	 */
	public static Object getIndex(String id){
		id=id.trim();
		if (id!=null&&id!="") {
			int index=(Integer) null;
			List<Map<String, Object>> clazz2 = getInstance().getClazz();
			for(int i=0;i<clazz2.size();i++){
				Map<String, Object> map = clazz2.get(i);
				if(id==map.get("id")){
					index = i;
					break;
				}
				else {
					continue;
				}
			}
			return index;
		}else {
			return null;
		} 
	}
	
	public Map<String, Object> getClassById(String id){
		id=id.trim();
		if (id!=null&&id!="") {
			Map<String, Object> mapId=null;
			List<Map<String, Object>> clazz2 =getInstance().getClazz();
			for(int i=0;i<clazz2.size();i++){
				Map<String, Object> map = clazz2.get(i);
				System.out.println(map.get("id"));
				if(id.equals(map.get("id"))){
					mapId=map;
					break;
				}
				else {
					continue;
				}
			}
			return mapId;
		}else {
			return null;
		} 
	}

	
	
	public List<Map<String, Object>> getClazz() {
		return clazz;
	}

	public void setClazz(List<Map<String, Object>> clazz) {
		this.clazz = clazz;
	}
}
