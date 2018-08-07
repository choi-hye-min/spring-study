package com.tuyano.springboot;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "mydata")
@Data
public class MyData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 200, nullable = true)
    private String mail;

    @Column(nullable = true)
    private Integer age;

    @Column(nullable = true)
    private String memo;

    @OneToMany(cascade = CascadeType.ALL)
    @Column(nullable = true)
    private List<MsgData> msgdatas;
}
