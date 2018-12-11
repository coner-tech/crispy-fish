package org.coner.crispy_fish.filetype.staging

class StagingLineException : Exception {

    constructor(s: String) : super(s) {}

    constructor(s: String, throwable: Throwable) : super(s, throwable) {}
}
