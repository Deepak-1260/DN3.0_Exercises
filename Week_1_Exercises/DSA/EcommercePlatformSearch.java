import java.util.*;

class Product {
    int productId;
    String productName;
    String category;
    int quantity;
    int price;

    Product(int productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }
}

class Inventory {
    ArrayList<Product> arr;

    Inventory() {
        arr = new ArrayList<>();
    }

    public void add(Product p) {
        arr.add(p);
    }

    public Product linearSearch(int productId) {
        for (Product p : arr) {
            if (p.productId == productId) {
                return p;
            }
        }
        return null;
    }

    public Product binarySearch(int productId) {
        Collections.sort(arr, Comparator.comparingInt(x -> x.productId));
        int low = 0;
        int high = arr.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr.get(mid).productId == productId) {
                return arr.get(mid);
            } else if (arr.get(mid).productId > productId) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return null;
    }
}

public class EcommercePlatformSearch {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Product p1 = new Product(1, "Product1", "Category1");
        Product p2 = new Product(2, "Product2", "Category2");

        inventory.add(p1);
        inventory.add(p2);

        Product result = inventory.linearSearch(1);
        if (result != null) {
            System.out.println("Linear search found: " + result.productName);
        } else {
            System.out.println("Linear search did not find the product.");
        }

        result = inventory.binarySearch(2);
        if (result != null) {
            System.out.println("Binary search found: " + result.productName);
        } else {
            System.out.println("Binary search did not find the product.");
        }
    }
}
