package alevel.dao;

import alevel.model.impl.Operation;

import java.util.List;

public interface BaseOperationDao <T>{


    void createOperation(T t);
    void updateOperation(T t);
    void deleteOperation(Long id);
    boolean existOperationById(Long id);
    Operation findOperationById(Long id);

    Operation findByAccountId(Long id);

    List<Operation> findAllByAccountId(Long id);

}
