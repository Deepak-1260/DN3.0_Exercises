import java.util.*;

class Order {
    int orderId;
    String customerName;
    int totalPrice;

    Order(int orderId, String customerName, int totalPrice) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
    }
}

public class SortingCustomerOrders {
    public static void bubbleSort(Order[] a) {
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (a[j].totalPrice > a[j + 1].totalPrice) {
                    Order temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }

    public static void quickSort(Order[] a, int low, int high) {
        if (low >= high) return;
        int pivotIndex = partition(a, low, high);
        quickSort(a, low, pivotIndex - 1);
        quickSort(a, pivotIndex + 1, high);
    }

    private static int partition(Order[] a, int low, int high) {
        Order pivot = a[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (a[j].totalPrice < pivot.totalPrice) {
                i++;
                Order temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }
        Order temp = a[i + 1];
        a[i + 1] = a[high];
        a[high] = temp;
        return i + 1;
    }

    public static void main(String[] args) {
        Order[] orders = {
            new Order(1, "Alice", 300),
            new Order(2, "Bob", 150),
            new Order(3, "Charlie", 200)
        };

        System.out.println("Before Bubble Sort:");
        for (Order order : orders) {
            System.out.println(order.customerName + ": " + order.totalPrice);
        }

        bubbleSort(orders);

        System.out.println("\nAfter Bubble Sort:");
        for (Order order : orders) {
            System.out.println(order.customerName + ": " + order.totalPrice);
        }

        Order[] ordersForQuickSort = {
            new Order(1, "Alice", 300),
            new Order(2, "Bob", 150),
            new Order(3, "Charlie", 200)
        };

        System.out.println("\nBefore Quick Sort:");
        for (Order order : ordersForQuickSort) {
            System.out.println(order.customerName + ": " + order.totalPrice);
        }

        quickSort(ordersForQuickSort, 0, ordersForQuickSort.length - 1);

        System.out.println("\nAfter Quick Sort:");
        for (Order order : ordersForQuickSort) {
            System.out.println(order.customerName + ": " + order.totalPrice);
        }
    }
}
