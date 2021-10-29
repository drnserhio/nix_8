package build;

import boot.ImplSearchByInterface;
import factory.BeanFactory;
import store.BeanStore;

public class BuildApplication {

    public static void run(Class<?> className) {
        ImplSearchByInterface searchByInterface = new ImplSearchByInterface(className.getPackageName());
        FramenixContext context = new FramenixContext(className);
        context.setSearchByInterface(searchByInterface);
        context.initalizeService();

        BeanFactory factory = new BeanFactory(searchByInterface);
        context.setFactory(factory);
        context.initBeanMap();
        context.configureBean();

        RunApplication runApplication = new RunApplication();
        runApplication.start();



    }
}
