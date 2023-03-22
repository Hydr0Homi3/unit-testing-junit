package kubala.testing.meal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/*
W ramach ćwiczeń z metodyką Test Driven Development, mamy dla Ciebie dwa zadania:
Zadanie nr 1:

Należy utworzyć rozszerzenie metody findByPrice tak by można było szukać po cenie niższej, równej i wyższej niż podana.

Podpowiedź: zamiast boolean warto jako drugi element użyć np. Enum określającego typ operacji.

Zadanie nr 2:

Analitycy doszli do wniosku, że częstym przypadkiem użycia będzie wyszukiwanie po nazwie i po cenie jednocześnie.
Musisz więc dodać metodę find w MealRepository, która będzie wyszukiwać posiłki według nazwy (dokładnej, bądź po pierwszych literach)
oraz po cenie (większa, mniejsza, równa). Metoda może więc przymować  cztery argumenty -
nazwa posiłku, flage czy wyszukać dokładną nazwę czy nie, a następnie cenę oraz rodzaj wyszukiwania.
 */

public class MealRepositoryTest {

    MealRepository mealRepository = new MealRepository();

    @BeforeEach
    public void cleanUp() {
        mealRepository.getAllMeals().clear();
    }

    @Test
    public void shouldBeAbleToAddMealToRepository() {

        //given
        Meal meal = new Meal(10, "Pizza");

        //when
        mealRepository.add(meal);

        //then
        assertThat(mealRepository.getAllMeals().get(0), is(meal));
    }

    @Test
    public void shouldBeAbleToRemoveMealFromRepository() {

        //given
        Meal meal = new Meal(10, "Pizza");
        mealRepository.add(meal);

        //when
        mealRepository.delete(meal);

        //then
        assertThat(mealRepository.getAllMeals(), not(contains(meal)));
    }

    @Test
    public void shouldBeAbleToFindMealByExactName() {

        //given
        Meal meal = new Meal(10, "Pizza");
        Meal meal2 =  new Meal(10, "Pi");
        mealRepository.add(meal);
        mealRepository.add(meal2);

        //when
        List<Meal> results = mealRepository.findByName("Pizza", true);

        //then
        assertThat(results.size(), is(1));
    }

    @Test
    public void shouldBeAbleToFindMealByStartingLetters() {

        //given
        Meal meal = new Meal(10, "Pizza");
        Meal meal2 =  new Meal(10, "Pi");
        mealRepository.add(meal);
        mealRepository.add(meal2);

        //when
        List<Meal> results = mealRepository.findByName("P", false);

        //then
        assertThat(results.size(), is(2));
    }

    @Test
    void shouldBeAbleToFindMealByExactPrice() {

        //given
        Meal meal = new Meal(10, "Pizza");
        Meal meal2 = new Meal(9, "Burger");

        mealRepository.add(meal);
        mealRepository.add(meal2);

        //when
        List<Meal> results = mealRepository.findByPrice(10, SearchType.EXACT);

        //then
        assertThat(results.size(),is(1));
        assertThat(results.get(0), is(meal));
    }

    @Test
    void shouldBeAbleToFindMealByLesserPrice() {

        //given
        Meal meal = new Meal(10, "Pizza");
        Meal meal2 = new Meal(9, "Burger");

        mealRepository.add(meal);
        mealRepository.add(meal2);

        //when
        List<Meal> results = mealRepository.findByPrice(10, SearchType.LESS);

        //then
        assertThat(results.size(),is(1));
        assertThat(results.get(0), is(meal2));
    }

    @Test
    void shouldBeAbleToFindMealByHigherPrice() {

        //given
        Meal meal = new Meal(11, "Pizza");
        Meal meal2 = new Meal(9, "Burger");

        mealRepository.add(meal);
        mealRepository.add(meal2);

        //when
        List<Meal> results = mealRepository.findByPrice(10, SearchType.MORE);

        //then
        assertThat(results.size(),is(1));
        assertThat(results.get(0), is(meal));
    }

    @Test
    void shouldFindByExactNameAndExactPrice() {

        //given
        Meal meal = new Meal(10, "Pizza");
        Meal meal2 = new Meal(9, "Burger");

        mealRepository.add(meal);
        mealRepository.add(meal2);

        //when
        List<Meal> results = mealRepository.find("Pizza", true, 10, SearchType.EXACT);

        //then
        assertThat(results.size(),is(1));
        assertThat(results.get(0), is(meal));
    }

    @Test
    void shouldFindByFirstLetterAndExactPrice() {
        //given
        Meal meal = new Meal(10, "Pizza");
        Meal meal2 = new Meal(9, "Burger");

        mealRepository.add(meal);
        mealRepository.add(meal2);

        //when
        List<Meal> results = mealRepository.find("B", false, 9, SearchType.EXACT);

        //then
        assertThat(results.size(),is(1));
        assertThat(results.get(0), is(meal2));
    }

    @Test
    void shouldFindByExactNameAndLowerPrice() {
        //given
        Meal meal = new Meal(10, "Pizza");
        Meal meal2 = new Meal(9, "Burger");

        mealRepository.add(meal);
        mealRepository.add(meal2);

        //when
        List<Meal> results = mealRepository.find("Pizza", true, 11, SearchType.LESS);

        //then
        assertThat(results.size(),is(1));
        assertThat(results.get(0), is(meal));
    }

    @Test
    void shouldFindByFirstLetterAndLowerPrice() {
        //given
        Meal meal = new Meal(10, "Pizza");
        Meal meal2 = new Meal(9, "Burger");

        mealRepository.add(meal);
        mealRepository.add(meal2);

        //when
        List<Meal> results = mealRepository.find("B", false, 10, SearchType.LESS);

        //then
        assertThat(results.size(),is(1));
        assertThat(results.get(0), is(meal2));
    }

    @Test
    void shouldFindByExactNameAndHigherPrice() {
        //given
        Meal meal = new Meal(10, "Pizza");
        Meal meal2 = new Meal(9, "Burger");

        mealRepository.add(meal);
        mealRepository.add(meal2);

        //when
        List<Meal> results = mealRepository.find("Pizza", true, 9, SearchType.MORE);

        //then
        assertThat(results.size(),is(1));
        assertThat(results.get(0), is(meal));
    }

    @Test
    void shouldFindByFirstLetterAndHigherPrice() {

        //given
        Meal meal = new Meal(10, "Pizza");
        Meal meal2 = new Meal(9, "Burger");

        mealRepository.add(meal);
        mealRepository.add(meal2);

        //when
        List<Meal> results = mealRepository.find("B", false, 8, SearchType.MORE);

        //then
        assertThat(results.size(),is(1));
        assertThat(results.get(0), is(meal2));
    }
}