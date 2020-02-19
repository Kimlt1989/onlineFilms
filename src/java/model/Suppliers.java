/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author KIM
 */
public class Suppliers {
    private int sup_id;
    private String sup_name;
    private String sup_address;

    public Suppliers() {
    }

    public Suppliers(int sup_id, String sup_name, String sup_address) {
        this.sup_id = sup_id;
        this.sup_name = sup_name;
        this.sup_address = sup_address;
    }
       public Suppliers(String sup_name, String sup_address) {
        
        this.sup_name = sup_name;
        this.sup_address = sup_address;
    }

    public String getSup_address() {
        return sup_address;
    }

    public void setSup_address(String sup_address) {
        this.sup_address = sup_address;
    }

    public int getSup_id() {
        return sup_id;
    }

    public void setSup_id(int sup_id) {
        this.sup_id = sup_id;
    }

    public String getSup_name() {
        return sup_name;
    }

    public void setSup_name(String sup_name) {
        this.sup_name = sup_name;
    }
    
    
}
