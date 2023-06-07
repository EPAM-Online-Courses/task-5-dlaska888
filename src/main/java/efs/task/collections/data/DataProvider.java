package efs.task.collections.data;

import efs.task.collections.entity.Hero;
import efs.task.collections.entity.Town;

import java.util.List;
import java.util.Set;
import java.util.Arrays;
import java.util.stream.Collectors;

public class DataProvider
{

    public static final String DATA_SEPARATOR = ",";

    // TODO Utwórz listę miast na podstawie tablicy Data.baseTownsArray.
    //  Tablica zawiera String zawierający nazwę miasta oraz dwie klasy bohatera związane z tym miastem oddzielone przecinkami.
    //  Korzystając z funkcji split() oraz stałej DATA_SEPARATOR utwórz listę obiektów klasy efs.task.collections.entities.Town.
    //  Funkcja zwraca listę obiektów typu Town ze wszystkimi dziewięcioma podstawowymi miastami.
    public List<Town> getTownsList()
    {
        return Arrays.stream(Data.baseTownsArray)
                .map(d -> d.split(DATA_SEPARATOR))
                .map(d -> Arrays.stream(d).map(String::trim).toArray(unused -> d)) // wpisujemy do tej samej tablicy
                .map(d -> new Town(d[0], List.of(d[1], d[2])))
                .toList();
    }

    //TODO Analogicznie do getTownsList utwórz listę miast na podstawie tablicy Data.DLCTownsArray
    public List<Town> getDLCTownsList()
    {
        return Arrays.stream(Data.dlcTownsArray)
                .map(d -> d.split(DATA_SEPARATOR))
                .map(d -> Arrays.stream(d).map(String::trim).toArray(unused -> d))
                .map(d -> new Town(d[0], List.of(d[1], d[2])))
                .toList();
    }

    //TODO Na podstawie tablicy Data.baseCharactersArray utworzyć listę bohaterów dostępnych w grze.
    // Tablica Data.baseCharactersArray zawiera oddzielone przecinkiem imie bohatera, klasę bohatera.
    // Korzystając z funkcji split() oraz DATA_SEPARATOR utwórz listę unikalnych obiektów efs.task.collections.entities.Hero.
    // UWAGA w Data.baseCharactersArray niektórzy bohaterowie powtarzają się, do porównania bohaterów używamy zarówno imie jak i jego klasę;
    public Set<Hero> getHeroesSet()
    {
        return Arrays.stream(Data.baseCharactersArray)
                .map(d -> d.split(DATA_SEPARATOR))
                .map(d -> Arrays.stream(d).map(String::trim).toArray(unused -> d))
                .map(d -> new Hero(d[0], d[1]))
                .collect(Collectors.toSet());
    }

    //TODO Analogicznie do getHeroesSet utwórz listę bohaterów na podstawie tablicy Data.DLCCharactersArray
    public Set<Hero> getDLCHeroesSet()
    {
        return Arrays.stream(Data.dlcCharactersArray)
                .map(d -> d.split(DATA_SEPARATOR))
                .map(d -> Arrays.stream(d).map(String::trim).toArray(unused -> d))
                .map(d -> new Hero(d[0], d[1]))
                .collect(Collectors.toSet());
    }
}
