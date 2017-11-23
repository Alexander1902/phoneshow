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
  
    	HashMap<String, Object> hs = new HashMap<>(); //������ŷ��ص�һЩ����,����չ
    	String name = fileName.substring(0, fileName.lastIndexOf("."))+"ppt";//��ȡ��׺��
    	System.out.println("name:"+name);
    	String htmlname=fileName+".html"; //���������� 
    	System.out.println("htmlname:"+htmlname);
    	File file = new File(filepath+"\\"+name); //�����ļ���
    	 String imgpath =filepath+name+"/images";   //ͼƬ·���Ƿ����
     	System.out.println("htmlname:"+imgpath);
    	File imgp = new File(imgpath); //�����ļ���
    	if(!file.exists()){//��ַĿ¼�������򴴽�
    		file.mkdirs();
         	System.out.println("������ַ�ļ���");
         }
    	if(!imgp.exists()){//ͼƬĿ¼�������򴴽�
    		imgp.mkdirs();
    		System.out.println("����ͼƬ�ļ���");
         }
        try {     
        	//��ppt ���д���
            FileInputStream is = new FileInputStream(filepath+fileName);     
            SlideShow ppt = new SlideShow(is);     
            is.close();     
            Dimension pgsize = ppt.getPageSize();     
            org.apache.poi.hslf.model.Slide[] slide = ppt.getSlides();    
            FileOutputStream out =null;  
            String imghtml="";  
            for (int i = 0; i < slide.length; i++) {     
                System.out.print("��" + i + "ҳ��");     
                TextRun[] truns = slide[i].getTextRuns();        
                for ( int k=0;k<truns.length;k++){        
                   RichTextRun[] rtruns = truns[k].getRichTextRuns();        
                  for(int l=0;l<rtruns.length;l++){        
                        rtruns[l].setFontIndex(1);        
                        rtruns[l].setFontName("����");    
                   }        
                }        
                BufferedImage img = new BufferedImage(pgsize.width,pgsize.height, BufferedImage.TYPE_INT_RGB);     
                Graphics2D graphics = img.createGraphics();  
              // graphics.setPaint( new GradientPaint( x, y, Color.GREEN, x + RADIUS/4, y + RADIUS/4, Color.BLACK, true));     
                graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));     
                slide[i].draw(graphics);     
                // ��������ͼƬ�Ĵ��·����ͼƬ�ĸ�ʽ(jpeg,png,bmp�ȵ�),ע�������ļ�·��     
                out= new FileOutputStream(imgpath+"/" +fileName+(i + 1) + ".jpeg");    
                javax.imageio.ImageIO.write(img, "jpeg", out);   
                //ͼƬ��html����·��  
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
               System.out.println("ƴ�ӳ�һ����ҳ����������");
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
