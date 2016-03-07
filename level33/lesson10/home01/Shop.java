package com.javarush.test.level33.lesson10.home01;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IGOR on 17.01.2016.
 */

@XmlRootElement
@XmlType(name = "shop")
public class Shop
{
    @XmlElementWrapper(name = "goods", nillable = true)
    public List<String> names = new ArrayList<>();
    public int count;
    public double profit;
    public List<String> secretData = new ArrayList<>();

    public Shop()
    {
    }
}

