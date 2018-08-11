package com.tuyano.springboot;

import com.tuyano.springboot.repositories.MyDataRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class MyDataBean {

    @Autowired
    MyDataRepository repository;

    public String getTableTagById(Long id){
        MyData data = repository.findById(id).get();

        String result = "<tr><td>" + data.getName()
                + "</td><td>" + data.getMail() +
                "</td><td>" + data.getAge() +
                "</td><td>" + data.getMemo() +
                "</td></tr>";

        return result;
    }
}
