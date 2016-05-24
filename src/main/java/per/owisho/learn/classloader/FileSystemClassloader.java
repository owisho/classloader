package per.owisho.learn.classloader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 学习自定义类加载器
 * @author owisho
 *
 */
public class FileSystemClassloader extends ClassLoader{
	
	private String rootPath;//存放加载器根路径
	
	public FileSystemClassloader(String rootPath){
		this.rootPath = rootPath;
	}

	/**
	 * 重写父类的查找类方法
	 */
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		String className = rootPath + name.replace(".", "/")+".class";
		FileInputStream in = null;
		byte[] classbytes = null;
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			in = new FileInputStream(className);
			byte[] b = new byte[1024];
			int len = -1;
			while((len=in.read(b))!=-1){
				os.write(b, 0, len);
			}
			classbytes = os.toByteArray();
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
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Class<?> c = defineClass(name, classbytes, 0, classbytes.length);
		if(c!=null){
			return c;
		}else{
			return super.findClass(className);
		}
	}
	
	
	
	
}
