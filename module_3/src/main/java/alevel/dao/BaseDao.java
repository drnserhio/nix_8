package alevel.dao;

import alevel.dto.table.ResponseUserTablePage;

import java.util.List;

public interface BaseDao <T>{

    List<T> findAll();
    void create(T t);
    void update(T t);
    void delete(Long id);
    boolean existById(Long id);
    T findById(Long id);


    ResponseUserTablePage findAllWithSortColumn(int page, int showEntity, String column, String sort);

//    public EmployeeResponse findAllWithSortColumn(int page, int showEntity, String column, String sort) {
//        List<Employee> list = new ArrayList<>();
//        int firstPage = (page - 1) * showEntity;
//        log.debug(sort);
//
//        try {
//            String sql = String.format("select * from employees order by %s %s limit %d, %d", column, sort, firstPage, showEntity);
//            Query query =  session
//                    .getCurrentSession()
//                    .createNativeQuery(sql, Employee.class);
//            list = query.getResultList();
//        } catch (Exception e) {
//            log.debug(e.getMessage());
//        }
//
//
//        int totalPages = 0;
//        int itemSize = countEmployeeEntity();
//        if (itemSize % showEntity == 0) {
//            totalPages = (itemSize / showEntity);
//        } else {
//            totalPages = (itemSize / showEntity) + 1;
//        }
//
//        EmployeeResponse departmentResponse = new EmployeeResponse();
//
//        departmentResponse.setEmployees(list);
//        departmentResponse.setPage(page);
//        departmentResponse.setTotalPages(totalPages);
//        departmentResponse.setShowEntity(showEntity);
//        departmentResponse.setAllSizeEntity(itemSize);
//        departmentResponse.setSort(sort);
//        return departmentResponse;
//    }
//
//    private int totalPage(int itemSize, int showEntity) {
//        if (itemSize % showEntity == 0) {
//            return (itemSize / showEntity);
//        } else {
//            return (itemSize / showEntity) + 1;
//        }
//    }
//
//    private int showEntriesTo(int entFrom, int itemSize) {
//        return entFrom - 1 + itemSize;
//    }
//
//    private int showEntriesFrom(int page, int showEntity) {
//        return (page - 1) * showEntity + 1;
//    }

}
