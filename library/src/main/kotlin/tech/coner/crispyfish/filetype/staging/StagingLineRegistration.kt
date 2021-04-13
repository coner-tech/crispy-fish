package tech.coner.crispyfish.filetype.staging

data class StagingLineRegistration(
        val number: String? = null,
        val classing: String? = null,
        val name: String? = null,
        val car: String? = null,
        val carColor: String? = null
) {
    val driverIdentityKey by lazy { "$classing:$number" }
}