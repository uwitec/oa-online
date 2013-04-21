package com.fhi.framework.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class JpgTag {
	private synchronized static void jpgTag(String fileUrl, Integer wideth, Integer height)
			throws Exception {
		File _file = new File(fileUrl); // 读入文件　　
		// System.out.println("缩略图文件："+fileUrl);
		Image src = javax.imageio.ImageIO.read(_file); // 构造Image对象　
		int wideth_ = src.getWidth(null); // 得到源图宽　　
		int height_ = src.getHeight(null); // 得到源图长

		// System.out.println("生成缩略图！原文件路径："+fileUrl+","+wideth_+"*"+height_);

		// 设置缩略图大小 1设定高度等比例缩小 2设定宽度等比例缩小 3都不设定自动减半缩小
		if (wideth == null && height != null) {
			wideth = (height.intValue() * wideth_ / height_);
		} else if (wideth != null && height == null) {
			height = (wideth.intValue() * height_ / wideth_);
		} else if (wideth == null && height == null) {
			wideth = wideth_ / 2;
			height = height_ / 2;
		} else {
			if (wideth_/wideth > height_/height) {
				height = (wideth.intValue() * height_ / wideth_);
			} else {
				wideth = (height.intValue() * wideth_ / height_);
			}
		}

		BufferedImage tag = new BufferedImage(wideth, height,
				BufferedImage.TYPE_INT_RGB);// 设置新文件画布大小

		tag.getGraphics().drawImage(src, 0, 0, wideth, height, null); // 绘制缩小后的图

		// 将新文件命名为*_tag.*;
		int e = fileUrl.lastIndexOf(".");
		fileUrl = fileUrl.substring(0, e) + "_tag" + fileUrl.substring(e);
		FileOutputStream out = new FileOutputStream(fileUrl); // 输出到文件流

		// System.out.println("新文件路径："+fileUrl+","+wideth+"*"+height);

		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		
		encoder.encode(tag); // 近JPEG编码

		out.close();
	}

	public synchronized static File downloadPic(File _file, String wideth_in, String height_in)
			throws IOException {
		String fileUrl = _file.getPath();			
		int e = fileUrl.lastIndexOf(".");
		fileUrl = fileUrl.substring(0, e) + "_tag_"+wideth_in+"_"+height_in + fileUrl.substring(e);
		File file_temp = new File(fileUrl);
		if (file_temp.exists()) {
			return file_temp;
		}
		Image src;
		FileOutputStream out = null;
		Integer wideth=wideth_in==null?null:Integer.parseInt(wideth_in);
		Integer height=height_in==null?null:Integer.parseInt(height_in);		
		try {
			src = javax.imageio.ImageIO.read(_file);
			
			// 构造Image对象　
			InputStream input = null;
			int wideth_ = src.getWidth(null); // 得到源图宽　　
			int height_ = src.getHeight(null); // 得到源图长

			// 设置缩略图大小 1设定高度等比例缩小 2设定宽度等比例缩小 3都不设定自动减半缩小
			if (wideth == null && height != null) {
				wideth = (height.intValue() * wideth_ / height_);
			} else if (wideth != null && height == null) {
				height = (wideth.intValue() * height_ / wideth_);
			} else if (wideth == null && height == null) {
				wideth = wideth_ / 2;
				height = height_ / 2;
			} else {
				if (wideth_/wideth.floatValue() > height_/height.floatValue()) {
					height = (wideth.intValue() * height_ / wideth_);
				} else {
					wideth = (height.intValue() * wideth_ / height_);
				}
			}			
			
			BufferedImage tag = new BufferedImage(wideth, height,
					BufferedImage.TYPE_INT_RGB);// 设置新文件画布大小

			tag.getGraphics().drawImage(src, 0, 0, wideth, height, null); // 绘制缩小后的图
			out = new FileOutputStream(file_temp); // 输出到文件流
			
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(tag); // 近JPEG编码
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			out.close();
		}
		return file_temp;
	}

	/**
	 * 生成缩略图 按设置最大宽高输出
	 * 
	 * @param fileUrl
	 * @param wideth
	 * @param height
	 * @throws Exception
	 */
	public static void JpgTag(String fileUrl, Integer wideth, Integer height)
			throws Exception {
		jpgTag(fileUrl, wideth, height);
	}

	/**
	 * 生成缩略图 按设置宽度等比例缩小输出
	 * 
	 * @param fileUrl
	 * @param wideth
	 * @throws Exception
	 */
	public static void JpgTag(String fileUrl, Integer wideth) throws Exception {
		jpgTag(fileUrl, wideth, null);
	}

	/**
	 * 生成缩略图 按设置高度等比例缩小输出
	 * 
	 * @param fileUrl
	 * @param height
	 * @throws Exception
	 */
	public static void JpgTag(String fileUrl, int height) throws Exception {
		jpgTag(fileUrl, null, height);
	}

	/**
	 * 生成缩略图 按原图大小宽高减半输出
	 * 
	 * @param fileUrl
	 * @throws Exception
	 */
	public static void JpgTag(String fileUrl) throws Exception {
		jpgTag(fileUrl, null, null);
	}
}
