package com.tuyano.springboot;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "mydata")
@Data
@NamedQueries(
        @NamedQuery(
                name = "findWithName",
                query = "from MyData where name like :fname"
        )
)
public class MyData {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 200, nullable = true)
    private String mail;

    @Column(nullable = true)
    private Integer age;

    @Column(nullable = true)
    private String memo;
}
