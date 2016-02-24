package org.coner.crispy_fish.domain.driver;

import org.coner.crispy_fish.domain.run.Run;
import org.coner.crispy_fish.domain.car.Car;
import org.coner.crispy_fish.domain.competition_group.CompetitionGroup;
import org.coner.crispy_fish.domain.handicap_group.HandicapGroup;

import java.util.List;

public class Driver {

    private final HandicapGroup handicapGroup;
    private final CompetitionGroup competitionGroup;
    private final String number;
    private final String name;
    private final Car car;
    private final List<Run> runs;

    public Driver(HandicapGroup handicapGroup, CompetitionGroup competitionGroup, String number, String name, Car car, List<Run> runs) {
        this.handicapGroup = handicapGroup;
        this.competitionGroup = competitionGroup;
        this.number = number;
        this.name = name;
        this.car = car;
        this.runs = runs;
    }

    public HandicapGroup getHandicapGroup() {
        return handicapGroup;
    }

    public CompetitionGroup getCompetitionGroup() {
        return competitionGroup;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public Car getCar() {
        return car;
    }

    public List<Run> getRuns() {
        return runs;
    }

}
