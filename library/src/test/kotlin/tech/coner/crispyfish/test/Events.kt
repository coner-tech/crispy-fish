package tech.coner.crispyfish.test

import tech.coner.crispyfish.CrispyFishEvent
import tech.coner.crispyfish.filetype.EventControlFile
import java.nio.file.Path
import kotlin.io.path.exists
import kotlin.io.path.toPath

sealed class Events(
        val classDefinitions: ClassDefinitions,
        ecfFile: String,
) {

    val eventControlFile = EventControlFile(
        file = resource(ecfFile),
    )

    fun factory(): CrispyFishEvent = CrispyFishEvent.factory(eventControlFile)

    object Thscc2016Points1Danville : Events(
            classDefinitions = ClassDefinitions.Thscc2016,
            ecfFile = "thscc/2016/points1/2016-03-05 points autox 1 danville.ecf"
    )

    object Thscc2016Points2Cary : Events(
            classDefinitions = ClassDefinitions.Thscc2016,
            ecfFile = "thscc/2016/points2/2016-03-18 points autox 2 cary.ecf"
    )

    object Thscc2016Points3Danville : Events(
            classDefinitions = ClassDefinitions.Thscc2016,
            ecfFile = "thscc/2016/points3/2016 Points Event 3 Danville.ecf"
    )

    object Thscc2016Points9Cary : Events(
            classDefinitions = ClassDefinitions.Thscc2016,
            ecfFile = "thscc/2016/points9/2016-09-17 points autox 9 cary.ecf"
    )

    object Thscc2017Points1Danville : Events(
            classDefinitions = ClassDefinitions.Thscc2017,
            ecfFile = "thscc/2017/points1/20170311_Danville_Va_Event1.ecf"
    )

    object Thscc2017Points5CaryTowneCenter : Events(
            classDefinitions = ClassDefinitions.Thscc2017,
            ecfFile = "thscc/2017/points5/20170604 Points Autocross 5 Cary Towne Center.ecf"
    )

    object Thscc2017Points9Danville : Events(
            classDefinitions = ClassDefinitions.Thscc2017,
            ecfFile = "thscc/2017/points9/20171021 Points Autocross 9 Danville.ecf"
    )

    object Thscc2018Points1Danville : Events(
            classDefinitions = ClassDefinitions.Thscc2018,
            ecfFile = "thscc/2018/points1/2018-03-04 Points Autocross 1 Danville.ecf"
    )

    object Thscc2018JustForFunCary : Events(
            classDefinitions = ClassDefinitions.Thscc2018,
            ecfFile = "thscc/2018/justforfun/2018-08-25 Just for Fun Autocross Cary.ecf"
    )

    object Thscc2018WeddingcrossDanville : Events(
            classDefinitions = ClassDefinitions.Thscc2018,
            ecfFile = "thscc/2018/weddingcross/2018-09-30 Maylotta Weddingcross Danville.ecf"
    )

    object Thscc2018Points8Cary : Events(
            classDefinitions = ClassDefinitions.Thscc2018,
            ecfFile = "thscc/2018/points8/2018-10-21 Points Autocross 8 Rescheduled Cary.ecf"
    )

    object Thscc2019Points8Nccar : Events(
            classDefinitions = ClassDefinitions.Thscc2019,
            ecfFile = "thscc/2019/points8/2019-08-31 points 8 nccar.ecf"
    )

    object Issue42 : Events(
        classDefinitions = ClassDefinitions.Issue42,
        ecfFile = "Issue42Test/64-crispy-fish-staging-lines-invalid-signage.ecf"
    )

    private fun resource(relativeFilePath: String): Path {
        val absolutePath = "/tech/coner/crispyfish/test/Events/$relativeFilePath"
        val resourceURL = javaClass.getResource(absolutePath)
        val file = resourceURL?.toURI()?.toPath()
        check(file?.exists() == true) { "test event resource does not exist: $relativeFilePath, likely error in test arrangement or tools" }
        return file!!
    }
}