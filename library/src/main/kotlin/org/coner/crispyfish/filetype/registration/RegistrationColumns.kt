package org.coner.crispyfish.filetype.registration

sealed class RegistrationColumns(val heading: String, var index: Int? = null) {
    class Class : RegistrationColumns("Class")
    class Number : RegistrationColumns("Number")
    class FirstName : RegistrationColumns("First Name")
    class LastName : RegistrationColumns("Last Name")
    class CarModel : RegistrationColumns("Car Model")
    class CarColor : RegistrationColumns("Car Color")
}