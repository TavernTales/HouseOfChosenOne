package com.alphadev.api.repository;

import java.util.List;
public interface IHocoRepository<K,T>{
    List<T> getAll();
    T getById(K id);
    void save(T entity);
    void update(K entityId, T newEntity);
    void delete(K id);
}
