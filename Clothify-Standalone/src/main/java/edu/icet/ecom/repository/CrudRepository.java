package edu.icet.ecom.repository;

import edu.icet.ecom.entity.Employee;

import java.util.List;

public interface CrudRepository<T> extends SuperDao{
    boolean save(T entity);
    boolean delete(Integer id);
    boolean update(T entity,Integer id);
    List<T> getAll();
    T getById(Integer id);
    byte[] getImageData(Integer id);
}
