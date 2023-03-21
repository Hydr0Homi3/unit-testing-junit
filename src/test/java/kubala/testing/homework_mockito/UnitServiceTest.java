package kubala.testing.homework_mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/*Bazując na poniższych klasach, utwórz testy dla klasy UnitService.
Do stworzenia mocków dla UnitRepository i CargoRepository skorzystaj z frameworka Mockito 2.
Możesz używać dowolnych typów asercji: Hamcrestowych lub też innych.
*/

@ExtendWith(MockitoExtension.class)
class UnitServiceTest {

    @Mock
    private CargoRepository cargoRepository;

    @Mock
    private UnitRepository unitRepository;

    @InjectMocks
    private UnitService unitService;

    @Test
    public void addedCargoShouldBeLoadedToUnit() {

        //given
        Unit unit = new Unit(new Coordinates(0, 0), 10, 10);
        Cargo cargo = new Cargo("package", 8);

        given(cargoRepository.findCargoByName("package")).willReturn(Optional.of(cargo));

        //when
        unitService.addCargoByName(unit, "package");

        //then
        verify(cargoRepository).findCargoByName("package");
        assertThat(unit.getLoad(), is(8));
        assertThat(unit.getCargo().get(0), is(cargo));
    }

    @Test
    public void shouldThrowExceptionIfThereIsNoCargoToAdd() {

        //given
        Unit unit = new Unit(new Coordinates(0, 0), 10, 10);

        given(cargoRepository.findCargoByName("package")).willReturn(Optional.empty());

        //when
        //then
        assertThrows(NoSuchElementException.class, () -> unitService.addCargoByName(unit, "package"));
    }

    @Test
    public void shouldReturnUnitByCoordinates() {

        //given
        Unit unit = new Unit(new Coordinates(0, 0), 10, 10);

        given(unitRepository.getUnitByCoordinates(new Coordinates(0, 0))).willReturn(unit);

        //when
        Unit result = unitService.getUnitOn(new Coordinates(0,0));

        //then
        assertThat(result, sameInstance(unit));
    }

    @Test
    public void shouldReturnExceptionWhenUnitNotFound() {

        //given
        given(unitRepository.getUnitByCoordinates(new Coordinates(0, 0))).willReturn(null);

        //when
        //then
        assertThrows(NoSuchElementException.class, () -> unitService.getUnitOn(new Coordinates(0, 0)));
    }
}