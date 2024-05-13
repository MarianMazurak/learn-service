package org.example.entity;

import org.example.models.Element;

import java.util.Date;

public class Component {

    private String price;
    private Date orderDate;
    private Date deliveryDate;
    private Element element;

    public Component(String price, Date orderDate, Date deliveryDate, Element element) {
        this.price = price;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.element = element;
    }


}
