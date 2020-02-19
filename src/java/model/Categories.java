/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author KIM
 */
public class Categories {
    private int cat_id;
    private String cat_name;
    private String cat_description;
    private String cat_image;

    public Categories() {
    }

    public Categories(int cat_id, String cat_name, String cat_description, String cat_image) {
        this.cat_id = cat_id;
        this.cat_name = cat_name;
        this.cat_description = cat_description;
        this.cat_image = cat_image;
    }
    
    public Categories(String cat_name, String cat_description, String cat_image) {
        
        this.cat_name = cat_name;
        this.cat_description = cat_description;
        this.cat_image = cat_image;
    }

    public String getCat_description() {
        return cat_description;
    }

    public void setCat_description(String cat_description) {
        this.cat_description = cat_description;
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public String getCat_image() {
        return cat_image;
    }

    public void setCat_image(String cat_image) {
        this.cat_image = cat_image;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }
    
}
