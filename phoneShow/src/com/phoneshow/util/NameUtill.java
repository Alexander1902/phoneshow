package com.phoneshow.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.Count;

import sun.jdbc.odbc.OdbcDef;

public class NameUtill {
	
	public static String getNewNameByCount(Integer count,String oldName){
		String name = oldName.substring(0,oldName.lastIndexOf('.'));
		String exp = oldName.substring(oldName.lastIndexOf('.')+1);//��չ��
		count+=1;
		return name+"("+count+")."+exp;
	}
	
	public static String getNewName(List<Map<String, Object>> list,String oldName){
		int lastIndexOf = oldName.lastIndexOf(".");
		oldName=oldName.substring(0, lastIndexOf);
		int count=0;
		for(int i=0;i<list.size();i++){
			String namelist = (String) list.get(i).get("original_name");
			char charAt = namelist.charAt(namelist.lastIndexOf(".")-1);
			if(charAt==')'){
				String number= namelist.substring(namelist.lastIndexOf("("), namelist.lastIndexOf("."));
				System.out.println(number);
			}
		}
		return null;
	}
	public static String getNewNameTest(String oldName){
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		Map<String, Object> name=new HashMap<String, Object>();
		name.put("original_name", "��.doc");
		list.add(name);
		Map<String, Object> name1=new HashMap<String, Object>();
		name.put("original_name", "��(2).doc");
		list.add(name1);
		Map<String, Object> name3=new HashMap<String, Object>();
		name.put("original_name", "��(3).doc");
		list.add(name3);
		Map<String, Object> name4=new HashMap<String, Object>();
		name.put("original_name", "��(4).doc");
		list.add(name4);
		
		
		int lastIndexOf = oldName.lastIndexOf(".");
		oldName=oldName.substring(0, lastIndexOf);
		int count=0;
		for(int i=0;i<list.size();i++){
			String namelist = (String) list.get(i).get("original_name");
			char charAt = namelist.charAt(namelist.lastIndexOf(".")-1);
			if(charAt==')'){
				String number= namelist.substring(namelist.lastIndexOf("("), namelist.lastIndexOf("."));
				System.out.println(number);
			}
		}
		return "sreing";
	}
	public static void main(String[] args) {
		System.out.println(getNewNameTest("��.ces.doc"));
	}
}
