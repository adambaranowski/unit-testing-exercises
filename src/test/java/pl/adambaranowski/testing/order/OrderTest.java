package pl.adambaranowski.testing.order;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.adambaranowski.testing.Meal;
import pl.adambaranowski.testing.order.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


class OrderTest {

    private Order order;

    @BeforeEach
    void initializeOrder(){
        this.order = new Order();
    }

    @Test
    void testAssertArrayEquals() {
        //given
        int[] ints1 = {1, 2, 3};
        int[] ints2 = {1, 2, 3};

        //then
        assertArrayEquals(ints1, ints2);
    }

    @Test
    void mealListShouldBeEmptyAfterCreationOPfOrder(){

        //given


        //when
        //then
        assertThat(order.getMeals(), is(empty()));
        assertThat(order.getMeals(), hasSize(0));
        MatcherAssert.assertThat(order.getMeals(), emptyCollectionOf(Meal.class));
    }

    @Test
    void addingMealToOrderShouldIncreaseOrderSize(){
        //given
        Meal meal1 = new Meal(15, "Burger");
        Meal meal2 = new Meal(15, "Burger");


        //when
        order.addMealToOrder(meal1);

        //then
        assertThat(order.getMeals(), hasSize(1));
        assertThat(order.getMeals(), contains(meal2));
        assertThat(order.getMeals(), hasItem(meal2));

        assertEquals(order.getMeals().get(0), meal2);

        //different instances
        //assertThat(order.getMeals().get(0), sameInstance(Meal.class));


    }

    @Test
    void removingMealFromOrderShouldDecreaseSize(){
        //given
        Meal meal1 = new Meal(15, "Burger");
        Meal meal2 = new Meal(15, "Burger");

        Order order = new Order();

        //when
        order.addMealToOrder(meal1);
        order.removeMealFromOrder(meal1);

        //then
        assertThat(order.getMeals(), hasSize(0));
        assertThat(order.getMeals(), not(contains(meal1)));
    }

    @Test
    void mealsShouldBeInCorrectOrderAfterAddingThemToOrder(){
        //given
        Meal meal1 = new Meal(15, "Burger");
        Meal meal2 = new Meal(15, "Burger");

        Order order = new Order();

        //when
        order.addMealToOrder(meal1);
        order.addMealToOrder(meal2);

        //then
        assertThat(order.getMeals(), contains(meal1, meal2));
        assertThat(order.getMeals(), containsInAnyOrder(meal2, meal1));


    }

    @Test
    void testIfTwoMealsListAreTheSame(){
        //given
        Meal meal1 = new Meal(15, "Burger");
        Meal meal2 = new Meal(5, "Sandwich");
        Meal meal3 = new Meal(13, "Kebab");

        List<Meal> meals1 = new ArrayList<>(Arrays.asList(meal1, meal2));
        List<Meal> meals2 = new ArrayList<>(Arrays.asList(meal1, meal2));

        //when
        //then
        assertThat(meals1, is(meals2));

    }



}