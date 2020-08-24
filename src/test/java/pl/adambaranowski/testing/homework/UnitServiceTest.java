package pl.adambaranowski.testing.homework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

class UnitServiceTest {

    @Mock
    private UnitRepository unitRepository;

    @Mock
    private CargoRepository cargoRepository;

    @InjectMocks
    private UnitService unitService;

    private Unit unit;
    private Cargo cargo;
    private Coordinates coordinates;


    @BeforeEach
    void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @BeforeEach
    void initUnitAndCargo(){
        coordinates = new Coordinates(10,10);
        unit = new Unit(coordinates,10,10);
        cargo = new Cargo("sampleCargo", 10);
    }

    @Test
    void addedCargoShouldBeLoadedInUnit() {

        //given
        //in before each

        given(cargoRepository.findCargoByName("sampleCargo")).willReturn(Optional.of(cargo));

        //when
        unitService.addCargoByName(unit, "sampleCargo");

        //then

        //test if method findCargoByName is called
        then(cargoRepository).should().findCargoByName("sampleCargo");
        //test if cargo loaded in unit is current loaded cargo
        assertThat(unit.getCargo().get(0), equalTo(cargo));

    }

    @Test
    void addingCargoThatDoesntExistShouldThrowAnException(){
        //given
        //in before each

        given(cargoRepository.findCargoByName("sampleCargo")).willReturn(Optional.empty());

        //when
        assertThrows(NoSuchElementException.class, () -> unitService.addCargoByName(unit, "sampleCargo"));
    }

    @Test
    void getUnitOnShouldReturnUnit() {
        //given
        //in before each
        given(unitRepository.getUnitByCoordinates(coordinates)).willReturn(unit);

        //when
        Unit resultUnit = unitService.getUnitOn(coordinates);

        //then
        then(unitRepository).should().getUnitByCoordinates(coordinates);
        assertThat(resultUnit, equalTo(unit));

    }
    @Test
    void getUnitOnShouldThrowExceptionWhenNoUnitFound() {
        //given
        //in before each
        given(unitRepository.getUnitByCoordinates(coordinates)).willReturn(null);

        //when
        //then
        assertThrows(NoSuchElementException.class, () -> {
            unitService.getUnitOn(coordinates);
            then(unitRepository).should().getUnitByCoordinates(coordinates);
        });

    }
}