package PaintBall;

import java.util.Objects;

public class TeamClass implements Team {
    private static final int DEFAULT_BUNKER_NUMBER = 1;
    private String name;
    private boolean status;
    private String[] bunkers;
    private int size;

    public TeamClass(String name, String bunker){
        this.name = name;
        status = true;
        size = 0;
        bunkers = new String[DEFAULT_BUNKER_NUMBER];
        conquerBunker(bunker);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isActive() {
        return status;
    }

    @Override
    public void changeStatus() {
        status = false;
    }

    @Override
    public void conquerBunker(String name) {
        bunkers[size++] = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamClass teamClass = (TeamClass) o;
        return Objects.equals(name, teamClass.name);
    }
}
