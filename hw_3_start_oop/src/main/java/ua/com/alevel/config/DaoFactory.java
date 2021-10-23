package ua.com.alevel.config;

import org.reflections.Reflections;
import ua.com.alevel.dataBase.impl.DataBaseDefault;
import ua.com.alevel.dataBase.impl.DataBaseUserInMemory;
import ua.com.alevel.dataBase.UserDB;
import ua.com.alevel.utils.ResourceReadUtil;

import java.util.Map;

public class DaoFactory {
    private static DaoFactory instance;
    private final Reflections reflection;
    private final Map<String, String> mapProperties;

    private DaoFactory(){
        reflection = new Reflections("ua.com.alevel");
        mapProperties = ResourceReadUtil.getPropertieToMap(this.getClass().getClassLoader());
    }

    public static DaoFactory getInstance() {
        if (instance == null) {
            instance = new DaoFactory();
        }
        return instance;
    }

    public <T> T getDataBaseObject(Class<T> clazz) {
        if (clazz.isInterface()) {
            var set = reflection.getSubTypesOf(clazz);
            for (Class<? extends T> child : set) {
                if (clazz.isAssignableFrom(UserDB.class)){
                    String str = mapProperties.get("dataBase");
                    if (!str.equals("memory")) {
                        return(T) DataBaseDefault.getInstance();
                    } else {
                        return (T) DataBaseUserInMemory.getInstance();
                    }
                }

                if (!child.isAnnotationPresent(Deprecated.class)) {
                    try {
                        return child.getDeclaredConstructor().newInstance();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        throw new NoClassDefFoundError("Error...");
    }


}
