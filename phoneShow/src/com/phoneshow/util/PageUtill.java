package com.phoneshow.util;

public class PageUtill {

	public static Integer getPageCount(Integer dataCount,Integer dataSize){
		if(dataCount%dataSize==0){
			return dataCount/dataSize;
		}else{
			return dataCount/dataSize+1;
		}
	}
	
	public static Integer getPageData(Integer pageno,Integer datasize){
		return (pageno-1)*datasize;
	}
}
