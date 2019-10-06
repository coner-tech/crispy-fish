package org.coner.crispyfish.query

import org.coner.crispyfish.filetype.classdefinition.ClassDefinitionFile
import org.coner.crispyfish.model.*
import org.coner.crispyfish.filetype.ecf.EventControlFile

import java.time.Duration
import java.util.HashMap

class RawTimeResultsQuery(
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
        return registrations.map {
            Result(
                    driver = it,
                    position = it.rawResult.position!!,
                    time = it.rawResult.time
            )
        }.sortedBy(Result::position)
    }

}
