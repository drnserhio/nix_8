package boot;

import annotation.Service;
import org.reflections.Reflections;
import org.reflections.Store;


import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


public class ImplSearchByInterface {

    private final Reflections reflection;

    public ImplSearchByInterface(String pathPackage) {
        this.reflection = new Reflections(pathPackage);
    }




    public Set<Class<?>> searchImplByInterface(Class<?> clazz) {
        Store store = reflection.getStore();

       Set<Class<?>> tred =collectionImplToSet(store.values())
                .stream()
                .map(nameClass -> reflection.forClass(nameClass, clazz.getClassLoader()))
                .filter(impls -> hasAnnotation(impls))
                .collect(Collectors.toSet());
        System.out.println(tred);

        return tred;

    }

    private Set<String> collectionImplToSet(Collection<Map<String, Set<String>>> values) {
        Set<String> nameClass = new HashSet<>();
        values.forEach(v -> {
            nameClass.addAll(v.keySet());
        });
        return nameClass;
    }

    private boolean hasAnnotation(Class<?> impls) {
            Set<Class<?>> annotation = reflection.getSubTypesOf((Class<Object>) impls);
        for (Class<?> className : annotation) {
            if (className.isAnnotationPresent(Service.class)) {
                return true;
            }
        }
        return false;
    }


    public Reflections getReflection() {
        return reflection;
    }
}
