package tech.coner.crispyfish.query

import tech.coner.crispyfish.filetype.classdefinition.ClassDefinitionFile
import tech.coner.crispyfish.model.*
import tech.coner.crispyfish.filetype.ecf.EventControlFile

class PaxTimeResultsQuery(
        private val classDefinitionFile: ClassDefinitionFile,
        private val eventControlFile: EventControlFile,
        private val eventDay: EventDay = EventDay.ONE
) {

    @Throws(QueryException::class)
    fun query(): List<Result> {
        val categories = CategoriesQuery(classDefinitionFile).query()
        val handicaps = HandicapsQuery(classDefinitionFile).query()
        val registrations = RegistrationsQuery(
                eventControlFile = eventControlFile,
                categories = categories,
                handicaps = handicaps
        ).query()
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
