package pl.adambaranowski.testing.order;

import org.junit.jupiter.api.*;
import pl.adambaranowski.testing.Meal;
import pl.adambaranowski.testing.order.Order;
import pl.adambaranowski.testing.order.OrderBackup;

import java.io.FileNotFoundException;
import java.io.IOException;

class OrderBackupTest {

    private static OrderBackup orderBackup;
    private Order order;

    @BeforeAll
    static void setup() throws FileNotFoundException {
        orderBackup = new OrderBackup();
        orderBackup.createFile();
    }

    @AfterAll
    static void tearDown() throws IOException {
        orderBackup.closeFile();
    }

    @BeforeEach
    void setOrder(){
        Meal meal = new Meal(7, "Fries");
        order = new Order();
        order.addMealToOrder(meal);
    }

    @AfterEach
    void clearOrder(){
        order.cancel();
    }



    @Test
    void backupOrderWithOneMeal() throws IOException {
        // given


        //when
        orderBackup.backupOrder(order);

        //then
        System.out.println(order.toString());

    }

}