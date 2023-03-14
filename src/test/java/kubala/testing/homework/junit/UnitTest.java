package kubala.testing.homework.junit;
/*Przed Tobą pierwsze wyzwanie!

Twoim zadaniem jest stworzenie metod testowych dla poniższych klas:
- Cargo
- Coordinates
- Unit
Możesz używać dowolnych typów asercji: Hamcrestowych lub też innych.

Rzeczy warte przetestowania to m.in. konstruktor Coordinates oraz metoda copy.
W klasie Cargo nie ma żadnej zaawansowanej logiki do sprawdzenia.
W tym przypadku nie ma też potrzeby, aby testować settery, gettery oraz metody equals i hashCode.
Natomiast w klasie Unit warto przetestować wszystkie publiczne metody poza konstruktorem.
*/

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnitTest {

    @Test
    public void unitShouldNotMoveWithoutFuel() {

        //given
        Unit unit = new Unit(new Coordinates(10, 10), 0, 1000);

        //when
        //then
        assertThrows(IllegalStateException.class, () -> unit.move(5,5));
    }

    @Test
    public void unitShouldHaveLessFuelWhenMoving() {

        //given
        Unit unit = new Unit(new Coordinates(10, 10), 10, 10);

        //when
        unit.move(1, 1);

        //then
        assertThat(unit.getFuel(), is(8));
    }

    @Test
    public void unitShouldReturnNewCoordinatesWhenMoving() {

        //given
        Unit unit = new Unit(new Coordinates(10, 10), 10, 10);

        //when
        Coordinates move = unit.move(1, 1);

        //then
        assertThat(move, equalTo(new Coordinates(11 ,11)));
    }

    @Test
    public void fuelingShouldNotExceedMaxFuelLimit() {

        //given
        Unit unit = new Unit(new Coordinates(10, 10), 10, 10);

        //when
        unit.tankUp();

        //then
        assertThat(unit.getFuel(), is(10));
    }

    @Test
    public void cargoShouldNotExceedMaxWeightLimit() {

        //given
        Unit unit = new Unit(new Coordinates(10, 10), 10, 10);

        Cargo cargo1 = new Cargo("cargo1", 5);
        Cargo cargo2 = new Cargo("cargo2", 6);

        //when
        unit.loadCargo(cargo1);

        //then
        assertThrows(IllegalStateException.class, () -> unit.loadCargo(cargo2));
        assertThrows(IllegalStateException.class, () -> unit.loadCargo(new Cargo("random", 11)));
    }

    @Test
    public void unloadingCargoShouldReduceUnitLoad() {

        //given
        Unit unit = new Unit(new Coordinates(0, 0), 10, 10);

        Cargo cargo1 = new Cargo("cargo1", 5);
        Cargo cargo2 = new Cargo("cargo2", 5);

        unit.loadCargo(cargo1);
        unit.loadCargo(cargo2);

        //when
        unit.unloadCargo(cargo1);

        //then
        assertThat(unit.getLoad(), is(5));
    }

    @Test
    public void unloadingAllCargoShouldReduceUnitLoadToZero() {

        //given
        Unit unit = new Unit(new Coordinates(0,0), 10, 10);

        Cargo cargo1 = new Cargo("cargo1", 5);
        Cargo cargo2 = new Cargo("cargo2", 5);

        unit.loadCargo(cargo1);
        unit.loadCargo(cargo2);

        //when
        unit.unloadAllCargo();

        //then
        assertThat(unit.getLoad(), is(0));
    }
}
