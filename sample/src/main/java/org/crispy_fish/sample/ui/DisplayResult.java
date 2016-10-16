package org.crispy_fish.sample.ui;

import org.crispy_fish.domain.Result;

import java.util.concurrent.TimeUnit;

public class DisplayResult {
    public final Result domainResult;
    public final String timeForDisplay;
    public final String penaltyForDisplay;

    public DisplayResult(Result domainResult) {
        this.domainResult = domainResult;
        switch (domainResult.run.penaltyType) {
            case CLEAN:
            case DID_NOT_FINISH:
            case DISQUALIFIED:
                timeForDisplay = domainResult.run.timeScratchAsString;
                break;
            case CONE:
                timeForDisplay = String.format(
                        "%d.%d",
                        domainResult.run.timeScored.getSeconds(),
                        TimeUnit.NANOSECONDS.toMillis(domainResult.run.timeScored.getNano())
                );
                break;
            case RERUN:
                throw new IllegalStateException("Reruns should never bubble up to display");
            default:
                throw new IllegalStateException("Unrecognized penalty type " + domainResult.run.penaltyType);
        }
        switch (domainResult.run.penaltyType) {
            case CLEAN:
                penaltyForDisplay = "";
                break;
            case CONE:
                penaltyForDisplay = String.format("+%d", domainResult.run.cones);
                break;
            case DID_NOT_FINISH:
                penaltyForDisplay = "DNF";
                break;
            case DISQUALIFIED:
                penaltyForDisplay = "DSQ";
                break;
            case RERUN:
                throw new IllegalStateException("Reruns should never bubble up to display");
            default:
                throw new IllegalStateException("Unrecognized penalty type " + domainResult.run.penaltyType);
        }
    }

}
