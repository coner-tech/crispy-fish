package tech.coner.crispyfish.model

import java.math.BigDecimal

data class ClassDefinition(
        val abbreviation: String,
        val name: String,
        val groupName: String,
        val paxFactor: BigDecimal,
        val paxed: Boolean,
        val exclude: Boolean
)