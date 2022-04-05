package tech.coner.crispyfish.model

import assertk.Assert
import assertk.assertions.prop

fun Assert<StagingLineRegistration>.number() = prop(StagingLineRegistration::number)
fun Assert<StagingLineRegistration>.classing() = prop(StagingLineRegistration::classing)
fun Assert<StagingLineRegistration>.name() = prop(StagingLineRegistration::name)
fun Assert<StagingLineRegistration>.car() = prop(StagingLineRegistration::car)
fun Assert<StagingLineRegistration>.carColor() = prop(StagingLineRegistration::carColor)