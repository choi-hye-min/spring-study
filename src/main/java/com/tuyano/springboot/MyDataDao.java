package com.tuyano.springboot;

import java.io.Serializable;
import java.util.List;

/**
 * DataAccessObject(DTO)
 * 데이터베이스에 접속하기 위한 수단으 제공하는 객체
 * @param <T>
 */
public interface MyDataDao <T> extends Serializable {

    public List<T> getAll(); // 전체 조회
    public T findById(Long id); // Id 조화
    public List<T> findByName(String name); // name 조회
    public List<T> find(String fstt); // 문자열 조회
}
