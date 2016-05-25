package per.owisho.learn.utils;

import java.io.File;

import org.junit.Test;

/**
 * 加密工具测试类
 * @author Administrator
 *
 */
public class EncryptUtilTest {

	/**
	 * 加密测试：因为加密方法不支持路径创建功能，所以解密文件要放置的目录需要手动创建
	 */
	@Test
	public void encrypt(){
		
		File dest = new File("D:/classes/encrypt/per/owisho/helloworld");
		if(!dest.exists()){
			dest.mkdirs();
		}
		EncryptUtil.encrypt("D:/classes/per/owisho/helloworld/HelloWorld.class", "D:/classes/encrypt/per/owisho/helloworld/HelloWorld.class");
		
	}
	
}
