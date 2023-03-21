package kubala.testing.meal;

import javax.naming.directory.SearchResult;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

public class MealRepository {

    private List<Meal> meals = new ArrayList<>();

    public void add(Meal meal) {
        meals.add(meal);
    }

    public List<Meal> getAllMeals() {
        return meals;
    }

    public void delete(Meal meal) {
        meals.remove(meal);
    }

    public List<Meal> findByName(String mealName, boolean exactMatch) {

        List<Meal> result;

        if (exactMatch) {
            result = meals.stream()
                    .filter(meal -> meal.getName().equals(mealName))
                    .collect(Collectors.toList());
        } else {
            result = meals.stream()
                    .filter(meal -> meal.getName().startsWith(mealName))
                    .collect(Collectors.toList());
        }
        return result;
    }

    public List<Meal> findByPrice(int price, SearchType searchType) {

        List<Meal> result = new ArrayList<>();

        switch (searchType) {
            case EXACT -> {
                result = meals.stream()
                        .filter(meal -> meal.getPrice() == price)
                        .collect(Collectors.toList());
            }
            case LESS -> {
                result = meals.stream()
                        .filter(meal -> meal.getPrice() < price)
                        .collect(Collectors.toList());
            }
            case MORE -> {
                result = meals.stream()
                        .filter(meal -> meal.getPrice() > price)
                        .collect(Collectors.toList());
            }
        }
        return result;
    }
}
