package org.crispy_fish.query;

import java.time.Duration;

public class RawResult {

    public int position;
    public Driver driver;
    public Times times;

    public static class Times {
        public Duration scratch;
        public Duration fromPenalty;
        public Duration sort;
    }

    public static class Penalty {
        private PenaltyType penaltyType;
        private int cones;
    }
}
