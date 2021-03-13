package org.coner.crispyfish.model

data class RegistrationRun(
        val time: String?,
        val penalty: Penalty?
) {
    sealed class Penalty {
        class Cone(val count: Int) : Penalty()
        object DidNotFinish : Penalty()
        object Disqualified : Penalty()
        object Unknown : Penalty()
    }
}