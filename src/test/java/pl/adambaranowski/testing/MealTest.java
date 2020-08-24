package pl.adambaranowski.testing;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.adambaranowski.testing.order.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

class MealTest {

    @Spy
    private Meal mealSpy;

    private static Stream<Arguments> createMealsWithNamesAndPrices() {
        return Stream.of(
                Arguments.of("Hamburger", 10),
                Arguments.of("Cheseburger", 12)
        );
    }

    private static Stream<String> createCakeNames() {
        List<String> cakeNames = Arrays.asList("puciakcake", "cheescake", "cupcake");
        return cakeNames.stream();
    }

    @Test
    void shouldReturnDiscountedPrice() {

        //given
        Meal meal = new Meal(35);

        //when
        int discountedPrice = meal.getDiscountedPrice(7);

        //then
        assertEquals(28, discountedPrice);
        assertThat(discountedPrice, is(equalTo(28)));
    }

    @Test
    void referencesToTheSameObjectShouldBeEqual() {
        //given
        Meal meal1 = new Meal(10);
        Meal meal2 = meal1;

        //then
        assertSame(meal1, meal2);
        assertThat(meal1, sameInstance(meal2));
    }

    @Test
    void referencesToTheSameDifferentShouldNotBeEqual() {
        //given
        Meal meal1 = new Meal(10);
        Meal meal2 = new Meal(34);

        //then
        assertNotSame(meal1, meal2);
    }

    @Test
    void twoMealsShouldBeEqualWhenPriceAndNameAreTheSame() {
        //given
        Meal meal1 = new Meal(10, "Pizza");
        Meal meal2 = new Meal(10, "Pizza");
        //when
        //then
        assertEquals(meal1, meal2);
    }

    @Test
    void exceptionShouldBeThrownIfDiscountIsHigherThanPrice() {
        //given
        Meal meal = new Meal(1, "Zupa");

        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> meal.getDiscountedPrice(2));
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 10, 15, 18})
    void mealPricesShouldBeLowerThan20(int prices) {
        assertThat(prices, lessThan(20));
    }

    @ParameterizedTest
    @MethodSource("createMealsWithNamesAndPrices")
    void burgersShouldHaveCorrectNameAndPrice(String name, int price) {
        assertAll(
                () -> assertThat(name.length(), lessThan(15)),
                () -> assertThat(price, greaterThan(1))
        );
    }

    @ParameterizedTest
    @MethodSource("createCakeNames")
    void cakeNameShouldEndWithCake(String name) {
        assertThat(name, notNullValue());
        assertThat(name, endsWith("cake"));
    }

    @TestFactory
    Collection<DynamicTest> dynamicTestCollection() {
        return Arrays.asList(
                DynamicTest.dynamicTest("Dynamic Test 1",
                        () -> assertThat(5, lessThan(6))),
                DynamicTest.dynamicTest("Dynamic Test 2",
                        () -> assertEquals(4, 4))
        );
    }

    private int calculatePrice(int price, int quantity) {
        return price * quantity;
    }

    @Tag("fries")
    @TestFactory
    Collection<DynamicTest> calculateMealPrices() {
        Order order = new Order();
        order.addMealToOrder(new Meal(10, "hamburger", 2));
        order.addMealToOrder(new Meal(6, "fries", 1));
        order.addMealToOrder(new Meal(8, "pizza", 6));

        Collection<DynamicTest> dynamicTests = new ArrayList<>();

        for (int i = 0; i < order.getMeals().size(); i++) {
            int price = order.getMeals().get(i).getPrice();
            int quantity = order.getMeals().get(i).getQuantity();

            Executable executable = () -> {
                assertThat(calculatePrice(price, quantity), lessThan(67));
            };

            String name = "Test name: " + i;

            DynamicTest dynamicTest = DynamicTest.dynamicTest(name, executable);
            dynamicTests.add(dynamicTest);
        }

        return dynamicTests;
    }

    @Test
    void testMealSumPrice(){
        //given
        Meal meal = mock(Meal.class);

        given(meal.getPrice()).willReturn(15);
        given(meal.getQuantity()).willReturn(3);

        //first option
        given(meal.sumPrice()).will(invocationOnMock -> meal.getPrice()*meal.getQuantity());

        //second option
        given(meal.sumPrice()).willCallRealMethod();

        //when
        int result = meal.sumPrice();

        //then
        assertThat(result, equalTo(45));
    }


    @Test
    void testMealSumPriceWithSpy(){

        //option with annotations
        MockitoAnnotations.initMocks(this);

        //given
        Meal mealInstance = new Meal(14, "burrito", 2);

        //option with no arg constructor
        Meal meal = spy(Meal.class);
        //option with meal with args
        Meal mealWithParamsSpy = spy(mealInstance);

        given(mealSpy.getPrice()).willReturn(15);
        given(mealSpy.getQuantity()).willReturn(3);


        //when
        int result = mealSpy.sumPrice();

        //then
        then(mealSpy).should().getPrice();

        //then
        assertThat(result, equalTo(45));
    }
}
