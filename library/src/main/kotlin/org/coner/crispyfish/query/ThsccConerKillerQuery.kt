package org.coner.crispyfish.query

import org.coner.crispyfish.filetype.classdefinition.ClassDefinitionFile
import org.coner.crispyfish.filetype.ecf.EventControlFile
import org.coner.crispyfish.model.EventDay
import org.coner.crispyfish.model.RegistrationRun
import org.coner.crispyfish.model.Run
import org.coner.crispyfish.model.ThsccConerKillerResult

class ThsccConerKillerQuery(
        private val classDefinitionFile: ClassDefinitionFile,
        private val eventControlFile: EventControlFile,
        private val eventDay: EventDay = EventDay.ONE
) {

    fun query(): List<ThsccConerKillerResult> {
        val categories = CategoriesQuery(classDefinitionFile).query()
        val handicaps = HandicapsQuery(classDefinitionFile).query()
        val registrations = RegistrationsQuery(
                eventControlFile = eventControlFile,
                categories = categories,
                handicaps = handicaps
        ).query()
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
                .sortedByDescending { (registration, conedRuns) ->
                    (conedRuns.first().penalty as RegistrationRun.Penalty.Cone).count
                }
        // TODO: comparator for choosing coniest registrations
    }
}