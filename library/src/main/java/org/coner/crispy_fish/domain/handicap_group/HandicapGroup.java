package org.coner.crispy_fish.domain.handicap_group;

import org.coner.crispy_fish.domain.competition_group.CompetitionGroup;

public class HandicapGroup {

    private final String abbreviation;
    private final String name;
    private final float pax;
    private final CompetitionGroup competitionGroup;

    public HandicapGroup(String abbreviation, String name, float pax, CompetitionGroup competitionGroup) {
        this.abbreviation = abbreviation;
        this.name = name;
        this.pax = pax;
        this.competitionGroup = competitionGroup;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getName() {
        return name;
    }

    public float getPax() {
        return pax;
    }

    public CompetitionGroup getCompetitionGroup() {
        return competitionGroup;
    }
}
