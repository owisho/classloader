package per.owisho.learn.pluginclassloader;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


/**
 * 测试通过插件classloader加载的类对于通过系统加载器加载的类的可见性
 * 以及通过系统类加载器加载的类对插件类加载器加载的类的可见性
 * @author owisho
 *
 */
public class PluginClassVisibility {

	public static void main(String[] args) throws Exception {
		cannotfindsysclass();
	}
	
	public static void canfindsysclass() throws Exception{
		URL url = new URL("file:\\D:\\workspace\\java\\learn\\classloader\\extrajar\\plugin.jar");
		URLClassLoader loader = new URLClassLoader(new URL[]{url});
		Class<?> cls = loader.loadClass("Plugin");
		loader.close();
		System.out.println(cls);
		Method method = cls.getMethod("findClass", String.class);
		Object obj = method.invoke(cls.newInstance(), "per.owisho.learn.pluginclassloader.PluginClassVisibility");
		System.out.println(obj);
	}
	
	public static void canfindpluginclass(){
		PluginClassVisibility p = new PluginClassVisibility();
		try {
			p.findClass("Plugin");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void cannotfindsysclass() throws Exception{
		URL url = new URL("jar",null,"file:\\D:\\workspace\\java\\learn\\classloader\\extrajar\\plugin.jar!/");
		SpecialClassLoader loader = new SpecialClassLoader(url);
		Class<?> cls = loader.loadClass("Plugin");
		System.out.println(cls);
		Method method = cls.getMethod("findClass", String.class);
		Object obj = method.invoke(cls.newInstance(), "per.owisho.learn.pluginclassloader.PluginClassVisibility");
		System.out.println(obj);
	}
	
	public Class<?> findClass(String className) throws ClassNotFoundException{
		return Class.forName(className);
	}
	
}

/**
 * 不使用父类加载器查找类的加载器
 * @author owisho
 *
 */
class SpecialClassLoader extends ClassLoader{
	
	private URL url;//网络地址的根路径
	
	public SpecialClassLoader(URL url){
		super(Thread.currentThread().getContextClassLoader().getParent());
		this.url = url;
	}
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		try {
			JarURLConnection jarconnection = (JarURLConnection)url.openConnection();
			JarFile jar = jarconnection.getJarFile();
			JarEntry entry = jar.getJarEntry(name+".class");
			InputStream in = jar.getInputStream(entry);
			ByteArrayOutputStream b = new ByteArrayOutputStream();
			int read = -1;
			while((read=in.read())!=-1){
				b.write(read);
			}
			return defineClass(name, b.toByteArray(), 0, b.size());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ClassNotFoundException();
		}
	}
	
}
