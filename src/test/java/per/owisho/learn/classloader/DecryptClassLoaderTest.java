package per.owisho.learn.classloader;

import org.junit.Test;

/**
 * 解密加载器测试
 * @author Administrator
 *
 */
public class DecryptClassLoaderTest {

	/**
	 * 使用解密加载器加载加密后的类
	 */
	@Test
	public void load(){
		DecryptClassLoader loader = new DecryptClassLoader("D:/classes/encrypt/");
		try {
			Class<?> c = loader.loadClass("per.owisho.helloworld.HelloWorld");
			System.out.println(c);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 使用普通类加载器加载加密后的类
	 */
	@Test
	public void loadNormal(){
		FileSystemClassloader loader = new FileSystemClassloader("D:/classes/encrypt/");
		try {
			Class<?> c = loader.loadClass("per.owisho.helloworld.HelloWorld");
			System.out.println(c);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	/*
	java.lang.ClassFormatError: Incompatible magic value 889275713 in class file per/owisho/helloworld/HelloWorld
	at java.lang.ClassLoader.defineClass1(Native Method)
	at java.lang.ClassLoader.defineClass(ClassLoader.java:800)
	at java.lang.ClassLoader.defineClass(ClassLoader.java:643)
	at per.owisho.learn.classloader.FileSystemClassloader.findClass(FileSystemClassloader.java:53)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:425)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:358)
	at per.owisho.learn.classloader.DecryptClassLoaderTest.loadNormal(DecryptClassLoaderTest.java:33)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:606)
	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
	at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
	at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
	at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
	at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
	at org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:86)
	at org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:459)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:675)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:382)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:192)
	 */
}
