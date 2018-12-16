package org.coner.crispyfish.filetype.registration

import assertk.all
import assertk.assert
import assertk.assertions.hasSize
import assertk.assertions.index
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import org.coner.crispyfish.model.Numbers
import org.coner.crispyfish.model.Registration
import org.junit.Test
import org.coner.crispyfish.util.TestEvent

class RegistrationFileTest {

    val registrations = mutableListOf<Registration>()
    val columnIndices = mutableMapOf<RegistrationColumns, Int>()

    @Test
    fun itShouldRead() {
        val registrationFile = TestEvent.THSCC_2016_POINTS_3.buildRegistrationFile(
                registrations,
                columnIndices
        )

        registrationFile.read()

        assert(registrations).all {
            hasSize(111)
            index(103) {
                it.isNotNull()
                it.isEqualTo(Registration(
                        numbers = Numbers("str", "8"),
                        firstName = "Carlton",
                        lastName = "Whitehead",
                        carModel = "2002 Honda S2000",
                        carColor = "Silver"
                ))
            }
        }
    }
}