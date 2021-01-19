package com.owntech.taskmanagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;

public interface IGenericService<T, D extends Serializable> {

    List<T> findAll();

    Page<T> findAll(Pageable page);

    List<T> findAll(Sort sort);

    List<T> findAll(Iterable<D> ids);

    <S extends T> List<S> save(Iterable<S> entities);

    void flush();

    <S extends T> S saveAndFlush(S entity);

    void deleteInBatchSoft(Iterable<T> entities);

    void delete(D id);

    void delete(T entity);

    T findOne(D id);

    void deleteSoft(D id);

}

