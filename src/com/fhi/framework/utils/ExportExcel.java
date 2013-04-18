package com.fhi.framework.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;

import com.fhi.questionnaire.vo.DeptExcelBean;
import com.fhi.questionnaire.vo.VoteListExcelBean;

/**
 * 
 * 利用开源组件POI3.0.2动态导出EXCEL文档
 * 
 * 转载时请保留以下信息，注明出处！
 * 
 * @author 鑫缘
 * 
 * @version v1.3
 * 
 * @param <T>
 *            应用泛型，代表任意一个符合javabean风格的类
 * 
 *            注意这里为了简单起见，boolean型的属性xxx的get器方式为getXxx(),而不是isXxx()
 * 
 *            byte[]表jpg格式的图片数据
 */

public class ExportExcel<T> {

	public void exportExcel(Collection<T> dataset, OutputStream out) throws IOException, SecurityException, NoSuchMethodException {

		exportExcel("系统导出文档", null, null, dataset, out, "yyyy-MM-dd");

	}

	public void exportExcel(String[] headers, Collection<T> dataset,
			OutputStream out) throws IOException, SecurityException, NoSuchMethodException {

		exportExcel("系统导出文档", headers, null, dataset, out, "yyyy-MM-dd");

	}

	public void exportExcel(String[] headers, Collection<T> dataset,

	OutputStream out, String pattern) throws IOException, SecurityException, NoSuchMethodException {

		exportExcel("系统导出文档", headers, null, dataset, out, pattern);

	}

	public void exportExcel(String[] headers, Short[] widths,
			Collection<T> dataset, OutputStream out)  throws IOException, SecurityException, NoSuchMethodException {

		exportExcel("系统导出文档", headers, widths, dataset, out, "yy-MM-dd");

	}

	public void exportExcel(String title, String[] headers, Short[] widths,
			Collection<T> dataset, OutputStream out) throws IOException, SecurityException, NoSuchMethodException {

		exportExcel(title, headers, widths, dataset, out, "yyyy-MM-dd");

	}
	
	public void myExportExcel(String title, String[] headers, Short[] widths,
			Collection<T> dataset, Collection<DeptExcelBean> dataset1,Collection<VoteListExcelBean> dataset2, Collection<VoteListExcelBean> dataset3,List<Integer> answerCount,OutputStream out) throws IOException, SecurityException, NoSuchMethodException {

		myExportExcel(title, headers, widths, dataset, dataset1, dataset2, dataset3,answerCount, out, "yyyy-MM-dd");

	}

	/**
	 * 
	 * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
	 * 
	 * 
	 * 
	 * @param title
	 * 
	 *            表格标题名
	 * 
	 * @param headers
	 * 
	 *            表格属性列名数组
	 * @param widths
	 * 
	 *            表格宽度数组 默认15
	 * @param dataset
	 * 
	 *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
	 * 
	 *            javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
	 * 
	 * @param out
	 * 
	 *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
	 * 
	 * @param pattern
	 * 
	 *            如果有时间数据，设定输出格式。默认为"yyyy-MM-dd"
	 * @throws IOException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 */

