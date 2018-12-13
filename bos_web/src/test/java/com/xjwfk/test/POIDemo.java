package com.xjwfk.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Test;

public class POIDemo {
	
	@Test
	public void apple() throws FileNotFoundException, IOException {
		System.out.println("早上好");
		String filePath = "J:\\【阶段11】物流BOS系统\\BOS-day05\\BOS-day05\\资料\\分区导入测试数据.xls";
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(filePath));
		HSSFSheet sheet = workbook.getSheetAt(0);
		for(Row row:sheet) {
			for(Cell cell:row) {
				String cellValue = cell.getStringCellValue();
				System.out.print(cellValue + "  ");
			}
			System.out.println();
		}
		
	}
}
