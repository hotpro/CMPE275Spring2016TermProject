package edu.sjsu.cmpe275.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

/**
 * Created by yutao on 5/5/16.
 */
@Entity
public class Chief {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private List<Order> orderList;

    public Chief(String name) {
        this.name = name;
    }
}
