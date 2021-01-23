package com.owntech.taskmanagement.service.impl;

import com.owntech.taskmanagement.dao.BaseDao;
import com.owntech.taskmanagement.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class GenericService<T, D extends Serializable> implements IGenericService<T, D> {

    @Autowired
    private BaseDao<T, D> baseDao;

    @Autowired
    public GenericService() {
        super();
    }

    @Override
    public List<T> findAll() {
        return baseDao.findAll();
    }

    @Override
    public List<T> findAll(Sort sort) {
        return baseDao.findAll(sort);
    }

    @Override
    public List<T> findAll(Iterable<D> ids) {
        return baseDao.findAllById(ids);
    }

    @Override
    public <S extends T> List<S> save(Iterable<S> entities) {
        return baseDao.saveAll(entities);
    }

    @Override
    public void flush() {
        baseDao.flush();

    }

    @Override
    public <S extends T> S saveAndFlush(S entity) {
        return baseDao.saveAndFlush(entity);
    }

    @Override
    public void deleteInBatchSoft(Iterable<T> entities) {
        UUID uuid = UUID.randomUUID();
        baseDao.deleteInBatchSoft(entities,uuid);
    }

    @Override
    public T findOne(D id) {
        return baseDao.findOne(id);
    }

    @Override
    public void delete(D id) {
        baseDao.delete(id);
    }

    @Override
    public void delete(T entity) {
        UUID uuid = UUID.randomUUID();
        baseDao.delete(entity, uuid);
    }

    @Override
    public Page<T> findAll(Pageable page) {
        return baseDao.findAll(page);
    }

    @Override
    public void deleteSoft(D id) {
        baseDao.deleteById(id);

    }

}
