/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.tiago.mondodbteste.dto;

/**
 *
 * @author NB20708
 */
public class Category {
    private int ID;
    private String Name;
    private String Description;
    private float Total;
    private float[] TotalByMonth;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public float getTotal() {
        return Total;
    }

    public void setTotal(float Total) {
        this.Total = Total;
    }

    public float[] getTotalByMonth() {
        return TotalByMonth;
    }

    public void setTotalByMonth(float[] TotalByMonth) {
        this.TotalByMonth = TotalByMonth;
    }

    @Override
    public String toString() {
        return "Category{" + "ID=" + ID + ", Name=" + Name + ", Description=" + Description + ", Total=" + Total + ", TotalByMonth=" + TotalByMonth + '}';
    }
    
}
