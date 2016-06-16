package per.owisho.learn.outofmemoryerror;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args:-XX:PermSize=10M -XX:MaxPermSize=10M
 * 使用JDK1.6及之前的版本中，由于常量池分配在永久代内，我们可以通过-XX:PermSize和
 * -XX:MaxPermSize限制方法区大小，从而间接限制其中常量池的容量
 * 运行结果：
 * Exception in thread "main" java.lang.OutOfMemoryError: PermGen space
 * 	at java.lang.String.intern(Na)
 * 使用JDK1.7未获得异常
 * 
 * @author owisho
 *
 */
public class RuntimeConstantPoolOOM {

	public static void main(String[] args) {
		internTest();
		//使用List保持着常量池引用，避免Full GC回收常量池行为
		List<String> list = new ArrayList<String>();
		//10MB的PermSize在integer范围足够产生OOM了
		int i=0;
		while(true){
			list.add(String.valueOf(i++).intern());
		}
	}
	
	/**
	 * 这段代码在jdk1.6中运行会得到两个false，而在jdk1.7中会得到一个true和一个false。
	 * 产生差异的原因是：在jdk1.6中，intern()方法会把首次遇到的字符串实例复制到永久代中，
	 * 返回的也是永久带中的这个字符串实例的引用，而StringBuilder创建的字符串实例在Java堆上，
	 * 所以必然不是同一个引用
	 * 而JDK1.7的intern()实现不会再赋值实例，只是在常量池中记录首次出现的实例引用，因此
	 * intern()返回的引用和由StringBuilder创建的那个字符串实例是同一个。对str2比较返回
	 * false是因为“java”这个字符串在执行StringBuilder.toString()之前已经出现过，
	 * 字符串常量池中已经有他的引用了，不符合“首次出现”的原则，而“计算机软件”这个字符串则是首次出现的，
	 * 因此返回true
	 */
	public static void internTest(){
		String str1 = new StringBuilder("计算机").append("软件").toString();
		System.out.println(str1.intern()==str1);
		
		String str2 = new StringBuilder("ja").append("va").toString();
		System.out.println(str2.intern()==str2);
	}	
}
