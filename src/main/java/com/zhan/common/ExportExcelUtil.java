package com.zhan.common;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.NumberFormat;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


public class ExportExcelUtil {
	
	/**
	 * 起始列
	 */
	private static int ROW_START = 0;
	
	public static WritableCellFormat titleFormat;
	public static WritableCellFormat dataFormat;
	public static WritableCellFormat numberFormat;
	
	static{
		try {
			//设置标题格式
			WritableFont titleFont = new WritableFont(WritableFont.ARIAL, 10,
				WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
				jxl.format.Colour.RED);
			titleFormat = new WritableCellFormat(titleFont);
			titleFormat.setWrap(true);
			titleFormat.setAlignment(Alignment.CENTRE);
			titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			
			//设置数据格式
			WritableFont detFont = new WritableFont(WritableFont.ARIAL, 10,
					WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLACK);
			dataFormat = new WritableCellFormat(detFont);
			dataFormat.setWrap(true);
			dataFormat.setAlignment(Alignment.CENTRE);
			dataFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			
			//设置数字格式
			numberFormat = new WritableCellFormat(new NumberFormat("#.##"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 导出excel
	 * @param dataList
	 * @param mapping
	 * @param os
	 * @throws Exception
	 */
	public static void exportExcel(List<?> dataList, List<CellFormatInfo> mapping, OutputStream os) throws Exception{
		WritableWorkbook workbook = null;
		try{
			//创建工作簿
			workbook = Workbook.createWorkbook(os);
			//创建工作表
			WritableSheet sheet = workbook.createSheet("列表", 0);
			//设置标题
			addHeader(sheet, mapping);
			//设置内容
			addContent(sheet, dataList, mapping);
			
			workbook.write();
			os.flush();
		}finally{
			if(workbook != null){
				workbook.close();
			}
			os.close();
		}
	}
	
	/**
	 * 设置标题
	 * @param sheet
	 * @param mapping
	 * @throws Exception
	 */
	private static void addHeader(WritableSheet sheet, List<CellFormatInfo> mapping) throws Exception {
		
		int c = 0;
		for(CellFormatInfo cf : mapping){
			Label label = new Label( c, ROW_START, cf.getTitle(), titleFormat );
			sheet.addCell(label);
			
			sheet.setColumnView( c, cf.getWidth());
			sheet.setRowView(ROW_START, cf.getHeight());
			c++;
		}
	}
	
	/**
	 * 设置内容
	 * @param sheet
	 * @param dataList
	 * @param mapping
	 * @throws Exception
	 */
	private static void addContent(WritableSheet sheet, List<?> dataList, List<CellFormatInfo> mapping) throws Exception {
		
		int rowNum = 1;
		//循环一次是一行
		for(Object rowObj : dataList){
			//循环一次是一列,每行都要初始化columnNum
			int columnNum = 0;
			for( CellFormatInfo cf : mapping ){
				
				WritableCell label = getLabel(columnNum, ROW_START + rowNum, rowObj, cf.getKey());
				sheet.addCell(label);
				sheet.setColumnView( columnNum, cf.getWidth());
				sheet.setRowView(ROW_START + rowNum, cf.getHeight());
				columnNum++;
			}
			rowNum++;
		} 
	} 
	
	/**
	 * 设置单元格
	 * @param columnNum
	 * @param rowNum
	 * @param rowObj
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static WritableCell getLabel(int columnNum, int rowNum, Object rowObj, String key) throws Exception{

		return new Label( columnNum , rowNum, BeanUtils.getProperty(rowObj, key), dataFormat );

	}
		
}

