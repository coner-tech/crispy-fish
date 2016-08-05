package org.coner.crispy_fish.domain;

public enum EventDay {
    ONE(1),
    TWO(2);

    private final int sequence;

    EventDay(int sequence) {
        this.sequence = sequence;
    }

    public int getSequence() {
        return sequence;
    }
}
