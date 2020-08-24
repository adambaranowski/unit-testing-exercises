package pl.adambaranowski.testing.cart;

import pl.adambaranowski.testing.Meal;
import pl.adambaranowski.testing.order.Order;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Order> orders = new ArrayList<>();

    public void addOrderToCart(Order order){
        orders.add(order);
    }

    public void clearCart(){
        orders.clear();
    }

    void simulateLargeOrder(){
        for (int i = 0; i < 1_000; i++) {
            Meal meal = new Meal(i%10, "Hamburger" + i);
            Order order = new Order();
            order.addMealToOrder(meal);
            addOrderToCart(order);
        }
        System.out.println("Cart size " + orders);
        clearCart();
    }

    public List<Order> getOrders() {
        return orders;
    }
}
