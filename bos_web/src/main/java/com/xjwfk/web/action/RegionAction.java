package com.xjwfk.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hp.hpl.sparta.Document.Index;
import com.xjwfk.domain.Region;
import com.xjwfk.service.RegionService;
import com.xjwfk.utils.MyJsonUtils;
import com.xjwfk.utils.PinYin4jUtils;
import com.xjwfk.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<Region> {

	private File regionFile;
	private String q;	//条件查询
	@Autowired
	private RegionService regionService;

	public String importXls() throws FileNotFoundException, IOException {
		List<Region> regions = new ArrayList<>();	//把解析得到的数据封装在这个集合里
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(regionFile));//解析Excel文件的的对象
		HSSFSheet sheet = hssfWorkbook.getSheetAt(0);	
		for (Row row : sheet) {
			if (row.getRowNum() == 0) continue; // 剔除表格第一行的数据
			String id = row.getCell(0).getStringCellValue();
			String province = row.getCell(1).getStringCellValue();
			String city = row.getCell(2).getStringCellValue();
			String district = row.getCell(3).getStringCellValue();
			String postcode = row.getCell(4).getStringCellValue();
			Region region = new Region(id, province, city, district, postcode, null, null);
			
			province = province.substring(0, province.length()-1);	//剔除字符串的最后一个字符
			city = city.substring(0,city.length()-1);
			district = district.substring(0,district.length()-1);
			String info = province + city + district;
			
			String[] headByString = PinYin4jUtils.getHeadByString(info);	//汉字字符串转-->>汉字首字母
			String shortcode = StringUtils.join(headByString);	//字符串数组转为转为字符串
			String citycode = PinYin4jUtils.hanziToPinyin(city,"");
			
			region.setShortcode(shortcode);
			region.setCitycode(citycode);
			
			regions.add(region);
		}		//循环完毕后，数据全部解析并封装到集合regions了
		regionService.saveBatch(regions);
		return null;
	}
	
	public String pageQuery() throws IOException {
		regionService.pageQuery(pageBean);
		String json = MyJsonUtils.OjectToJson(pageBean, new String[] {"id"});
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(json);
		return null;
	}
	
	public String listAjax() throws IOException {
		List<Region> regions = null;
		if (StringUtils.isNotBlank(q)) {	//根据关键字查询
			regions = regionService.findBy_Keyword(q);
		}else {		//查询所有
			 regions = regionService.findAll();
		}
		String json = MyJsonUtils.OjectToJson(regions, new String[] {"province","city","district","postcode","shortcode","citycode","subareas"});
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(json);
		
		return null;
	}
	

	public void setRegionFile(File regionFile) {
		this.regionFile = regionFile;
	}

	public void setQ(String q) {
		this.q = q;
	}

	
}
