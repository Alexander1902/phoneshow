package com.phoneshow.util;

import org.apache.poi.xwpf.converter.core.BasicURIResolver;
import org.apache.poi.xwpf.converter.core.IURIResolver;

public class MyFileURIResolver implements IURIResolver{
	
	 private final String imagePath;
	
	 public MyFileURIResolver(String imagePath) {
		this.imagePath=imagePath;
	}
	@Override
	public String resolve(String uri) {
		
		uri=this.imagePath;
        return  uri;
	}
}
