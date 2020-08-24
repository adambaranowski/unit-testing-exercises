package pl.adambaranowski.testing.homework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class UnitTest {

    private Unit unit;

    @BeforeEach
    void setUnit(){
        unit = new Unit(new Coordinates(10, 10), 10, 10);
    }

    @Test
    void moveShouldThrowIllegalStateExceptionWhenXPlusYGreaterThanFuel() {
        //given
        Unit unit1 = new Unit(new Coordinates(10, 10), 10, 10);
        //when
        //then
        assertThrows(IllegalStateException.class, () -> unit1.move(10, 10));
    }

    @Test
    void moveShouldReturnNewCoordinatesWithAddedXAndYAndDecreaseFuel() {
        //given
        //when
        Coordinates coordinates = unit.move(1, 1);

        //then
        assertAll(
                () -> assertEquals(11, coordinates.getX()),
                () -> assertEquals(11, coordinates.getY()),
                () -> assertEquals(8, unit.getFuel())
        );
    }

    @Test
    void tankUpShouldIncreaseFuel() {
        //given
        unit.move(3,3);
        int fuelAfterMove = unit.getFuel();

        //when
        unit.tankUp();
        int fuelAfterTankUp = unit.getFuel();

        //then
        assertThat(fuelAfterMove, lessThanOrEqualTo(fuelAfterTankUp));

    }

    @Test
    void loadCargoShouldThrowAnExceptionWhenCargoWeightIsGreaterThanMaxCargoWeight() {
        assertThrows(IllegalStateException.class, () -> unit.loadCargo(new Cargo("bigWeight", 20)));
    }

    @Test
    void loadCargoShouldAddCargoAndIncreaseCurrentWeight(){
        //given
        //when
        Cargo cargo1 = new Cargo("cargo1", 2);
        Cargo cargo2 = new Cargo("cargo2", 3);
        unit.loadCargo(cargo1);
        unit.loadCargo(cargo2);

        //then
        assertAll(
                () -> assertThat(cargo1, is(in(unit.getCargo()))),
                () -> assertThat(cargo2, is(in(unit.getCargo()))),
                () -> assertEquals(5,unit.getLoad())
        );
    }

    @Test
    void unloadCargoShouldRemoveCargoAndDecreaseTheWeight() {
        //given
        //when
        Cargo cargo1 = new Cargo("cargo1", 2);
        Cargo cargo2 = new Cargo("cargo2", 3);
        unit.loadCargo(cargo1);
        unit.loadCargo(cargo2);

        unit.unloadCargo(cargo1);

        //then
        assertAll(
                () -> assertThat(cargo1, is(not(in(unit.getCargo())))),
                () -> assertThat(cargo2, is(in(unit.getCargo()))),
                () -> assertEquals(3,unit.getLoad())
        );

    }

    @Test
    void unloadAllCargoShouldRemoveAllCargo() {
        //given
        //when
        Cargo cargo1 = new Cargo("cargo1", 2);
        Cargo cargo2 = new Cargo("cargo2", 3);
        unit.loadCargo(cargo1);
        unit.loadCargo(cargo2);

        unit.unloadAllCargo();

        assertAll(
                () -> assertThat(unit.getCargo(), is(emptyCollectionOf(Cargo.class))),
                () -> assertThat(unit.getLoad(), is(equalTo(0)))
        );
    }
}