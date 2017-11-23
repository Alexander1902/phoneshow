package com.phoneshow.util;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hslf.model.TextRun;
import org.apache.poi.hslf.usermodel.RichTextRun;
import org.apache.poi.hslf.usermodel.SlideShow;


public class PptToHtml {
 
    /**
     * @param filepath 
     * @param fileName
     * @return
     * @throws Exception
     */
    public static HashMap<String, Object> doPPTtoHtml(String filepath,String fileName) throws Exception {    
  
    	HashMap<String, Object> hs = new HashMap<>(); //用来存放返回的一些东西,可扩展
    	String name = fileName.substring(0, fileName.lastIndexOf("."))+"ppt";//获取后缀名
    	System.out.println("name:"+name);
    	String htmlname=fileName+".html"; //真正的名字 
    	System.out.println("htmlname:"+htmlname);
    	File file = new File(filepath+"\\"+name); //创建文件夹
    	 String imgpath =filepath+name+"/images";   //图片路径是否存在
     	System.out.println("htmlname:"+imgpath);
    	File imgp = new File(imgpath); //创建文件夹
    	if(!file.exists()){//网址目录不存在则创建
    		file.mkdirs();
         	System.out.println("创建网址文件夹");
         }
    	if(!imgp.exists()){//图片目录不存在则创建
    		imgp.mkdirs();
    		System.out.println("创建图片文件夹");
         }
        try {     
        	//对ppt 进行处理
            FileInputStream is = new FileInputStream(filepath+fileName);     
            SlideShow ppt = new SlideShow(is);     
            is.close();     
            Dimension pgsize = ppt.getPageSize();     
            org.apache.poi.hslf.model.Slide[] slide = ppt.getSlides();    
            FileOutputStream out =null;  
            String imghtml="";  
            for (int i = 0; i < slide.length; i++) {     
                System.out.print("第" + i + "页。");     
                TextRun[] truns = slide[i].getTextRuns();        
                for ( int k=0;k<truns.length;k++){        
                   RichTextRun[] rtruns = truns[k].getRichTextRuns();        
                  for(int l=0;l<rtruns.length;l++){        
                        rtruns[l].setFontIndex(1);        
                        rtruns[l].setFontName("宋体");    
                   }        
                }        
                BufferedImage img = new BufferedImage(pgsize.width,pgsize.height, BufferedImage.TYPE_INT_RGB);     
                Graphics2D graphics = img.createGraphics();  
              // graphics.setPaint( new GradientPaint( x, y, Color.GREEN, x + RADIUS/4, y + RADIUS/4, Color.BLACK, true));     
                graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));     
                slide[i].draw(graphics);     
                // 这里设置图片的存放路径和图片的格式(jpeg,png,bmp等等),注意生成文件路径     
                out= new FileOutputStream(imgpath+"/" +fileName+(i + 1) + ".jpeg");    
                javax.imageio.ImageIO.write(img, "jpeg", out);   
                //图片在html加载路径  
                String imgs=fileName+(i + 1) + ".jpeg";    
                imghtml+="<img src=\'"+"images\\"+imgs+"\' style=\'width:600px;height:600px;vertical-align:text-bottom;\'><br><br><br><br>";  
            }     
            	DOMSource domSource = new DOMSource();  
                StreamResult streamResult = new StreamResult(out);  
                TransformerFactory tf = TransformerFactory.newInstance();  
                Transformer serializer = tf.newTransformer();  
                serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");  
                serializer.setOutputProperty(OutputKeys.INDENT, "yes");  
                serializer.setOutputProperty(OutputKeys.METHOD, "html");  
                serializer.transform(domSource, streamResult);  
               out.close();   
               System.out.println("拼接成一个网页。。。。。");
               String ppthtml="<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"></head><body>"+imghtml+"</body></html>";  
              
               FileUtils.writeStringToFile(new File(file, htmlname), ppthtml, "utf-8");      
               System.out.println(name+"/??????????"+htmlname);
               hs.put("htmlURL", name+"/"+htmlname);
        } catch (FileNotFoundException e) {     
            System.out.println(e);     
        } catch (IOException e) {     
        }     
        return hs;
    }     
}
