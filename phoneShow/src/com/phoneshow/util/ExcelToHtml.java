package com.phoneshow.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.converter.ExcelToHtmlConverter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.w3c.dom.Document;


public class ExcelToHtml {
    final static String path = "C:/test/";
     final static String file = "excel.xls";
 public static void main(String args[]) throws Exception {
	 Excel2003ToHtml(path, file);
 }
 public static void Excel2003ToHtml(String filepath,String filename) throws Exception{
	 String outName=filename.substring(0,filename.indexOf("."))+"excl.html";
	 String date=filename.substring(0,filename.indexOf("."));
	 InputStream input=new FileInputStream(filepath+filename);
     HSSFWorkbook excelBook=new HSSFWorkbook(input);
     ExcelToHtmlConverter excelToHtmlConverter = new ExcelToHtmlConverter (DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument() );
     excelToHtmlConverter.processWorkbook(excelBook);
     List pics = excelBook.getAllPictures();
     if (pics != null) {
         for (int i = 0; i < pics.size(); i++) {
             Picture pic = (Picture) pics.get (i);
             try {
                 pic.writeImageContent (new FileOutputStream (filepath + pic.suggestFullFileName() ) );
             } catch (FileNotFoundException e) {
                 e.printStackTrace();
             }
         }
     }
     Document htmlDocument =excelToHtmlConverter.getDocument();
     ByteArrayOutputStream outStream = new ByteArrayOutputStream();
     DOMSource domSource = new DOMSource (htmlDocument);
     StreamResult streamResult = new StreamResult (outStream);
     TransformerFactory tf = TransformerFactory.newInstance();
     Transformer serializer = tf.newTransformer();
     serializer.setOutputProperty (OutputKeys.ENCODING, "GBK");
     serializer.setOutputProperty (OutputKeys.INDENT, "yes");
     serializer.setOutputProperty (OutputKeys.METHOD, "html");
     serializer.transform (domSource, streamResult);
     outStream.close();

     String content = new String (outStream.toByteArray() );

     FileUtils.writeStringToFile(new File (filepath, outName), content, "GBK");
 }
}