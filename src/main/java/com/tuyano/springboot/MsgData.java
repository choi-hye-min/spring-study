package com.tuyano.springboot;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "msgdata")
@Data
public class MsgData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @NotNull
    private long id;

    @Column
    private String title;

    @Column
    private String message;

    @ManyToOne
    private MyData mydata;

    public MsgData(){
        super();
        mydata = new MyData();
    }
}
