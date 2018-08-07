package com.tuyano.springboot;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MsgDataDaoImpl implements MsgDataDao<MsgDataDao> {

    @PersistenceContext
    private EntityManager entityManager;

    public MsgDataDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<MsgData> getAll() {
        return entityManager
                .createQuery("from MsgData")
                .getResultList();
    }

    @Override
    public MsgData findById(long id) {
        return (MsgData) entityManager
                .createNamedQuery("from MsgData where id="+id)
                .getSingleResult();
    }
}
