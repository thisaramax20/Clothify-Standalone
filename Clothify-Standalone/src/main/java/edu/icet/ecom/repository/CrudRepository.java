package edu.icet.ecom.repository;

public interface CrudRepository<T> extends SuperDao{
    boolean save(T entity);
}
