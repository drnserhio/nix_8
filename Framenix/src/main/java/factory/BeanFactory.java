package factory;

import annotation.Service;
import boot.ImplSearchByInterface;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

public final class BeanFactory {

    private final ImplSearchByInterface searchByInterface;
    public BeanFactory(ImplSearchByInterface searchByInterface) {
        this.searchByInterface = searchByInterface;
    }


    public  <T> T createBeanByInterfaces(Class<T> interf) {

        System.out.println("intf : " + interf);
        if (interf.isInterface()) {
          Set<Class<? extends T>> classes = searchByInterface.getReflection().getSubTypesOf(interf);

            for (Class<? extends T> impl : classes) {
                if (impl.isAnnotationPresent(Service.class)) {
                    System.out.println("impl " + impl);
                    try {
                        return impl.getDeclaredConstructor().newInstance();
                    } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;

    }



}
