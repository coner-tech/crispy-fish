package tech.coner.crispyfish.model

import assertk.Assert
import assertk.assertions.*

fun Assert<RegistrationRun>.hasTime(expected: String) {
    prop("time", RegistrationRun::time).isEqualTo(expected)
}

fun Assert<RegistrationRun>.isClean() {
    prop("penalty", RegistrationRun::penalty).isNull()
}

fun Assert<RegistrationRun>.isNotClean() {
    prop("penalty", RegistrationRun::penalty).isNotNull()
}

fun Assert<RegistrationRun>.hasConeCount(expected: Int) {
    prop("penalty", RegistrationRun::penalty)
            .isNotNull()
            .isInstanceOf(RegistrationRun.Penalty.Cone::class)
            .prop("count", RegistrationRun.Penalty.Cone::count)
            .isEqualTo(expected)
}

fun Assert<RegistrationRun>.isDidNotFinish() {
    prop("penalty", RegistrationRun::penalty)
            .isNotNull()
            .isSameAs(RegistrationRun.Penalty.DidNotFinish)
}

fun Assert<RegistrationRun>.isDisqualified() {
    prop("penalty", RegistrationRun::penalty)
            .isNotNull()
            .isSameAs(RegistrationRun.Penalty.Disqualified)
}
