
package model;

public class UserOrder {
    
    private int orderId;    
    private int userId;     
    private String name;
    private String phone;  
    private String address; 
    private int confirm;    
    private String gameId;

    public UserOrder() {
    }

    public UserOrder(int userId, String name, String phone, String address, int confirm, String gameId) {
        this.userId = userId;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.confirm = confirm;
        this.gameId = gameId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getConfirm() {
        return confirm;
    }

    public void setConfirm(int confirm) {
        this.confirm = confirm;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }
    
    
    
}
