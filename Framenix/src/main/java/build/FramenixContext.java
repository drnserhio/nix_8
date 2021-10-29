package build;

import annotation.Autowired;
import boot.ImplSearchByInterface;
import factory.BeanFactory;
import store.BeanStore;

import java.lang.reflect.Field;
import java.util.Set;

public class FramenixContext {

    private Set<Class<?>> ServiceClasses;
    private ImplSearchByInterface searchByInterface;
    private BeanFactory factory;
    private String pathPackage;
    private Class<?> aClass;

    public FramenixContext(Class<?>className) {
        this.aClass = className;
        this.pathPackage = className.getPackageName();
    }

    public void setSearchByInterface(ImplSearchByInterface searchByInterface) {
        this.searchByInterface = searchByInterface;
    }

    public void initalizeService() {
       this.ServiceClasses = searchByInterface.searchImplByInterface(aClass);
    }

    public void initBeanMap() {
       this.ServiceClasses.forEach((serviceClass) -> {
           Object ob = factory.createBeanByInterfaces(serviceClass);
           System.out.println("ob " + ob);

           if (ob != null) {
               BeanStore.getInstance().getStore().put(serviceClass,ob);
           }
       });
    }

    public void configureBean() {

        BeanStore.getInstance().getStore().forEach((service,ob) -> {
            Field[] fields = ob.getClass().getDeclaredFields();
            for (Field field : fields) {
                System.out.println("f "  + field);
                if (field.isAnnotationPresent(Autowired.class)) {
                    field.setAccessible(true);
                    Object o = BeanStore.getInstance().getStore().get(field.getType());
                        try {
                            field.set(ob,o);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                }
            }
        });
    }

    public void setFactory(BeanFactory factory) {
        this.factory = factory;
    }
}
