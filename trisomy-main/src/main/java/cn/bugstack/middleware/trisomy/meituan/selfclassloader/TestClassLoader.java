package cn.bugstack.middleware.trisomy.meituan.selfclassloader;

/**
 *1、 启动类加载器Bootstrap ClassLoader:之前说过了这是一个嵌在JVM内核中的加载器。它负责加载的是JAVA_HOME/lib下的类库，系统类加载器无法被Java程序直接应用
 * 2、扩展类加载器Extension ClassLoader:这个类加载器由sun.misc.Launcher$ExtClassLoader实现，它负责用于加载JAVA_HOME/lib/ext目录中的，或者被java.ext
 * .dirs系统变量指定所指定的路径中所有类库，开发者可以直接使用扩展类加载器。java.ext.dirs系统变量所指定的路径的可以通过程序来查看
 * 3、应用程序类加载器Application ClassLoader：只能加载项目bin目录下的.class文件。
 * 这个类加载器由sun.misc.Launcher$AppClassLoader实现。这个类也一般被称为系统类加载器
 * @author qiping.chi
 * @date 2021-10-06 15:16
 **/
public class TestClassLoader {

    public static void main(String[] args) {
        System.out.println(ClassLoader.getSystemClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader().getParent());
        //Bootstrap ClassLoader是JVM的一部分，是用C/C++写的，不属于Java，自然在Java堆中也没有自己的空间，所以就返回null了。所以，如果ClassLoader得到的是null，那么表示的ClassLoader就是Bootstrap ClassLoader
        System.out.println(ClassLoader.getSystemClassLoader().getParent().getParent());

        //java.ext.dirs系统变量所指定的路径的可以通过程序来查看
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("java.class.path"));

    }
}
