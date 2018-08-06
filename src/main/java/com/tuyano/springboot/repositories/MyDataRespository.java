package com.tuyano.springboot.repositories;


import com.tuyano.springboot.MyData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyDataRespository extends JpaRepository<MyData, Long> {

    @Query("select d from MyData d order by d.name")
    List<MyData> findAllOrderByName();
}
