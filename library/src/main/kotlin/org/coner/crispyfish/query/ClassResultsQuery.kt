package org.coner.crispyfish.query

import org.coner.crispyfish.filetype.classdefinition.ClassDefinitionFile
import org.coner.crispyfish.filetype.ecf.EventControlFile
import org.coner.crispyfish.model.ClassDefinition
import org.coner.crispyfish.model.EventDay
import org.coner.crispyfish.model.Registration
import org.coner.crispyfish.model.Result

class ClassResultsQuery(
        private val classDefinitionFile: ClassDefinitionFile,
        private val eventControlFile: EventControlFile,
        private val eventDay: EventDay = EventDay.ONE
) {

    fun query(): Map<ClassDefinition?, List<Result>> {
        val categories = CategoriesQuery(classDefinitionFile).query()
        val handicaps = HandicapsQuery(classDefinitionFile).query()
        val registrations = RegistrationsQuery(
                eventControlFile = eventControlFile,
                categories = categories,
                handicaps = handicaps
        ).query()
        val registrationsByResultGrouping = registrations
                .sortedBy { it.classResult?.time ?: Int.MAX_VALUE.toString() }
                .groupBy { it.classDefinitionForClassResults }
        return registrations
            .mapNotNull { registration ->
                registration.classResult?.let { classResult ->
                    Result(
                        driver = registration,
                        position = registrationsByResultGrouping.getValue(registration.classDefinitionForClassResults)
                            .indexOf(registration) + 1,
                        time = classResult.time
                    )
                }
            }
            .sortedBy { it.position }
            .groupBy { it.driver.classDefinitionForClassResults }
    }

    private val Registration.classDefinitionForClassResults: ClassDefinition?
        get() = if (category?.paxed == true) {
            category
        } else {
            handicap
        }
}