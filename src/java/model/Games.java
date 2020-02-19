/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author KIM
 */
public class Games {
    private int id;
    private int cat_id;
    private int sup_id;
    private String name;
    private double price;
    private int quantity;
    private String issuedate;
    private String description;
    private String image;
    private String cover;
    private int status;
    private String coverthump;
    private String trailer;

    public Games() {
    }

    public Games(int id, int cat_id, int sup_id, String name, double price, int quantity, String issuedate, String description, String image, String cover, int status, String coverthump, String trailer) {
        this.id = id;
        this.cat_id = cat_id;
        this.sup_id = sup_id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.issuedate = issuedate;
        this.description = description;
        this.image = image;
        this.cover = cover;
        this.status = status;
        this.coverthump = coverthump;
        this.trailer = trailer;
    }
    
       public Games(int cat_id, int sup_id, String name, double price, int quantity, String issuedate, String description, String image, String cover, int status,  String trailer) {
        
        this.cat_id = cat_id;
        this.sup_id = sup_id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.issuedate = issuedate;
        this.description = description;
        this.image = image;
        this.cover = cover;
        this.status = status;
       
        this.trailer = trailer;
    }
       
       public Games(String name, double price, int quantity, String issuedate, String description, String image, String cover, int status, String trailer) {
        
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.issuedate = issuedate;
        this.description = description;
        this.image = image;
        this.cover = cover;
        this.status = status;
       
        this.trailer = trailer;
    }
       
       

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCoverthump() {
        return coverthump;
    }

    public void setCoverthump(String coverthump) {
        this.coverthump = coverthump;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIssuedate() {
        return issuedate;
    }

    public void setIssuedate(String issuedate) {
        this.issuedate = issuedate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSup_id() {
        return sup_id;
    }

    public void setSup_id(int sup_id) {
        this.sup_id = sup_id;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

   
    
    
}
