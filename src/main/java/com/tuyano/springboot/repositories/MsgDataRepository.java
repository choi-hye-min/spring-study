package com.tuyano.springboot.repositories;

import com.tuyano.springboot.MsgData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MsgDataRepository extends JpaRepository<MsgData, Long> {
}
