package com.fhi.custom.vo;

import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class PoiInData {
	
	public List<FhiOaCustom> getData(String creator){
		

		
		String strPath = "c:\\"+creator+".xls";
		
		HSSFWorkbook wb;
		
		try {
			wb = new HSSFWorkbook(new FileInputStream(strPath));
			
			HSSFSheet sheet = wb.getSheetAt(0);
			
			HSSFRow row;
			
			HSSFRichTextString cell;
			List<FhiOaCustom> list = new ArrayList<FhiOaCustom>();
			
			System.out.println("------------"+sheet.getPhysicalNumberOfRows());
			
			for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
				System.out.print(i+"|");
				row = sheet.getRow(i);
				FhiOaCustom c= new FhiOaCustom();
				
				cell = row.getCell((short) 0).getRichStringCellValue();
				System.out.print(cell+"|");
				c.setFullName(cell.getString());
				
				cell = row.getCell((short) 1).getRichStringCellValue();
				System.out.print(cell+"|");
				c.setAddress(cell.getString());
				
				cell = row.getCell((short) 2).getRichStringCellValue();
				System.out.print(cell+"|");
				c.setContactPerson(cell.getString());
				
				
				HSSFCell cc = row.getCell((short) 3);
				if(cc.getCellType()==0){
					 DecimalFormat df1 = new DecimalFormat("#");
					  c.setPhone(df1.format(cc.getNumericCellValue()));
					  System.out.println(c.getPhone()+"|");
				}
				else{
					cell = cc.getRichStringCellValue();
					System.out.println(cell+"|");
					c.setPhone(cell.getString());
				}
//				
//				cc = row.getCell((short) 4);				
//				if(cc.getCellType()!=0){
//					DecimalFormat df1 = new DecimalFormat("#");
//					  c.setMobile(df1.format(cc.getNumericCellValue()));
//				}

				c.setCreator(creator);
				
				c.setAddDate(new Date());
				
				list.add(c);
			}
			
			return list;
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}
