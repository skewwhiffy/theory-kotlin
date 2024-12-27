package org.skewwhiffy.theory

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import org.skewwhiffy.theory.org.skewwhiffy.theory.Interval
import org.skewwhiffy.theory.org.skewwhiffy.theory.NonPerfectIntervalBuilder

class CanInitializeNonPerfectIntervalTestCase(
    val initialize: (builder: NonPerfectIntervalBuilder) -> Interval,
    val expectedSize: Int
)

val nonPerfectTestCases: List<CanInitializeNonPerfectIntervalTestCase>
    get() {
        fun test(
            expectedSize: Int,
            initialize: (builder: NonPerfectIntervalBuilder) -> Interval
        ) = CanInitializeNonPerfectIntervalTestCase(initialize, expectedSize)
        return listOf(
            test(2) { it.second },
            test(3) { it.third },
            test(6) { it.sixth },
            test(7) { it.seventh },
        )
    }

class IntervalTest : FunSpec({
    context("Non perfect intervals") {
        withData(nameFn = { "Major ${it.expectedSize}" }, nonPerfectTestCases) {
            val interval = Interval.major.let(it.initialize)

            interval.size shouldBe it.expectedSize
            interval.offset shouldBe 0
        }
    }
})