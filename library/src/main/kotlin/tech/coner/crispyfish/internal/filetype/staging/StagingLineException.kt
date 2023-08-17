package tech.coner.crispyfish.internal.filetype.staging

class StagingLineException : Exception {

    constructor(s: String) : super(s) {}

    constructor(s: String, throwable: Throwable) : super(s, throwable) {}
}
