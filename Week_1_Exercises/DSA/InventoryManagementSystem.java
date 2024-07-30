import java.util.HashMap;

class Product {
    private int productId;
    private String productName;
    private int quantity;
    private int price;

    Product(int productId, String productName, int quantity, int price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}

class Inventory {
    private HashMap<Integer, Product> hm;

    Inventory() {
        hm = new HashMap<>();
    }

    public void add(Product p) {
        hm.put(p.getProductId(), p);
    }

    public void delete(int productId) {
        hm.remove(productId);
    }

    public void updateName(int productId, String productName) {
        Product p = hm.getOrDefault(productId, null);
        if (p != null) {
            p.setProductName(productName);
        }
    }

    public void updateQuantity(int productId, int quantity) {
        Product p = hm.getOrDefault(productId, null);
        if (p != null) {
            p.setQuantity(quantity);
        }
    }

    public HashMap<Integer, Product> getInventory() {
        return hm;
    }
}

public class InventoryManagementSystem {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Product p1 = new Product(1, "Product1", 10, 100);
        Product p2 = new Product(2, "Product2", 5, 200);

        inventory.add(p1);
        inventory.add(p2);

        System.out.println("Product1 Name: " + inventory.getInventory().get(1).getProductName());
        System.out.println("Product2 Quantity: " + inventory.getInventory().get(2).getQuantity());

        inventory.updateName(1, "UpdatedProduct1");
        inventory.updateQuantity(2, 15);

        System.out.println("Updated Product1 Name: " + inventory.getInventory().get(1).getProductName());
        System.out.println("Updated Product2 Quantity: " + inventory.getInventory().get(2).getQuantity());
    }
}
