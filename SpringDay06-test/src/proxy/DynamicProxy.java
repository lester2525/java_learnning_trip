package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import tx.TranscationManager;

//动态代理，完成事务控制
public class DynamicProxy {
	//生成代理对象
	public static Object getProxy(final Object target,final TranscationManager tx){
		Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
			//唯一一个方法
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				Object result = null;
				try {
					tx.begin();
					result = method.invoke(target, args);
					tx.commit();
				} catch (Exception e) {
					tx.rollback();
					e.printStackTrace();
				}
				return result;
			}
		});
		
		return proxy;
	}
}
