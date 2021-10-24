package ua.com.alevel.myList;

import ua.com.alevel.entity.BaseEntity;

public class ArrList<T extends BaseEntity> extends MyList {

    public boolean add(T ob) {
        return super.add(ob);
    }

    public boolean remove(T ob) {

        return super.remove(ob);
    }

    @Override
    public Object get(Object ob) {
        return super.get(ob);
    }


    @Override
    public Object get(Long id) {
        return super.get(id);
    }


}
