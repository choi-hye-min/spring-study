package com.tuyano.springboot;

import com.tuyano.springboot.repositories.MyDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class MyDataService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    MyDataRepository myDataRepository;

    private static final int PAGET_SIZE = 3;

    public Page<MyData> getMyDataInpage(Integer pageNumber){

        PageRequest pageRequest = new PageRequest(pageNumber-1, PAGET_SIZE);
        return myDataRepository.findAll(pageRequest);
    }

    public List<MyData> getAll(){
        return entityManager.createQuery("from MyData").getResultList();
    }

    public MyData get(int num){
        return (MyData) entityManager
                .createQuery("from MyData where id ="+num)
                .getSingleResult();
    }

    public List<MyData> find(String fstr){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MyData> query = builder.createQuery(MyData.class);
        Root<MyData> root = query.from(MyData.class);

        query.select(root)
                .where(builder.equal(root.get("name"), fstr));

        List<MyData> list = null;
        list = entityManager.createQuery(query).getResultList();

        return list;
    }
}
