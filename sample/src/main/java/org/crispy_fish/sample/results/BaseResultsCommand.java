package org.crispy_fish.sample.results;

import io.airlift.airline.Arguments;
import io.airlift.airline.Option;
import org.crispy_fish.domain.EventDay;

public abstract class BaseResultsCommand {

    @Arguments(
            required = true,
            title = "Event Control File",
            description = "Path to the Event Control File (*.ecf)"
    )
    public String file;

    @Option(
            name = {"-d", "--day"},
            title = "Event Day",
            description = "Print results for a given day"
    )
    public EventDay eventDay = EventDay.ONE;

    @Option(
            name = {"-D", "--days" },
            title = "Event Days",
            description = "Treat event as 1 or 2-day event",
            allowedValues = {"1", "2"}

    )
    public int eventDays = 1;

    @Option(
            name = "--cone-penalty",
            title = "Cone Penalty",
            description = "Seconds to apply for each cone penalty"
    )
    public int conePenalty = 2;
}
