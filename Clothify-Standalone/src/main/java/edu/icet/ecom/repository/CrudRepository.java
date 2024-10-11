package edu.icet.ecom.repository;

import java.util.List;

public interface CrudRepository<T> extends SuperDao{
    boolean save(T entity);
    boolean delete(String id);
    boolean update(T entity);
    List<T> getAll();
    T getById(String id);
    T getHigestId();
}
