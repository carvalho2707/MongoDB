/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.tiago.mongodbteste;

import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;
import pt.tiago.mondodbteste.dto.Category;
import pt.tiago.mondodbteste.dto.Person;
import pt.tiago.mondodbteste.dto.Purchase;

/**
 *
 * @author NB20708
 */
public class Populator {

    public static List<Person> populatePerson() {
        List<Person> personList = new ArrayList<>();
        Person p = new Person();
        p.setName("Tiago");
        p.setSurname("Carvalho");
        personList.add(p);

        p = new Person();
        p.setName("Filipe");
        p.setSurname("Carvalho");
        personList.add(p);
        
        p = new Person();
        p.setName("Jota");
        p.setSurname("Carvalho");
        personList.add(p);
        
        return personList;
    }

    public static List<Category> populateCategory() {
        List<Category> categoryList = new ArrayList<>();
        Category p = new Category();
        p.setName("Alimentacao");
        p.setDescription("TROLOLO");
        categoryList.add(p);

        p = new Category();
        p.setName("Escola");
        p.setDescription("LALALAL");
        categoryList.add(p);
        
        p = new Category();
        p.setName("Tranportes");
        p.setDescription("Carvalho");
        categoryList.add(p);
        
        return categoryList;
    }

    public static List<Purchase> populatePurchase() {
        List<Purchase> purchaseList = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            Purchase p1 = new Purchase();
            p1.setItemName("MAC" + i);
            p1.setPrice(0.5f * i);
            purchaseList.add(p1);
        }
        for (int i = 25; i < 50; i++) {
            Purchase p1 = new Purchase();
            p1.setItemName("MAC" + i);
            p1.setPrice(0.5f * i);
            purchaseList.add(p1);
        }

        return purchaseList;
    }

}
