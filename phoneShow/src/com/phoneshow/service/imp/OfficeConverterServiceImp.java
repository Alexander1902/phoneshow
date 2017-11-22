package com.phoneshow.service.imp;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;
import org.jcp.xml.dsig.internal.MacOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phoneshow.dao.OfficeDao;
import com.phoneshow.service.OfficeConverterService;
import com.phoneshow.util.ExcelToHtml;
import com.phoneshow.util.MyDateUtil;
import com.phoneshow.util.WordToHtml;

@Service
public class OfficeConverterServiceImp implements OfficeConverterService {
	Logger log = Logger.getLogger(OfficeConverterServiceImp.class);
	@Autowired
	private OfficeDao officeDao;

	/*
	 * 识别文件类型，转换格式,数据入库 VALUE(#{id},#{title},#{type},#{url},#{content},#{date})
	 * 
	 * @see
	 */
	@Override
	public int officeConverter(String filepath, String fileName, String expandname, String title) {
		if (!filepath.endsWith(File.separator)) {
			filepath = filepath + File.separator;
		}
		expandname = expandname.substring(1);// 扩展名
		String date = MyDateUtil.TimeShow();
		UUID randomUUID = UUID.randomUUID();
		String id = randomUUID.toString();
		log.info("srvice" + expandname);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("id", id);
			map.put("title", title);
			map.put("content", "");
			map.put("date", date);
			if (expandname.equals("doc") || expandname.equals("DOC")) {
				map.put("type", "1");
				String url = fileName.substring(0, fileName.indexOf('.')) + "word.html";// 输出文件
				map.put("url", url);
				WordToHtml.Word2003ToHtml(filepath, fileName);
				officeDao.insertOffice(map);
			} else if (expandname.equals("xls") || expandname.equals("XLS")) {
				ExcelToHtml.Excel2003ToHtml(filepath, fileName);
				map.put("type", "2");
				String url = fileName.substring(0, fileName.indexOf('.')) + "excl.html";// 输出文件
				map.put("url", url);
				officeDao.insertOffice(map);
				Map<String, Object> hMap = new HashMap<>();

				List<Map<String, String>> selectOffice = officeDao.selectOffice(hMap);
				for (Map<String, String> map2 : selectOffice) {
					System.out.println(map2.get("url") + "测试");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 2;
		}
		return 1;
	}

	/*
	 * 文档管理，分页，查询
	 * 
	 * @see
	 * com.phoneshow.service.OfficeConverterService#getOffice(java.util.Map)
	 */
	@Override
	public List<Map<String, String>> getOffice(Map<String, Object> map) {
		List<Map<String, String>> selectOffice = officeDao.selectOffice(map);
		Integer selectCount = officeDao.selectCount(map);
		// log.info("title"+map.get("title"));
		Map<String, Object> pageMap = new HashMap<>();
		pageMap.put("selectCount", selectCount);
		pageMap.put("selectOffice", selectOffice);
		return selectOffice;
	}

	@Override
	public Integer getCount(Map<String, Object> map) {
		Integer selectCount = officeDao.selectCount(map);
		return selectCount;
	}

	@Override
	public Integer deleteById(String id, String path) {
		if (id == null || id == "") {
			return 2;// 失败
		}
		try {
			Map<String, Object> office = officeDao.getOfficeById(id);
			String url = (String) office.get("url");
			File myFile = new File(path, url);
			try {
				if (!myFile.exists()) {
					log.info("文件不存在");
				}
				myFile.delete();
			} catch (Exception e) {
				e.printStackTrace();
				log.info("文件删除失败！");
			}
			officeDao.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return 2;// 失败
		}
		return 1;// 1成功
	}

	@Override
	public Map<String, Object> getOfficeById(String id) {
		Map<String, Object> map = null;
		if (id == "" || id == null) {
			return map;
		}
		map = officeDao.getOfficeById(id);
		return map;
	}

	/*
	 * (non-Javadoc) VALUE(#{id},#{title},#{type},#{url},#{content},#{date})
	 */
	@Override
	public Map<String, Object> insertNotice(String content, String title) {
		String date = MyDateUtil.TimeShow();
		UUID randomUUID = UUID.randomUUID();
		String id = randomUUID.toString();
		Map<String, Object> result = new HashMap<>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("title", title);
		map.put("type", 3);
		map.put("content", content);
		map.put("url", "");
		map.put("date", MyDateUtil.TimeShow());
		try {
			officeDao.insertOffice(map);
			result.put("stute", 1);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("stute", 2);
		}
		return result;
	}

	/*
	 * Administrator TODO修改文档的title
	 */
	@Override
	public void updateOffice(String title, String id) {
		Map<String, Object> map = new HashMap<>();
		map.put("title", title);
		map.put("id", id);
		int updateOffice = officeDao.updateOffice(map);
		if(updateOffice>0){
			log.info("修改成功："+title);
		}else {
			log.info("修改失败！"+title);
		}
	}

}
