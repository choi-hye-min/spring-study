package com.tuyano.springboot;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class MyDataDaoImpl implements MyDataDao<MyData> {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager entityManager;

    public MyDataDaoImpl(){
        super();
    }

    public MyDataDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public MyData findById(Long id) {
        Query query = entityManager.createQuery("from MyData where id="+id);
        return (MyData) query.getSingleResult();
    }

    @Override
    public List<MyData> findByName(String name) {
        return (List<MyData>) entityManager
                .createQuery("from MyData where name = '"+name+"'")
                .getResultList();
    }

    @Override
    public List<MyData> getAll() {
        List<MyData> list = null;

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<MyData> query = builder.createQuery(MyData.class);

        Root<MyData> root = query.from(MyData.class);

        query.select(root)
                .orderBy(builder.asc(root.get("name")));

        list = entityManager
                .createQuery(query)
                .getResultList();

        return list;
    }

    @Override
    public List<MyData> find(String fstt) {
        List<MyData> list = null;

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<MyData> query = builder.createQuery(MyData.class);
        Root<MyData> root = query.from(MyData.class);

        query.select(root)
                .where(builder.equal(root.get("name"), fstt));

        list = entityManager
                .createQuery(query)
                .getResultList();

        return list;
    }
}
