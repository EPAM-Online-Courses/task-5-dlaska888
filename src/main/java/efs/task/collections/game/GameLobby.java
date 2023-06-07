package efs.task.collections.game;

import efs.task.collections.data.DataProvider;
import efs.task.collections.entity.Hero;
import efs.task.collections.entity.Town;

import java.util.*;

public class GameLobby
{

    public static final String HERO_NOT_FOUND = "Nie ma takiego bohatera ";
    public static final String NO_SUCH_TOWN = "Nie ma takiego miasta ";

    private final DataProvider dataProvider;
    private Map<Town, List<Hero>> playableTownsWithHeroesList;

    public GameLobby()
    {
        this.dataProvider = new DataProvider();
        this.playableTownsWithHeroesList =
                mapHeroesToStartingTowns(dataProvider.getTownsList(), dataProvider.getHeroesSet());
    }

    public Map<Town, List<Hero>> getPlayableTownsWithHeroesList()
    {
        return playableTownsWithHeroesList;
    }

    //TODO Dodać miasta i odpowiadających im bohaterów z DLC gry do mapy dostępnych
    // miast - playableTownsWithHeroesList, tylko jeżeli jeszcze się na niej nie znajdują.
    public void enableDLC()
    {
        playableTownsWithHeroesList
                .putAll(mapHeroesToStartingTowns(dataProvider.getDLCTownsList(), dataProvider.getDLCHeroesSet()));
    }


    //TODO Usunąć miasta i odpowiadających im bohaterów z DLC gry z mapy dostępnych
    // miast - playableTownsWithHeroesList.
    public void disableDLC()
    {
        dataProvider.getDLCTownsList().forEach(t -> playableTownsWithHeroesList.remove(t));
    }

    // TODO Sprawdza czy mapa playableCharactersByTown zawiera dane miasto.
    //  Jeśli tak zwróć listę bohaterów z tego miasta.
    //  Jeśli nie rzuć wyjątek NoSuchElementException z wiadomością NO_SUCH_TOWN + town.getName()
    public List<Hero> getHeroesFromTown(Town town)
    {
        var result = playableTownsWithHeroesList.containsKey(town);

        if(!result)
            throw new NoSuchElementException(NO_SUCH_TOWN + town.getTownName());

        return playableTownsWithHeroesList.get(town);
    }

    // TODO Metoda powinna zwracać mapę miast w kolejności alfabetycznej z odpowiadającymi im bohaterami.
    //  Każde z miast charakteryzuje się dwoma klasami bohaterów dostępnymi dla tego miasta - Town.startingHeroClass.
    //  Mapa ma zawierać pare klucz-wartość gdzie klucz: miasto, wartość: lista bohaterów;
    public Map<Town, List<Hero>> mapHeroesToStartingTowns(List<Town> availableTowns, Set<Hero> availableHeroes)
    {
        var map = new TreeMap<Town, List<Hero>>();
        availableTowns.forEach(t -> map.put(t,
                        new ArrayList<>(availableHeroes
                                .stream()
                                .filter(h -> t.getStartingHeroClasses().contains(h.getHeroClass()))
                                .toList()))
                );
        return map;
    }

    //TODO metoda zwraca wybranego bohatera na podstawie miasta z którego pochodzi i imienia.
    // Jeżeli istnieje usuwa go z listy dostępnych bohaterów w danym mieście i zwraca bohatera.
    // Jeżeli nie ma go na liście dostępnych bohaterów rzuca NoSuchElementException z wiadomością HERO_NOT_FOUND + name
    public Hero selectHeroByName(Town heroTown, String name)
    {
        Optional<Hero> result = playableTownsWithHeroesList.get(heroTown)
                .stream()
                .filter(h -> h.getName().equals(name))
                .findAny();

        if(result.isEmpty())
            throw new NoSuchElementException(HERO_NOT_FOUND + name);

        Hero temp = result.get();
        playableTownsWithHeroesList.get(heroTown).remove(result.get());
        return temp;
    }
}