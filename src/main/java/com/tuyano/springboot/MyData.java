package com.tuyano.springboot;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Entity
@Table(name = "mydata")
@Data
@NamedQueries(
        @NamedQuery(name = "findByAge", query = "from MyData where age > :min and age < :max")
)
@XmlRootElement
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
    @JsonIgnore
    private List<MsgData> msgdatas;
}
