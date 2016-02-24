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

    private HandicapGroup(Builder builder) {
        abbreviation = builder.abbreviation;
        name = builder.name;
        pax = builder.pax;
        competitionGroup = builder.competitionGroup;
    }

    public static Builder newBuilder() {
        return new Builder();
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

    public static final class Builder {
        private String abbreviation;
        private String name;
        private float pax;
        private CompetitionGroup competitionGroup;

        private Builder() {
        }

        public Builder abbreviation(String val) {
            abbreviation = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder pax(float val) {
            pax = val;
            return this;
        }

        public Builder competitionGroup(CompetitionGroup val) {
            competitionGroup = val;
            return this;
        }

        public HandicapGroup build() {
            return new HandicapGroup(this);
        }
    }
}
