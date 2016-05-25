package per.owisho.learn.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 加密工具类
 * @author Administrator
 *
 */
public class EncryptUtil {

	/**
	 * 加密规则：按位取反  111100 --> 000011
	 * @param src
	 * @param dest
	 */
	public static void encrypt(String src,String dest){
		FileInputStream in = null;
		FileOutputStream os = null;
		try {
			in = new FileInputStream(src);
			os = new FileOutputStream(dest);
			int read = 0;
			while((read=in.read())!=-1){
				os.write(read^0xff);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(null!=in){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(null!=os){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
