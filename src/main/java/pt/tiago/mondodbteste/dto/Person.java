/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.tiago.mondodbteste.dto;

import java.util.List;

/**
 *
 * @author NB20708
 */
public class Person {

    private static final long serialVersionUID = 1L;
    private String ID;
    private String Name;
    private String Surname;
    private List<Purchase> purchases;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String Surname) {
        this.Surname = Surname;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    @Override
    public String toString() {
        return "Person{" + "ID=" + ID + ", Name=" + Name + ", Surname=" + Surname + ", purchases=" + purchases + '}';
    }

}
