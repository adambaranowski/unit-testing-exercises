package pl.adambaranowski.testing.homework;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CoordinatesTest {

    private static Stream<Arguments> coordinates() {
        return Stream.of(
                Arguments.of(101, 101),
                Arguments.of(-1, -1),
                Arguments.of(-1, 1100),
                Arguments.of(-1, 1),
                Arguments.of(1, -1),
                Arguments.of(90, 110),
                Arguments.of(110, 90)
        );
    }

    @Test
    @DisplayName("Constructor should not throw an exception when coordinates in range 0 to 100")
    void positionsOutOfRange0To100ShouldThrowAnIllegalArgException() {
        //assertAll requires exceptions not to be thrown
        assertAll(() -> {
            new Coordinates(10, 20);
        });
    }

    @ParameterizedTest
    @MethodSource("coordinates")
    @DisplayName("Positions x and y should be in range <0, 100>")
    void parametrizedConstructorTest(int x, int y) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Coordinates(x, y);
        });
    }

    @Test
    void coordinatesCopyShouldReturnCoordinatesWithAddedInParametersValues() {
        //given
        Coordinates coordinates = new Coordinates(10, 10);

        //when
        Coordinates coordinatesCopy = Coordinates.copy(coordinates, 10, 10);

        //then
        assertAll(
                () -> assertEquals(20, coordinatesCopy.getX()),
                () -> assertEquals(20, coordinatesCopy.getY())
        );

    }
}