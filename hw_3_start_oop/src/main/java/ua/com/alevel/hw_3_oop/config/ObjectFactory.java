package ua.com.alevel.hw_3_oop.config;


import org.apache.commons.lang3.StringUtils;
import org.reflections.Reflections;
import ua.com.alevel.hw_3_oop.dataBase.UserDB;
import ua.com.alevel.hw_3_oop.dataBase.impl.DataBase;
import ua.com.alevel.hw_3_oop.dataBase.impl.SomeUserDB;
import ua.com.alevel.hw_3_oop.util.ResourcesUtil;

import java.util.Map;
import java.util.Set;

public class ObjectFactory {

    private static ObjectFactory instance;
    private final Reflections scan;
    private final Map<String, String> mapProperties;

    private ObjectFactory() {
        scan = new Reflections("ua.com.alevel");
        mapProperties = ResourcesUtil.getProperties(this.getClass().getClassLoader());
    }

    public static ObjectFactory getInstance() {
        if (instance == null) {
            instance = new ObjectFactory();
        }
        return instance;
    }

    public <IFC> IFC getCurrentObject(Class<IFC> ifc) {
        if (ifc.isInterface()) {
            Set<Class<? extends IFC>> imls = scan.getSubTypesOf(ifc);
            for (Class<? extends IFC> iml : imls) {
                if (ifc.isAssignableFrom(UserDB.class)) {
                    String db = mapProperties.get("db");
                    if (StringUtils.isNotEmpty(db)) {
                        if (db.equals("some")) {
                            return (IFC) SomeUserDB.getInstance();
                        } else {
                            return (IFC) DataBase.getInstance();
                        }
                    }
                    return (IFC) DataBase.getInstance();
                }
                if (!iml.isAnnotationPresent(Deprecated.class)) {
                    try {
                        return iml.getDeclaredConstructor().newInstance();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        throw new RuntimeException("class not found by interface");
    }
}
