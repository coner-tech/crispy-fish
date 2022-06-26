package tech.coner.crispyfish.query

import tech.coner.crispyfish.filetype.ecf.EventControlFile
import tech.coner.crispyfish.model.EventDay
import tech.coner.crispyfish.model.Result

class PaxTimeResultsQuery(
    private val eventControlFile: EventControlFile,
    private val eventDay: EventDay = EventDay.ONE
) {

    @Throws(QueryException::class)
    fun query(): List<Result> {
        val registrations = eventControlFile.queryRegistrations()
        return registrations.mapNotNull { registration ->
            registration.paxResult?.let { paxResult ->
                Result(
                    driver = registration,
                    position = paxResult.position,
                    time = paxResult.time
                )
            }
        }.sortedBy(Result::position)
    }

}
