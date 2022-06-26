package tech.coner.crispyfish.mapper

import tech.coner.crispyfish.model.ClassDefinition
import tech.coner.crispyfish.model.Classing

internal class ClassingMapper(
    private val categories: List<ClassDefinition>,
    private val handicaps: List<ClassDefinition>
) {

    fun toClassing(
        compositeClassField: String?
    ): Classing {
        var handicapAbbreviation = handicaps.firstOrNull { it.abbreviation == compositeClassField }?.abbreviation
        val category = if (compositeClassField != null && handicapAbbreviation == null) {
            categories
                .firstOrNull { compositeClassField.startsWith(it.abbreviation) }
                ?.also { handicapAbbreviation = compositeClassField.replaceFirst(it.abbreviation, "") }
        } else {
            null
        }

        val handicap = handicaps.firstOrNull { it.abbreviation == handicapAbbreviation }

        return Classing(
            category = category,
            handicap = handicap
        )
    }
}