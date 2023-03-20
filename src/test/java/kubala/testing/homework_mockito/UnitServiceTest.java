package kubala.testing.homework_mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

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


}