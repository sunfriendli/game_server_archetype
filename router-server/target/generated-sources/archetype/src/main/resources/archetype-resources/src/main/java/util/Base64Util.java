#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Util {
	
	public static String GetImageStr(String imgUrl) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(imgUrl);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);// 返回Base64编码过的字节数组字符串
	}

	public static boolean GenerateImage(String imgStr,String path) {// 对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null) // 图像数据为空
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			/*File file = new File(path);
			if(!file.exists()){  
				file.mkdirs();  
	        }*/
			// 生成jpeg图片
			OutputStream out = new FileOutputStream(path);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static void main(String[] args) {
	//	String pic = "data:image/jpeg;base64,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";
		String str = "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAYEBQYFBAYGBQYHBwYI…9a523/AOPiMds1saexW8mQcKRnHvnFduX1eWdu5GLjdXNGij0or3zzBKWik70ALRigUUDP/9k=";
		String[] split = str.split(",");
		String strImg = split[1];
		GenerateImage(strImg,"f://11.jpg");
	}

}