package pl.adambaranowski.testing.cart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.adambaranowski.testing.cart.Cart;
import pl.adambaranowski.testing.order.Order;

import java.time.Duration;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;

@DisplayName("Testing Cart Methods")
class CartTest {

    private Cart cart;
    private Order order;

    @BeforeEach
    void initializeCart(){
        cart = new Cart();
        order = new Order();
    }

    @Test
    void simulateLargeOrder() {
        //when
        //then
        assertTimeout(Duration.ofMillis(100), cart::simulateLargeOrder);
    }

    @DisplayName("Cart Should Not Be Empty - Assertion Chaining")
    @Test
    void cartShouldNotBeEmptyAfterAddingOrderToCart(){
        //when
        cart.addOrderToCart(order);

        //then
        assertThat(cart.getOrders(), allOf(
                notNullValue(),
                hasSize(1),
                is(not(empty()))
        ));

        //another version of assertion chaining
        assertAll(
                () -> assertThat(cart.getOrders(), notNullValue()),
                () -> assertThat(cart.getOrders(), hasSize(1)),
                () -> assertThat(cart.getOrders(), is(not(empty()))),
                () -> assertThat(cart.getOrders(), is(not(emptyCollectionOf(Order.class)))),

                //nie jestesmy uwiazani do pierwszego argumentu
                () -> assertThat(cart.getOrders().get(0).getMeals(), empty())
        );

    }
}