/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.tiago.mondodbteste.dto;

import java.util.Date;

/**
 *
 * @author NB20708
 */
public class Purchase {
    private int ID;
    private String ItemName;
    private float Price;
    private Person person;
    private Category category;
    private SubCategory subCategory;
    private Date DateOfPurchase;
    private int categoryID;
    private int subCategoryID;
    private int personID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String ItemName) {
        this.ItemName = ItemName;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float Price) {
        this.Price = Price;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public Date getDateOfPurchase() {
        return DateOfPurchase;
    }

    public void setDateOfPurchase(Date DateOfPurchase) {
        this.DateOfPurchase = DateOfPurchase;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getSubCategoryID() {
        return subCategoryID;
    }

    public void setSubCategoryID(int subCategoryID) {
        this.subCategoryID = subCategoryID;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    @Override
    public String toString() {
        return "Purchase{" + "ID=" + ID + ", ItemName=" + ItemName + ", Price=" + Price + ", person=" + person + ", category=" + category + ", subCategory=" + subCategory + ", DateOfPurchase=" + DateOfPurchase + ", categoryID=" + categoryID + ", subCategoryID=" + subCategoryID + ", personID=" + personID + '}';
    }
    
    
}
