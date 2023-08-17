package tech.coner.crispyfish.internal.mapper

import tech.coner.crispyfish.model.AllClassDefinitions
import tech.coner.crispyfish.model.ClassDefinition
import tech.coner.crispyfish.model.Classing

internal class ClassingMapper(
    private val allClassDefinitions: AllClassDefinitions
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

    private val categories: List<ClassDefinition>
        get() = allClassDefinitions.categories

    private val handicaps: List<ClassDefinition>
        get() = allClassDefinitions.handicaps
}