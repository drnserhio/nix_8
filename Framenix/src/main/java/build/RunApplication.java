package build;

import store.BeanStore;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RunApplication {

    public void start() {
        BeanStore.getInstance().getStore().forEach((service, ob) -> {
            if (service.getName().endsWith("Controller")) {
                System.out.println(service);
                Method[] methods = service.getMethods();

                for (Method method : methods) {
                    System.out.println(method);
                    if (method.getName().equals("run")) {
                        try {
                            method.invoke(ob);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }
}
