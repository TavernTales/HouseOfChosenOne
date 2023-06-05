package com.alphadev.api.repository;

import org.jetbrains.annotations.NotNull;

import java.util.List;
public interface IHOCORepository<K,T>{
    List<T> getAll();
    T getById(@NotNull K id);
    void save(@NotNull T entity);
    void update(@NotNull K entityId,@NotNull T newEntity);
    void delete(@NotNull K id);
}
