package kubala.testing.homework_mockito;
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
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class CoordinatesTest {

    @Test
    public void coordinatesConstructorShouldFailAnyValueBelow0() {

        //given
        //when
        //then
        assertThrows(IllegalArgumentException.class,() -> new Coordinates(-1, 100));
    }

    @Test
    public void coordinatesConstructorShouldFailAnyValueAbove100() {

        //given
        //when
        //then
        assertThrows(IllegalArgumentException.class,() -> new Coordinates(10, 101));
    }

    @Test
    public void copyShouldReturnNewObject() {

        //given
        Coordinates coordinates = new Coordinates(10, 50);

        //when
        Coordinates copy = Coordinates.copy(coordinates,0, 0);

        //then
        assertThat(copy, not(sameInstance(coordinates)));
        assertThat(copy, equalTo(coordinates));
    }

    @Test
    public void copyShouldReturnAddCoordinates() {

        //given
        Coordinates coordinates = new Coordinates(10, 50);

        //when
        Coordinates copy = Coordinates.copy(coordinates,5, 10);

        //then
        assertThat(copy.getX(), equalTo(15));
        assertThat(copy.getY(), equalTo(60));
    }
}
