package com.owntech.taskmanagement.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@NoRepositoryBean
public interface BaseDao<T, I extends Serializable> extends JpaRepository<T, I>, JpaSpecificationExecutor {

    @Override
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.isDeleted = false")
    List<T> findAll();

    @Override
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.isDeleted = false")
    List<T> findAll(Sort sort);

    @Override
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.id in ?1 and e.isDeleted = false")
    List<T> findAllById(Iterable<I> ids);

    @Override
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.isDeleted = false")
    Page<T> findAll(Pageable page);

    @Override
    @Transactional(readOnly = true)
    @Query("select count(e) from #{#entityName} e where e.isDeleted = false")
    long count();


    @Transactional
    @Query("update #{#entityName} e set e.isDeleted = true where e.id = ?1")
    @Modifying
    void delete(I i);

    @Transactional
    @Query("update #{#entityName} e set e.isDeleted = true where e = ?1")
    @Modifying
    void delete(T entity, UUID uuid);

    @Override
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.id = ?1 and e.isDeleted = false ")
    Optional<T> findById(I i);

    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.id = ?1 and e.isDeleted = false")
    T findOne(I id);

    Page<T> findAllByIsDeletedFalse(Pageable pageable);

    @Transactional
    @Modifying
    @Query("update #{#entityName} e set e.isDeleted=true WHERE e IN ?1")
    void deleteInBatchSoft(Iterable<T> entities, UUID uuid);
}
