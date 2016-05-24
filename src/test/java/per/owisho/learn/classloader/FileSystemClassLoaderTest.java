package per.owisho.learn.classloader;

import org.junit.Test;

/**
 * 自定义类加载器测试
 * @author owisho
 *
 */
public class FileSystemClassLoaderTest {

	/**
	 * 测试普通类加载
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void normalLoad() throws ClassNotFoundException{
		FileSystemClassloader classLoader = new FileSystemClassloader("D:/classes/");
		Class<?> c = classLoader.loadClass("per.owisho.helloworld.HelloWorld");
		System.out.println(c);
	}
	
	/**
	 * 测试同一个类加载器两次加载同一个类是否相同
	 * @throws ClassNotFoundException
	 */
	@Test
	public void twiceLoad() throws ClassNotFoundException{
		FileSystemClassloader classLoader = new FileSystemClassloader("D:/classes/");
		Class<?> c = classLoader.loadClass("per.owisho.helloworld.HelloWorld");
		Class<?> c1 = classLoader.loadClass("per.owisho.helloworld.HelloWorld");
		System.out.println(c.hashCode());
		System.out.println(c1.hashCode());
		System.out.println(c==c1);
	}
	
	/**
	 * 测试两个不同的类加载器加载同一个类文件
	 * @throws ClassNotFoundException
	 */
	@Test
	public void twoClassLoader() throws ClassNotFoundException{
		FileSystemClassloader classLoader = new FileSystemClassloader("D:/classes/");
		FileSystemClassloader classLoader1 = new FileSystemClassloader("D:/classes/");
		Class<?> c = classLoader.loadClass("per.owisho.helloworld.HelloWorld");
		Class<?> c1 = classLoader1.loadClass("per.owisho.helloworld.HelloWorld");
		System.out.println(c.hashCode());
		System.out.println(c1.hashCode());
		System.out.println(c==c1);
	}
	
	/**
	 * 查看类加载器
	 * @throws ClassNotFoundException
	 */
	@Test
	public void seeClassLoader() throws ClassNotFoundException{
		FileSystemClassloader classLoader = new FileSystemClassloader("D:/classes/");
		Class<?> c = classLoader.loadClass("per.owisho.helloworld.HelloWorld");
		System.out.println(c.getClassLoader());
		System.out.println(FileSystemClassLoaderTest.class.getClassLoader());
		System.out.println(String.class.getClassLoader());
	}
	
}
