package alevel.dao;

import alevel.dto.table.ResponseAbstTablePage;
import alevel.dto.table.impl.ResponseUserTablePage;

import java.util.List;

public interface BaseDao <T>{

    List<T> findAll();
    void create(T t);
    void update(T t);
    void delete(Long id);
    boolean existById(Long id);
    T findById(Long id);


    ResponseUserTablePage findAllWithSortColumn(int page, int showEntity, String column, String sort);

    ResponseAbstTablePage findAllAccountForUserListPage(int page, int showEntity, String columnSort, String sort, Long userId);

}
