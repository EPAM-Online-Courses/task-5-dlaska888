package efs.task.collections.entity;

import java.util.List;

public class Town implements Comparable {
    private String townName;
    private List<String> startingHeroClasses;

    public Town(String townName, List<String> startingHeroesClass) {
        this.townName = townName;
        startingHeroClasses = startingHeroesClass;
    }

    public String getTownName() {
        return townName;
    }

    public List<String> getStartingHeroClasses() {
        return startingHeroClasses;
    }

    //TODO implementacja metody equal porównująca obiekty Town na podstawie tylko townName.
    @Override
    public boolean equals(Object o) {
        return true;
    }

    //TODO implementacja metody equal biorąca pod uwagę tylko townName.
    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public String toString() {
        return "Miasto :" + townName;
    }

    @Override public int compareTo(Object o)
    {
        if(this == o)
            return 0;

        if(!(o instanceof Town))
            return -1;

        return this.townName.compareTo(((Town) o).townName);
    }
}
