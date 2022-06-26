package tech.coner.crispyfish.query

import tech.coner.crispyfish.filetype.ecf.EventControlFile
import tech.coner.crispyfish.model.EventDay
import tech.coner.crispyfish.model.Registration
import tech.coner.crispyfish.model.RegistrationRun
import tech.coner.crispyfish.model.ThsccConeKillerResult

class ThsccConeKillerQuery(
    private val eventControlFile: EventControlFile,
    private val eventDay: EventDay = EventDay.ONE
) {

    fun query(): List<ThsccConeKillerResult> {
        val registrations = eventControlFile.queryRegistrations()
        val registrationsAndConedRuns = registrations
                .map { registration ->
                    registration to registration.runs.filter { run ->
                        run.penalty is RegistrationRun.Penalty.Cone
                    }
                }
                .map { (registration, conedRuns) ->
                    registration to conedRuns.sortedByDescending {
                        (it.penalty as RegistrationRun.Penalty.Cone).count
                    }
                }
        return registrationsAndConedRuns.sortedWith(comparator)
                .mapIndexed { index, (registration, conedRuns) ->
                    ThsccConeKillerResult(
                            position = index + 1,
                            registration = registration,
                            conedRuns = conedRuns
                    )
                }
    }

    private val comparator = Comparator<Pair<Registration, List<RegistrationRun>>> { left, right ->
        val (leftRegistration, leftConedRuns) = left
        val (rightRegistration, rightConedRuns) = right
        val padToSize = maxOf(leftConedRuns.size, rightConedRuns.size)
        val mapCones = { run: RegistrationRun -> (run.penalty as RegistrationRun.Penalty.Cone).count }
        val leftCones = leftConedRuns.map(mapCones).sortedDescending()
        val rightCones = rightConedRuns.map(mapCones).sortedDescending()
        val compareLeftCones = leftCones.padded(toSize = padToSize, withValue = 0)
        val compareRightCones = rightCones.padded(toSize = padToSize, withValue = 0)
        for ((leftCone, rightCone) in compareLeftCones.zip(compareRightCones)) {
            if (leftCone == rightCone) continue
            else if (leftCone < rightCone) return@Comparator 1
            else if (leftCone > rightCone) return@Comparator -1
        }
        0
    }

    private fun List<Int>.padded(toSize: Int, withValue: Int): List<Int> {
        if (this.size >= toSize) return toList()
        return toMutableList().apply {
            while (size < toSize) {
                add(withValue)
            }
        }
    }
}