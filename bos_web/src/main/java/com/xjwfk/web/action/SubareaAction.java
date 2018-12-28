package com.xjwfk.web.action;

import java.io.IOException;
import java.util.List;

import javax.print.DocFlavor.STRING;
import javax.servlet.ServletOutputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xjwfk.domain.Region;
import com.xjwfk.domain.Subarea;
import com.xjwfk.service.SubareaService;
import com.xjwfk.utils.FileUtils;
import com.xjwfk.utils.MyJsonUtils;
import com.xjwfk.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea> {
	@Autowired
	private SubareaService subareaService;

	public String save() {
		subareaService.save(model);
		return "save_sucess";
	}

	/* 这个分页查询里有条件查询的功能 */
	public String pageQuery() throws IOException {
		Region region = model.getRegion();
		String addresskey = model.getAddresskey();
		DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();

		if (region != null) {
			String province = region.getProvince();
			String city = region.getCity();
			String district = region.getDistrict();

			detachedCriteria.createAlias("region", "r");
			/* 写了上面这行代码相当于告诉hibernate我要进行多表查询,就会查询关联相关的表了 */
			/* 下面这些if都是对用户传来的查询条件进行模糊查询 */
			if (StringUtils.isNotBlank(province)) {
				detachedCriteria.add(Restrictions.like("r.province", "%" + province + "%"));
			}
			if (StringUtils.isNotBlank(city)) {
				detachedCriteria.add(Restrictions.like("r.city", "%" + city + "%"));
			}
			if (StringUtils.isNotBlank(district)) {
				detachedCriteria.add(Restrictions.like("r.district", "%" + district + "%"));
			}
		}
		if (addresskey != null) {
			if (StringUtils.isNotBlank(addresskey)) {
				detachedCriteria.add(Restrictions.like("addresskey", "%" + addresskey + "%"));
			}

		}

		subareaService.pageQuery(pageBean);
		String json = MyJsonUtils.OjectToJson(pageBean, new String[] { "page", "pageSize", "detachedCriteria",
				"decidedzone", "region.id", "postcode", "shortcode", "citycode", "subareas" });
		System.out.println(json);
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(json);
		return null;
	}

	public String exportXls() throws IOException {
		List<Subarea> subareas = subareaService.findAll(); // 在数据库中获得了所有的分区数据

		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(); // 在内存创建了一个excel文件
		/* 下面直到第一行空白行都是往excel文件填充内容 */
		HSSFSheet sheet = hssfWorkbook.createSheet(); // 创建一个标签页
		HSSFRow headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("分区编号");
		headerRow.createCell(1).setCellValue("开始编号");
		headerRow.createCell(2).setCellValue("结束编号");
		headerRow.createCell(3).setCellValue("位置信息");
		headerRow.createCell(4).setCellValue("省市区");
		for (Subarea subarea : subareas) {
			HSSFRow row = sheet.createRow(sheet.getLastRowNum() + 1);
			row.createCell(0).setCellValue(subarea.getId());
			row.createCell(1).setCellValue(subarea.getStartnum());
			row.createCell(2).setCellValue(subarea.getEndnum());
			row.createCell(3).setCellValue(subarea.getPosition());
			row.createCell(4).setCellValue(subarea.getRegion().getName());
		}

		String agent = ServletActionContext.getRequest().getHeader("User-Agent"); // 获得浏览器信息
		String filename = FileUtils.encodeDownloadFilename("分区数据.xls", agent); // 这样编码之后文件名才不会中文乱码
		System.out.println(filename);
		ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename=" + filename);// 设置响应头的信息，与文件名有关
		ServletOutputStream outputStream = ServletActionContext.getResponse().getOutputStream();// 获得输出流
		hssfWorkbook.write(outputStream);// 向客户端进行输出,就是下载啦
		return null;
	}

	public String listAjax() throws IOException {
		List<Subarea> subareas = null;
		detachedCriteria.add(Restrictions.isNull("decidedzone")); // 过滤已经已经关联到定区的分区
		subareas = subareaService.findList_notAssociate(detachedCriteria);

		String json = MyJsonUtils.OjectToJson(subareas,
				new String[] { "id", "decidedzone", "region", "startnum", "endnum", "single" });
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(json);
		return null;
	}

	private String decidedzoneId;

	public String findListByDecidedzoneId() throws IOException {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Subarea.class);
		detachedCriteria.add(Restrictions.eq("decidedzone.id", decidedzoneId));
		
		List<Subarea> subareas = subareaService.findListByDecidedzoneId(detachedCriteria);
		String json = MyJsonUtils.OjectToJson(subareas, new String[]{"decidedzone","subareas"});
		ServletActionContext.getResponse().setContentType("html/text;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(json);
		return null;
	}

	public void setDecidedzoneId(String decidedzoneId) {
		this.decidedzoneId = decidedzoneId;
	}

}
