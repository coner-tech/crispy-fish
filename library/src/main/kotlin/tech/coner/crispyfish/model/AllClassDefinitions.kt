package tech.coner.crispyfish.model

data class AllClassDefinitions(
    val combined: List<ClassDefinition>,
    val categories: List<ClassDefinition>,
    val handicaps: List<ClassDefinition>
)
