package com.qdh.springmvc.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyqdhgo on 2016/5/18.
 */
@XmlRootElement(name = "pizza")
public class Pizza {
    private String name;
    private String flavor;
    private List<String> toppings = new ArrayList<String>();

    public Pizza() {
    }

    public Pizza(String name) {
        this.name = name;
        this.flavor="spicy";
        this.toppings.add("Chinese");
        this.toppings.add("Aliea");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public List<String> getToppings() {
        return toppings;
    }

    public void setToppings(List<String> toppings) {
        this.toppings = toppings;
    }
}
