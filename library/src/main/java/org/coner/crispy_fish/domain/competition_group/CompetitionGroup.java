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

    private CompetitionGroup(Builder builder) {
        name = builder.name;
        pax = builder.pax;
        parent = builder.parent;
    }

    public static Builder newBuilder() {
        return new Builder();
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

    public static final class Builder {
        private String name;
        private boolean pax;
        private CompetitionGroup parent;

        private Builder() {
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder pax(boolean val) {
            pax = val;
            return this;
        }

        public Builder parent(CompetitionGroup val) {
            parent = val;
            return this;
        }

        public CompetitionGroup build() {
            return new CompetitionGroup(this);
        }
    }
}