	@SuppressWarnings("unchecked")
	public void exportExcel(String title, String[] headers, Short[] widths,
			Collection<T> dataset, OutputStream out, String pattern) throws IOException, SecurityException, NoSuchMethodException {

		// 声明一个工作薄

		HSSFWorkbook workbook = new HSSFWorkbook();

		// 生成一个表格

		HSSFSheet sheet = workbook.createSheet(title);
		 

		// 设置表格默认列宽度为15个字节
		// 设置默认宽度15 字节否则循环设置宽度
		sheet.setDefaultColumnWidth((short) 15);

		if (widths != null) {
			for (int i = 0; i < widths.length; i++) {
				sheet.setColumnWidth((short) i, (short) (widths[i] * 250));
			}
		}

		// ************************************标题样式设置**************************************
		// 生成标题样式

		HSSFCellStyle style = workbook.createCellStyle();
		// 设置这些样式

		style.setFillForegroundColor(HSSFColor.LIME.index);

		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);

		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);

		style.setBorderRight(HSSFCellStyle.BORDER_THIN);

		style.setBorderTop(HSSFCellStyle.BORDER_THIN);

		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中

		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中

		style.setWrapText(true); // 自动换行

		// 生成标题字体

		HSSFFont font = workbook.createFont();

		font.setColor(HSSFColor.BLACK.index);// 字体颜色

		font.setFontHeightInPoints((short) 9);// 字号

		font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);// 是否加粗

		// 把字体应用到当前的标题样式

		style.setFont(font);
		// ************************************数据样式设置**************************************
		// 生成并设置数据样式

		HSSFCellStyle style2 = workbook.createCellStyle();

		style2.setFillForegroundColor(HSSFColor.WHITE.index);

		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);

		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);

		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);

		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);

		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		style2.setWrapText(true); // 自动换行

		// 生成数据字体

		HSSFFont font2 = workbook.createFont();

		font2.setFontHeightInPoints((short) 9);

		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);

		// 把字体应用到当前的样式

		style2.setFont(font2);
		// ************************************数据样式设置结束**************************************
		// 声明一个画图的顶级管理器

		// HSSFPatriarch patriarch = sheet.createDrawingPatriarch();

		// 定义注释的大小和位置,详见文档

		// HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,
		// 0, 0, 0, (short) 4, 2, (short) 6, 5));

		// 设置注释内容

		// comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));

		// 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.

		// comment.setAuthor("leno");

		// 产生表格标题行
		HSSFRow row = sheet.createRow(0);
		 
		for (short i = 0; i < headers.length; i++) {

			HSSFCell cell = row.createCell(i);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);

			cell.setCellStyle(style);

			HSSFRichTextString text = new HSSFRichTextString(headers[i]);

			cell.setCellValue(text);

		}
		
		

		// 遍历集合数据，产生数据行
		Class tCls = null;
		List<Method> methodList = new ArrayList<Method>();
		
		if(dataset.size()>0){
			tCls = dataset.iterator().next().getClass();
			
			Field[] fields = tCls.getDeclaredFields();
			for(int i=0;i<fields.length;i++){
				
				Field field = fields[i];
				
				String fieldName = field.getName();

				String getMethodName = "get"+fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				
				methodList.add(tCls.getMethod(getMethodName,new Class[] {}));				
			}
			
			Iterator<T> it = dataset.iterator();	

			int index = 0;

			while (it.hasNext()) {
				index++;
				// 获取列
				row = sheet.createRow(index);
				//取出当前对象
				T t = (T) it.next();
				//循环插入列
				for (short i = 0; i < methodList.size(); i++) {
					// 获取行
					HSSFCell cell = row.createCell(i);
					// 载入样式
					cell.setCellStyle(style2);

					try {

						//通过之前获取的方法名取出当前行对象中的当前列数据
						Object value = methodList.get(i).invoke(t, new Object[] {});

						// 判断值的类型后进行强制类型转换

						String textValue = null;


						if (value==null) {
							
							textValue = "";
							
						} else if (value instanceof String) {
							
							textValue = value.toString();
							
						} else if (value instanceof Boolean) {
							
							textValue=(Boolean) value?"是":"否";
							
						} else if (value instanceof Date) {

							Date date = (Date) value;

							SimpleDateFormat sdf = new SimpleDateFormat(pattern);

							textValue = sdf.format(date);

						} else if (value instanceof Float) {
							DecimalFormat df = new DecimalFormat();
							df.applyPattern("#.##");
							textValue = df.format(value);
						} else if (value instanceof Integer) {
							DecimalFormat df = new DecimalFormat();
							df.applyPattern("#");
							textValue = df.format(value);
						} else if (value instanceof Double) {
							DecimalFormat df = new DecimalFormat();
							df.applyPattern("#.##");
							textValue = df.format(value);
						}else if (value instanceof BigDecimal) {
							DecimalFormat df = new DecimalFormat();
							df.applyPattern("#.##");
							textValue = df.format(value);
						}else {							
							textValue = "";							
						}

						// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成

						if (textValue != null) {

							Pattern p = Pattern.compile("^\\d+(\\.\\d+)?$");

							Matcher matcher = p.matcher(textValue);

							if (matcher.matches()) {

								// 是数字当作double处理

								cell.setCellValue(Double.parseDouble(textValue));

							} else {

								HSSFRichTextString richString = new HSSFRichTextString(textValue);
								
								cell.setCellValue(richString);

							}
						}

					} catch (SecurityException e) {

						// TODO Auto-generated catch block

						e.printStackTrace();

					} catch (IllegalArgumentException e) {

						// TODO Auto-generated catch block

						e.printStackTrace();

					} catch (IllegalAccessException e) {

						// TODO Auto-generated catch block

						e.printStackTrace();

					} catch (InvocationTargetException e) {

						// TODO Auto-generated catch block

						e.printStackTrace();

					} finally {
						
						// 清理资源
					}

				}

			}
			
		}
		workbook.write(out);
	}
	
	/**
	 * 
	 * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
	 * 
	 * 
	 * 
	 * @param title
	 * 
	 *            表格标题名
	 * 
	 * @param headers
	 * 
	 *            表格属性列名数组
	 * @param widths
	 * 
	 *            表格宽度数组 默认15
	 * @param dataset
	 * 
	 *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
	 * 
	 *            javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
	 * 
	 * @param out
	 * 
	 *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
	 * 
	 * @param pattern
	 * 
	 *            如果有时间数据，设定输出格式。默认为"yyyy-MM-dd"
	 * @throws IOException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 */

	@SuppressWarnings("unchecked")
	public void myExportExcel(String title, String[] headers, Short[] widths,
			Collection<T> dataset,Collection<DeptExcelBean> deptDataSet, Collection<VoteListExcelBean> votedDataSet,Collection<VoteListExcelBean> unVotedDataSet,List<Integer> answercount, OutputStream out, String pattern) throws IOException, SecurityException, NoSuchMethodException {

		// 声明一个工作薄

		HSSFWorkbook workbook = new HSSFWorkbook();

		// 生成一个表格

		HSSFSheet sheet = workbook.createSheet(title);
		/***
		 * 部门统计导出
		 * */
		HSSFSheet deptSheet = workbook.createSheet("各部门问卷投票情况表");
		String[] deptHeader={"部门名称","各部门投票人数","各部门总人数","投票率"};
	    Short[] deptWidths={45,20,20,20};
	    
	    
	    if (deptWidths != null) {
			for (int i = 0; i < deptWidths.length; i++) {
				deptSheet.setColumnWidth((short) i, (short) (deptWidths[i] * 250));
			}
		}
		
		/**
		 * 
		 * */
	    
	    
	    /***
		 *已投票统计表
		 * */
		HSSFSheet votedSheet = workbook.createSheet("各部门已投票情况统计表");
		String[] votedHeader={"部门名称","姓名"};
	    Short[] votedWidths={40,20};
	    
	    
	    if (votedWidths != null) {
			for (int i = 0; i < votedWidths.length; i++) {
				votedSheet.setColumnWidth((short) i, (short) (votedWidths[i] * 250));
			}
		}
		
		/**
		 * 
		 * */
	    
	    
	    /***
		 *未投票统计表
		 * */
		HSSFSheet unVotedSheet = workbook.createSheet("各部门未投票情况统计表");
		String[] unVotedHeader={"部门名称","姓名"};
	    Short[] unVotedWidths={40,20};
	    
	    
	    if (unVotedWidths != null) {
			for (int i = 0; i < unVotedWidths.length; i++) {
				unVotedSheet.setColumnWidth((short) i, (short) (unVotedWidths[i] * 250));
			}
		}
		
		/**
		 * 
		 * */

		// 设置表格默认列宽度为15个字节
		// 设置默认宽度15 字节否则循环设置宽度
		sheet.setDefaultColumnWidth((short) 15);

		if (widths != null) {
			for (int i = 0; i < widths.length; i++) {
				sheet.setColumnWidth((short) i, (short) (widths[i] * 250));
			}
		}

		// ************************************标题样式设置**************************************
		// 生成标题样式

		HSSFCellStyle style = workbook.createCellStyle();
		// 设置这些样式

		style.setFillForegroundColor(HSSFColor.LIME.index);

		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);

		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);

		style.setBorderRight(HSSFCellStyle.BORDER_THIN);

		style.setBorderTop(HSSFCellStyle.BORDER_THIN);

		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中

		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中

		style.setWrapText(true); // 自动换行

		// 生成标题字体

		HSSFFont font = workbook.createFont();

		font.setColor(HSSFColor.BLACK.index);// 字体颜色

		font.setFontHeightInPoints((short) 9);// 字号

		font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);// 是否加粗

		// 把字体应用到当前的标题样式

		style.setFont(font);
		// ************************************数据样式设置**************************************
		// 生成并设置数据样式

		HSSFCellStyle style2 = workbook.createCellStyle();

		style2.setFillForegroundColor(HSSFColor.WHITE.index);

		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);

		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);

		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);

		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);

		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		style2.setWrapText(true); // 自动换行

		// 生成数据字体

		HSSFFont font2 = workbook.createFont();

		font2.setFontHeightInPoints((short) 9);

		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);

		// 把字体应用到当前的样式

		style2.setFont(font2);
		// ************************************数据样式设置结束**************************************
		// 声明一个画图的顶级管理器

		// HSSFPatriarch patriarch = sheet.createDrawingPatriarch();

		// 定义注释的大小和位置,详见文档

		// HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,
		// 0, 0, 0, (short) 4, 2, (short) 6, 5));

		// 设置注释内容

		// comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));

		// 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.

		// comment.setAuthor("leno");

		// 产生表格标题行
		HSSFRow row = sheet.createRow(0);
		for (short i = 0; i < headers.length; i++) {

			HSSFCell cell = row.createCell(i);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);

			cell.setCellStyle(style);

			HSSFRichTextString text = new HSSFRichTextString(headers[i]);

			cell.setCellValue(text);

		}
		/***
		 * 	dept 产生表格标题行
		 * 
		 * */
		HSSFRow deptRow = deptSheet.createRow(0);
		for (short i = 0; i < deptHeader.length; i++) {

			HSSFCell cell = deptRow.createCell(i);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);

			cell.setCellStyle(style);

			HSSFRichTextString text = new HSSFRichTextString(deptHeader[i]);

			cell.setCellValue(text);

		}
		
		/***
		 * 	votedList （以投票统计表）产生表格标题行 start
		 * 
		 * */
		HSSFRow votedRow = votedSheet.createRow(0);
		for (short i = 0; i < votedHeader.length; i++) {

			HSSFCell cell = votedRow.createCell(i);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);

			cell.setCellStyle(style);

			HSSFRichTextString text = new HSSFRichTextString(votedHeader[i]);

			cell.setCellValue(text);

		}
		/***
		 * 	votedList （以投票统计表）产生表格标题行  end 
		 * 
		 * */
		
		
		/***
		 * 	unVotedList （未投票统计表）产生表格标题行 start
		 * 
		 * */
		HSSFRow unVotedRow = unVotedSheet.createRow(0);
		for (short i = 0; i < unVotedHeader.length; i++) {

			HSSFCell cell = unVotedRow.createCell(i);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);

			cell.setCellStyle(style);

			HSSFRichTextString text = new HSSFRichTextString(unVotedHeader[i]);

			cell.setCellValue(text);

		}
		/***
		 * 	votedList （以投票统计表）产生表格标题行  end 
		 * 
		 * */
		//遍历集合
		Class depttCls = null;
		List<Method> deptMethodList = new ArrayList<Method>();//确定列数
		if(deptDataSet.size()>0){
			depttCls = deptDataSet.iterator().next().getClass();
			Field[] fields = depttCls.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				String fieldName = field.getName();
				String getMethodName = "get"+fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				deptMethodList.add(depttCls.getMethod(getMethodName,new Class[] {}));	

			}
			int k = 0;
			Iterator<DeptExcelBean> deptIterator = deptDataSet.iterator();	
			while (deptIterator.hasNext()) {
				k++;
				// 获取列
				deptRow = deptSheet.createRow(k);
				DeptExcelBean deptExcelBean = (DeptExcelBean) deptIterator.next();
				//循环插入列
				for (short i = 0; i < deptMethodList.size(); i++) {
					// 获取行
					HSSFCell cell = deptRow.createCell(i);
					//HSSFCell pCell = row.getCell(Short.parseShort("1"));
					
					// 载入样式
					cell.setCellStyle(style2);
					

					try {
						 
						//通过之前获取的方法名取出当前行对象中的当前列数据
						Object value = deptMethodList.get(i).invoke(deptExcelBean, new Object[] {});
						 
						// 判断值的类型后进行强制类型转换

						String textValue = null;


						if (value==null) {
							
							textValue = "";
							
						} else if (value instanceof String) {
							
							textValue = value.toString();
							
						} else if (value instanceof Boolean) {
							
							textValue=(Boolean) value?"是":"否";
							
						} else if (value instanceof Date) {

							Date date = (Date) value;

							SimpleDateFormat sdf = new SimpleDateFormat(pattern);

							textValue = sdf.format(date);

						} else if (value instanceof Float) {
							DecimalFormat df = new DecimalFormat();
							df.applyPattern("#.##");
							textValue = df.format(value);
						} else if (value instanceof Integer) {
							DecimalFormat df = new DecimalFormat();
							df.applyPattern("#");
							textValue = df.format(value);
						} else if (value instanceof Double) {
							DecimalFormat df = new DecimalFormat();
							df.applyPattern("#.##");
							textValue = df.format(value);
						}else if (value instanceof BigDecimal) {
							DecimalFormat df = new DecimalFormat();
							df.applyPattern("#.##");
							textValue = df.format(value);
						}else {							
							textValue = "";							
						}

						// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成

						if (textValue != null) {
								HSSFRichTextString richString = new HSSFRichTextString(textValue);
								cell.setCellValue(richString);
						 
						}

					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						
						// 清理资源
					}

				}
				
			}
			
		}
		/***
		 * 
		 * end 
		 * */
		
		/**
		 * 投票人数 start  遍历集合
		 * 
		 * */
		//遍历集合
		Class votedCls = null;
		List<Method> votedMethodList = new ArrayList<Method>();//确定列数
		if(votedDataSet.size()>0){
			votedCls = votedDataSet.iterator().next().getClass();
			Field[] fields = votedCls.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				String fieldName = field.getName();
				String getMethodName = "get"+fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				votedMethodList.add(votedCls.getMethod(getMethodName,new Class[] {}));	

			}
			int y = 0;
			Iterator<VoteListExcelBean>  votedIterator = votedDataSet.iterator();	
			while (votedIterator.hasNext()) {
				y++;
				// 获取列
				votedRow = votedSheet.createRow(y);
				VoteListExcelBean voteListExcelBean = (VoteListExcelBean) votedIterator.next();
				//循环插入列
				for (short i = 0; i < votedMethodList.size(); i++) {
					// 获取行
					HSSFCell cell = votedRow.createCell(i);
					//HSSFCell pCell = row.getCell(Short.parseShort("1"));
					
					// 载入样式
					cell.setCellStyle(style2);
					

					try {
						 
						//通过之前获取的方法名取出当前行对象中的当前列数据
						Object value = votedMethodList.get(i).invoke(voteListExcelBean, new Object[] {});
						 
						// 判断值的类型后进行强制类型转换

						String textValue = null;


						if (value==null) {
							
							textValue = "";
							
						} else if (value instanceof String) {
							
							textValue = value.toString();
							
						} else if (value instanceof Boolean) {
							
							textValue=(Boolean) value?"是":"否";
							
						} else if (value instanceof Date) {

							Date date = (Date) value;

							SimpleDateFormat sdf = new SimpleDateFormat(pattern);

							textValue = sdf.format(date);

						} else if (value instanceof Float) {
							DecimalFormat df = new DecimalFormat();
							df.applyPattern("#.##");
							textValue = df.format(value);
						} else if (value instanceof Integer) {
							DecimalFormat df = new DecimalFormat();
							df.applyPattern("#");
							textValue = df.format(value);
						} else if (value instanceof Double) {
							DecimalFormat df = new DecimalFormat();
							df.applyPattern("#.##");
							textValue = df.format(value);
						}else if (value instanceof BigDecimal) {
							DecimalFormat df = new DecimalFormat();
							df.applyPattern("#.##");
							textValue = df.format(value);
						}else {							
							textValue = "";							
						}

						// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成

						if (textValue != null) {
								HSSFRichTextString richString = new HSSFRichTextString(textValue);
								cell.setCellValue(richString);
						 
						}

					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						
						// 清理资源
					}

				}
				
			}
			
		}
		/**
		 * 投票人数 end
		 * 
		 * */
		/**
		 * 未投票人数 start  遍历集合
		 * 
		 * */
		//遍历集合
		Class unVotedCls = null;
		List<Method> unVotedMethodList = new ArrayList<Method>();//确定列数
		if(unVotedDataSet.size()>0){
			unVotedCls = unVotedDataSet.iterator().next().getClass();
			Field[] fields = unVotedCls.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				String fieldName = field.getName();
				String getMethodName = "get"+fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				unVotedMethodList.add(unVotedCls.getMethod(getMethodName,new Class[] {}));	

			}
			int x = 0;
			Iterator<VoteListExcelBean>  unVotedIterator = unVotedDataSet.iterator();	
			while (unVotedIterator.hasNext()) {
				x++;
				// 获取列
				unVotedRow = unVotedSheet.createRow(x);
				VoteListExcelBean  unVoteListExcelBean = (VoteListExcelBean) unVotedIterator.next();
				//循环插入列
				for (short i = 0; i < unVotedMethodList.size(); i++) {
					// 获取行
					HSSFCell cell = unVotedRow.createCell(i);
					//HSSFCell pCell = row.getCell(Short.parseShort("1"));
					
					// 载入样式
					cell.setCellStyle(style2);
					

					try {
						 
						//通过之前获取的方法名取出当前行对象中的当前列数据
						Object value = unVotedMethodList.get(i).invoke(unVoteListExcelBean, new Object[] {});
						 
						// 判断值的类型后进行强制类型转换

						String textValue = null;


						if (value==null) {
							
							textValue = "";
							
						} else if (value instanceof String) {
							
							textValue = value.toString();
							
						} else if (value instanceof Boolean) {
							
							textValue=(Boolean) value?"是":"否";
							
						} else if (value instanceof Date) {

							Date date = (Date) value;

							SimpleDateFormat sdf = new SimpleDateFormat(pattern);

							textValue = sdf.format(date);

						} else if (value instanceof Float) {
							DecimalFormat df = new DecimalFormat();
							df.applyPattern("#.##");
							textValue = df.format(value);
						} else if (value instanceof Integer) {
							DecimalFormat df = new DecimalFormat();
							df.applyPattern("#");
							textValue = df.format(value);
						} else if (value instanceof Double) {
							DecimalFormat df = new DecimalFormat();
							df.applyPattern("#.##");
							textValue = df.format(value);
						}else if (value instanceof BigDecimal) {
							DecimalFormat df = new DecimalFormat();
							df.applyPattern("#.##");
							textValue = df.format(value);
						}else {							
							textValue = "";							
						}

						// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成

						if (textValue != null) {
								HSSFRichTextString richString = new HSSFRichTextString(textValue);
								cell.setCellValue(richString);
						 
						}

					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						
						// 清理资源
					}

				}
				
			}
			
		}
		/**
		 * 未投票人数 end
		 * 
		 * */

		// 遍历集合数据，产生数据行
		Class tCls = null;
		List<Method> methodList = new ArrayList<Method>();
		
		if(dataset.size()>0){
			tCls = dataset.iterator().next().getClass();
			
			Field[] fields = tCls.getDeclaredFields();
			for(int i=0;i<fields.length;i++){
				
				Field field = fields[i];
				
				String fieldName = field.getName();

				String getMethodName = "get"+fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				if( !"questionnaireId".equals(fieldName) && !"id".equals(fieldName)&& !"questionId".equals(fieldName) && !"answerCount".equals(fieldName) ){//
					methodList.add(tCls.getMethod(getMethodName,new Class[] {}));	
				}
							
			}
			
			Iterator<T> it = dataset.iterator();	

			int index = 0;
			int start = 1;
			int  end = 0;
           
			while (it.hasNext()) {
				index++;
				// 获取列
				row = sheet.createRow(index);
				
				//取出当前对象
				T t = (T) it.next();
				//循环插入列
				if (end>=answercount.size()) {
					end = answercount.size();
					
				}else {
					end = (start+answercount.get(start-1))-1;
					sheet.addMergedRegion(new Region(start, (short) 0, end, (short) 0));
				}
				
				
				System.out.println("sheet.addMergedRegion(new Region("+start+", (short) 0,"+ end+", (short) 0))");
				
				if(end!=answercount.size()){
					start = end + 1;
				}
			
				for (short i = 0; i < methodList.size(); i++) {
					// 获取行
					HSSFCell cell = row.createCell(i);
					//HSSFCell pCell = row.getCell(Short.parseShort("1"));
					
					// 载入样式
					cell.setCellStyle(style2);
					

					try {
						 
						//通过之前获取的方法名取出当前行对象中的当前列数据
						Object value = methodList.get(i).invoke(t, new Object[] {});
						 
						// 判断值的类型后进行强制类型转换

						String textValue = null;


						if (value==null) {
							
							textValue = "";
							
						} else if (value instanceof String) {
							
							textValue = value.toString();
							
						} else if (value instanceof Boolean) {
							
							textValue=(Boolean) value?"是":"否";
							
						} else if (value instanceof Date) {

							Date date = (Date) value;

							SimpleDateFormat sdf = new SimpleDateFormat(pattern);

							textValue = sdf.format(date);

						} else if (value instanceof Float) {
							DecimalFormat df = new DecimalFormat();
							df.applyPattern("#.##");
							textValue = df.format(value);
						} else if (value instanceof Integer) {
							DecimalFormat df = new DecimalFormat();
							df.applyPattern("#");
							textValue = df.format(value);
						} else if (value instanceof Double) {
							DecimalFormat df = new DecimalFormat();
							df.applyPattern("#.##");
							textValue = df.format(value);
						}else if (value instanceof BigDecimal) {
							DecimalFormat df = new DecimalFormat();
							df.applyPattern("#.##");
							textValue = df.format(value);
						}else {							
							textValue = "";							
						}

						// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成

						if (textValue != null) {

						//	Pattern p = Pattern.compile("^\\d+(\\.\\d+)?$");
								HSSFRichTextString richString = new HSSFRichTextString(textValue);
								 
								 //Integer vInteger = Integer.parseInt(val)-1;
							     //  end = start+vInteger;
								 // 四个参数分别是：起始行，起始列，结束行，结束列    
								//sheet.addMergedRegion(new Region(start, (short) 0, end, (short) 0));
								
								/*//sheet.addMergedRegion(new Region(1, (short) 0, 3, (short) 0));
								sheet.addMergedRegion(new Region(4, (short) 0, 6, (short) 0));
								sheet.addMergedRegion(new Region(7, (short) 0, 8, (short) 0));
								sheet.addMergedRegion(new Region(9, (short) 0, 10, (short) 0));
								sheet.addMergedRegion(new Region(11, (short) 0, 12, (short) 0));
								sheet.addMergedRegion(new Region(13, (short) 0, 19, (short) 0));
								sheet.addMergedRegion(new Region(20, (short) 0, 21, (short) 0));
								sheet.addMergedRegion(new Region(22, (short) 0, 23, (short) 0));
								sheet.addMergedRegion(new Region(24, (short) 0, 25, (short) 0));
								sheet.addMergedRegion(new Region(26, (short) 0, 27, (short) 0));
								sheet.addMergedRegion(new Region(28, (short) 0, 30, (short) 0));
								sheet.addMergedRegion(new Region(31, (short) 0, 32, (short) 0));
								sheet.addMergedRegion(new Region(33, (short) 0, 34, (short) 0));
								sheet.addMergedRegion(new Region(35, (short) 0, 36, (short) 0));
								sheet.addMergedRegion(new Region(37, (short) 0, 44, (short) 0));
								sheet.addMergedRegion(new Region(45, (short) 0, 48, (short) 0));
								sheet.addMergedRegion(new Region(49, (short) 0, 50, (short) 0));*/
								 
								
								
								cell.setCellValue(richString);
						 
						}
 
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						
						// 清理资源
					}

				}

			}
			
		}
		workbook.write(out);
	}
	

}
