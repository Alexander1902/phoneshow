package com.phoneshow.util;

import java.awt.Color;
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

import javax.imageio.ImageIO;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.model.TextRun;
import org.apache.poi.hslf.usermodel.RichTextRun;
import org.apache.poi.hslf.usermodel.SlideShow;


public class PptToHtml {
	   final static String path = "D:/test/";
	     final static String file = "vr.ppt";
	 public static void main(String args[]) throws Exception {
		 //doPPTtoHtml(path, file);
		 doPPTtoHtml(path,file);
	 }
	 /**
	     * doPPTtoHtml(ppt2003转换为图片)
	     * @param pptpath  ppt文件路径
	     * @param imgpath	转换后图片的输出路径
	     * @param imgformat 转换的图片格式
	     * @return Integer  返回转换的图片数量
	 * @throws TransformerException 
	     */
	    
		@SuppressWarnings("unused")
		public static HashMap<String, Object> doPPTtoHtml(String filepath,String fileName) throws IOException, TransformerException{ 
	    	HashMap<String, Object> hs = new HashMap<>(); //用来存放返回的一些东西,可扩展
	    	String pptpath = filepath+ fileName;
	    	String name = fileName.substring(0, fileName.lastIndexOf("."))+"ppt";//获取后缀名
	    	String imgpath =filepath+"/images";   //图片路径是否存在
	    	File imgp = new File(imgpath); //创建文件夹
	    	if(!imgp.exists()){//图片目录不存在则创建
	    		imgp.mkdirs();
	    		System.out.println("创建图片文件夹");
	         }
	    	String imgformat ="jpg";
	    	String htmlname=fileName+".html"; //真正的名字 
	    //检查传入的ppt文件地址是否正确
	    String filename = preReadCheck(pptpath);
	    FileInputStream orignalPPTFileInputStream=null;
	    FileOutputStream orignalPPTFileOutputStream=null;

	    SlideShow oneSlideShow=null;
	   
	    String  imghtml=""; 
	    
	    try{ 
	    FileOutputStream out =null; 
	    orignalPPTFileInputStream=new FileInputStream(pptpath);
	    oneSlideShow=new SlideShow(orignalPPTFileInputStream);

	    /** *获得PPT每页的尺寸大小（宽和高度）*/
	    Dimension onePPTPageSize=oneSlideShow.getPageSize();
	    /** *获得PPT文件中的所有的PPT页面（获得每一张幻灯片），并转换为一张张 的播放片 */ 
	    Slide[]pptPageSlideArray=oneSlideShow.getSlides();
	    /** *下面的循环的主要功能是实现对PPT文件中的每一张幻灯片进行转换和操 作。 */
	   
	    for(int pptPageSlideIndex=0; pptPageSlideIndex<pptPageSlideArray.length; pptPageSlideIndex++){ 
	    TextRun[]textRunsArray=pptPageSlideArray[pptPageSlideIndex].getTextRuns(); 
	    for(int textRunsArrayIndex=0;textRunsArrayIndex<textRunsArray.length; textRunsArrayIndex++){ 
	    RichTextRun[] pptRichTextRunsArray = textRunsArray[textRunsArrayIndex].getRichTextRuns(); 
	    for(int pptRichTextRunsArrayIndex=0;pptRichTextRunsArrayIndex < pptRichTextRunsArray.length;pptRichTextRunsArrayIndex++){	
	    pptRichTextRunsArray[pptRichTextRunsArrayIndex].setFontIndex(1); 
	    pptRichTextRunsArray[pptRichTextRunsArrayIndex].setFontName("宋体"); 
	/*    但如果 PPT 文件在 WPS 中保存过，则 pptRichTextRunsArray[pptRichTextRunsArrayIndex].getFontSize()的值可能为0或者26040。 因此首先识别当前文本框内的字体尺寸是否为0或者大于26040，则 设置默认的字体尺寸。*/ 
	    int currentFontSize = pptRichTextRunsArray[pptRichTextRunsArrayIndex].getFontSize(); 
	    if((currentFontSize<=0)||(currentFontSize>=26040)){ 
	    pptRichTextRunsArray[pptRichTextRunsArrayIndex].setFontSize(30); 
	    }	
	    }
	    }

	    /** *创建BufferedImage对象，图象的尺寸为原来PPT的每页的尺寸*/
	    BufferedImage oneBufferedImage=new BufferedImage(onePPTPageSize.width, onePPTPageSize.height,BufferedImage.TYPE_INT_RGB); 
	    Graphics2D oneGraphics2D=oneBufferedImage.createGraphics();

	    oneGraphics2D.fill(new Rectangle2D.Float(0,0,onePPTPageSize.width, onePPTPageSize.height));
	    pptPageSlideArray[pptPageSlideIndex].draw(oneGraphics2D);

	    orignalPPTFileOutputStream=new FileOutputStream(imgpath + "/" + filename  + "_"+ pptPageSlideIndex + "."+imgformat);
	    
	    ImageIO.write(oneBufferedImage, imgformat, orignalPPTFileOutputStream); 
	    String imgs=filename  + "_"+ pptPageSlideIndex + "."+imgformat;  
	    out= new FileOutputStream(imgs);
	    System.out.println(imgs);
	    imghtml+="<img src=\'"+"images\\"+imgs+"\' style=\'width:800px;height:600px;vertical-align:text-bottom;\'><br><br><br><br>";  
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
       String ppthtml="<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"></head><body style=\"text-align:center;\">"+imghtml+"</body></html>";  
      
       FileUtils.writeStringToFile(new File(filepath, htmlname), ppthtml, "utf-8");      
       System.out.println(name+"/??????????"+htmlname);
       System.out.println(name+"/"+htmlname);
       hs.put("htmlURL",htmlname);
	    }finally{ 
	    try{ 
	    if(orignalPPTFileInputStream!=null){ 
	    orignalPPTFileInputStream.close();
	    }
	    if(orignalPPTFileOutputStream!=null){
	    orignalPPTFileOutputStream.close();
	    }
	    } catch(IOException e){ 
	    e.printStackTrace();
	    }
	    }
	    return hs;
	    }
    /**
     * @param filepath 
     * @param fileName
     * @return
     * @throws Exception
     */
    public static HashMap<String, Object> doPPTtoHtml1(String filepath,String fileName) throws Exception {    
  
    	HashMap<String, Object> hs = new HashMap<>(); //用来存放返回的一些东西,可扩展
    	String name = fileName.substring(0, fileName.lastIndexOf("."))+"ppt";//获取后缀名
    	System.out.println("name:"+name);
    	String htmlname=fileName+".html"; //真正的名字 
    	System.out.println("htmlname:"+htmlname);
    	File file = new File(filepath+name); //创建文件夹
    	System.out.println(file.getAbsolutePath());
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
            String  imghtml=""; 
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
                graphics.setPaint(Color.white); 
                graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));     
                slide[i].draw(graphics);     
                // 这里设置图片的存放路径和图片的格式(jpeg,png,bmp等等),注意生成文件路径     
                out= new FileOutputStream(imgpath+"/" +fileName+(i + 1) + ".jpeg");    
                javax.imageio.ImageIO.write(img, "jpeg", out);   
                //图片在html加载路径  
                String imgs=fileName+(i + 1) + ".jpeg";    
                imghtml+="<img src=\'"+"images\\"+imgs+"\' style=\'width:800px;height:600px;vertical-align:text-bottom;\'><br><br><br><br>";  
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
               String ppthtml="<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"></head><body style=\"text-align:center;\">"+imghtml+"</body></html>";  
              
               FileUtils.writeStringToFile(new File(file, htmlname), ppthtml, "utf-8");      
               System.out.println(name+"/??????????"+htmlname);
               hs.put("htmlURL", name+"/"+htmlname);
        } catch (FileNotFoundException e) {     
            System.out.println(e);     
        } catch (IOException e) {     
        }     
        return hs;
    }     
    
   
	    //文件检查,如果存在则返回文件名
	    private static String preReadCheck(String path) throws FileNotFoundException {
	    File file = new File(path);
	    if (!file.exists()) {
	    throw new FileNotFoundException("传入的文件不存在：" + path);
	    }
	    String filename = file.getName();
	    return filename.substring(0,filename.lastIndexOf("."));
	    }
    }

