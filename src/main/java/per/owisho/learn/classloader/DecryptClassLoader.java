package per.owisho.learn.classloader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 解密类加载器，加载使用加密工具加密过的类文件
 * @author Administrator
 *
 */
public class DecryptClassLoader extends ClassLoader{

	private String rootDir;//类加载器加载文件的根路径
	
	public DecryptClassLoader(String rootDir) {
		this.rootDir = rootDir;
	}
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		
		String classDir = rootDir + name.replace(".", "/")+".class";
		FileInputStream in =null;
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			in = new FileInputStream(classDir);
			int read=-1;
			while((read=in.read())!=-1){
				os.write(read^0xff);
			}
			Class<?> c = defineClass(name, os.toByteArray(), 0, os.toByteArray().length);
			if(null!=c){
				return c;
			}else{
				return super.findClass(name);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return super.findClass(name);
		} finally{
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
