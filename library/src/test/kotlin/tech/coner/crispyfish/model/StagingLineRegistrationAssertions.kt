package tech.coner.crispyfish.model

import assertk.Assert
import assertk.assertions.prop

fun Assert<StagingRegistration>.number() = prop(StagingRegistration::number)
fun Assert<StagingRegistration>.classing() = prop(StagingRegistration::classing)
fun Assert<StagingRegistration>.name() = prop(StagingRegistration::name)
fun Assert<StagingRegistration>.car() = prop(StagingRegistration::car)
fun Assert<StagingRegistration>.carColor() = prop(StagingRegistration::carColor)