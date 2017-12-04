package com.phoneshow.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.usermodel.PictureType;
/*
 *  public String savePicture(byte[] content, PictureType pictureType, String suggestedName, float widthInches, float heightInches) {
		               File imgPath = new File(imagepath+date+File.separator);
		               suggestedName=date+suggestedName;
	               if(!imgPath.exists()){//图片目录不存在则创建
		                  imgPath.mkdirs();
		                 }
		                 File file = new File(imgPath,suggestedName);
		                try {
	                     OutputStream os = new FileOutputStream(file);
		                     os.write(content);
		                     os.close();
		                 } catch (FileNotFoundException e) {
		                    e.printStackTrace();
		                } catch (IOException e) {
		                    e.printStackTrace();
		                }
		               // return imagepath + suggestedName;
		                return "image/"+date+"/"+suggestedName;
		            }
 */
public class NewPictureManager implements PicturesManager {
	private String datefile;
	private String imagepath;
	
	public NewPictureManager(String datefile, String imagepath) {
		super();
		this.datefile = datefile;
		this.imagepath = imagepath;
	}

	public String getImagepath() {
		return imagepath;
	}

	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}

	public String getDatefile() {
		return datefile;
	}

	public void setDatefile(String datefile) {
		this.datefile = datefile;
	}

	@Override
	public String savePicture(byte[] content, PictureType pictureType, String suggestedName, float widthInches, float heightInches) {
		 File imgPath = new File(imagepath+datefile+File.separator);
         suggestedName=datefile+suggestedName;
     if(!imgPath.exists()){//图片目录不存在则创建
            imgPath.mkdirs();
           }
           File file = new File(imgPath,suggestedName);
          try {
           OutputStream os = new FileOutputStream(file);
               os.write(content);
               os.close();
           } catch (FileNotFoundException e) {
              e.printStackTrace();
          } catch (IOException e) {
              e.printStackTrace();
          }
         // return imagepath + suggestedName;
          return "image/"+datefile+"/"+suggestedName;	}
	
}
