package org.coner.crispyfish.model

data class Registration(
        val category: ClassDefinition?,
        val handicap: ClassDefinition,
        val number: String,
        val firstName: String,
        val lastName: String,
        val carModel: String,
        val carColor: String
)
