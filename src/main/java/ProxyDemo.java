import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Illia Krysenko on 15.12.2016.
 */
public class ProxyDemo {
    public static void main(String[] args) {
        Person2 person = new Person2();

        IPerson personproxy = (IPerson) Proxy.newProxyInstance(Person2.class.getClassLoader(),
                Person2.class.getInterfaces(),
                new MyInvocationHandler(person));

        personproxy.setName("�����");
        String h  = personproxy.getName() ;
        personproxy.rename("����");
    }
}

class MyInvocationHandler implements InvocationHandler {

    Object obj;

    public MyInvocationHandler(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("MyInvocationHandler invoke : " + method.getName());
        return method.invoke(obj, args) ;
    }
}

interface IPerson {
    public String getName();
    public void setName(String name);
    public void rename(String new_name);
}

class Person2 implements IPerson {
    private String name ;
    public  String getName()            {	return name;	}
    public  void   setName(String name) {	this.name = name;	}

    public void rename(String new_name) {
        if (!new_name.equals(name))		this.name = new_name;
    }
}
