package tech.coner.crispyfish.filetype.staging

import tech.coner.crispyfish.model.ClassDefinition
import tech.coner.crispyfish.model.StagingRegistration

class StagingLineRegistrationMapper {

    fun toRegistration(
        categories: List<ClassDefinition>,
        handicaps: List<ClassDefinition>,
        reader: SimpleStringStagingLineReader,
        index: Int
    ): StagingRegistration {
        TODO()
    }
}