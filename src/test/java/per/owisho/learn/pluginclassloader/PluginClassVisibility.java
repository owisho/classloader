package per.owisho.learn.pluginclassloader;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;


/**
 * 测试通过插件classloader加载的类对于通过系统加载器加载的类的可见性
 * 以及通过系统类加载器加载的类对插件类加载器加载的类的可见性
 * @author owisho
 *
 */
public class PluginClassVisibility {

	public static void main(String[] args) throws Exception {
		
		URL url = new URL("file:\\D:\\workspace\\java\\learn\\classloader\\extrajar\\plugin.jar");
		URLClassLoader loader = new URLClassLoader(new URL[]{url});
		Class<?> cls = loader.loadClass("Plugin");
		loader.close();
		System.out.println(cls);
		Method method = cls.getMethod("findClass", String.class);
		Object obj = method.invoke(cls.newInstance(), "per.owisho.learn.pluginclassloader.PluginClassVisibility");
		System.out.println(obj);
		PluginClassVisibility p = new PluginClassVisibility();
		try {
			p.findClass("Plugin");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public Class<?> findClass(String className) throws ClassNotFoundException{
		return Class.forName(className);
	}
	
}
