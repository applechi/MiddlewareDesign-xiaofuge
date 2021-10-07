package cn.bugstack.middleware.trisomy.meituan.javaassist;

import cn.bugstack.middleware.trisomy.meituan.Base;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

import java.io.IOException;

/**
 * 如果我们在一个JVM中，先加载了一个类，然后又对其进行字节码增强并重新加载会发生什么呢？模拟这种情况，只需要我们在上文中Javassist的Demo中main()方法的第一行添加Base b=new Base()，即在增强前就先让JVM加载Base类，然后在执行到c.toClass()方法时会抛出错误
 *
 * 跟进c.toClass()方法中，我们会发现它是在最后调用了ClassLoader的native方法defineClass()时报错。也就是说，JVM是不允许在运行时动态重载一个类的。
 */
public class JavassistTest {
    public static void main(String[] args) throws NotFoundException, CannotCompileException, IllegalAccessException, InstantiationException, IOException {
        //Base b=new Base();
        ClassPool cp = ClassPool.getDefault();
        CtClass cc = cp.get("cn.bugstack.middleware.trisomy.meituan.Base");
        CtMethod m = cc.getDeclaredMethod("process");
        m.insertBefore("{ System.out.println(\"startttt\"); }");
        m.insertAfter("{ System.out.println(\"enddddd\"); }");
        Class c = cc.toClass();
        cc.writeFile("/Users/jake.chi/IdeaApple/MiddlewareDesign/trisomy-main/target/classes/cn/bugstack/middleware/trisomy/meituan/asm");
        Base h = (Base) c.newInstance();
        h.process();
    }
}