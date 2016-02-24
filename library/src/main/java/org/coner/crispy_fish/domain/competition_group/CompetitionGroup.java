package org.coner.crispy_fish.domain.competition_group;

public class CompetitionGroup {

    private final String name;
    private final boolean pax;
    private final CompetitionGroup parent;

    public CompetitionGroup(String name, boolean pax, CompetitionGroup parent) {
        this.name = name;
        this.pax = pax;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public boolean isPax() {
        return pax;
    }

    public CompetitionGroup getParent() {
        return parent;
    }
}
