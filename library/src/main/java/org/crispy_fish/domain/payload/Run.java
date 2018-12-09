package org.crispy_fish.domain.payload;

import org.coner.crispy_fish.domain.PenaltyType;

import java.time.Duration;

public class Run {

    public static final int CONES_UNKNOWN = Integer.MIN_VALUE;

    public Duration rawTime;
    public Duration paxTime;
    public PenaltyType penaltyType;
    public int cones;
    public Duration timeScored;
    public String timeScratchAsString;
    public Duration timeScratchAsDuration;


}
