package org.skewwhiffy.theory

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import org.skewwhiffy.theory.org.skewwhiffy.theory.Interval
import org.skewwhiffy.theory.org.skewwhiffy.theory.IntervalBuilder
import org.skewwhiffy.theory.org.skewwhiffy.theory.NonPerfectIntervalBuilder
import org.skewwhiffy.theory.org.skewwhiffy.theory.PerfectIntervalBuilder

private class TestCase<T>(
    val initialize: (builder: T) -> Interval,
    val expectedSize: Int
)

private val nonPerfectTestCases: List<TestCase<NonPerfectIntervalBuilder>>
    get() {
        fun test(
            expectedSize: Int,
            initialize: (builder: NonPerfectIntervalBuilder) -> Interval
        ) = TestCase(initialize, expectedSize)
        return listOf(
            test(2) { it.second },
            test(3) { it.third },
            test(6) { it.sixth },
            test(7) { it.seventh },
        )
    }


private val perfectTestCases: List<TestCase<PerfectIntervalBuilder>>
    get() {
        fun test(
            expectedSize: Int,
            initialize: (builder: PerfectIntervalBuilder) -> Interval
        ) = TestCase(initialize, expectedSize)
        return listOf(
            test(1) { it.unison },
            test(4) { it.fourth },
            test(5) { it.fifth },
            test(8) { it.octave },
        )
    }

private val intervalTestCases: List<TestCase<IntervalBuilder>>
    get() {
        fun test(
            expectedSize: Int,
            initialize: (builder: IntervalBuilder) -> Interval
        ) = TestCase(initialize, expectedSize)
        return listOf(
            test(1) { it.unison },
            test(2) { it.second },
            test(3) { it.third },
            test(4) { it.fourth },
            test(5) { it.fifth },
            test(6) { it.sixth },
            test(7) { it.seventh },
            test(8) { it.octave },
        )
    }

class IntervalTest : FunSpec({
    context("Major") {
        withData(nameFn = { "$it.expectedSize" }, nonPerfectTestCases) {
            val interval = Interval.major.let(it.initialize)

            interval.size shouldBe it.expectedSize
            interval.offset shouldBe 0
        }
    }

    context("Minor") {
        withData(nameFn = { "$it.expectedSize" }, nonPerfectTestCases) {
            val interval = Interval.minor.let(it.initialize)

            interval.size shouldBe it.expectedSize
            interval.offset shouldBe -1
        }
    }

    context("Augmented") {
        withData(nameFn = { "$it.expectedSize" }, intervalTestCases) {
            val interval = Interval.augmented.let(it.initialize)

            interval.size shouldBe it.expectedSize
            interval.offset shouldBe 1
        }
    }

    context("Diminished") {
        withData(nameFn = { "$it.expectedSize" }, nonPerfectTestCases) {
            val interval = Interval.diminished.let(it.initialize)

            interval.size shouldBe it.expectedSize
            interval.offset shouldBe -2
        }

        withData(nameFn = { "$it.expectedSize" }, perfectTestCases) {
            val interval = Interval.diminished.let(it.initialize)

            interval.size shouldBe it.expectedSize
            interval.offset shouldBe -1
        }
    }

    context("Perfect") {
        withData(nameFn = { "$it.expectedSize" }, perfectTestCases) {
            val interval = Interval.perfect.let(it.initialize)

            interval.size shouldBe it.expectedSize
            interval.offset shouldBe 0
        }
    }

    context("Compound") {
        withData(nameFn = { "Augmented ${it.expectedSize}" }, intervalTestCases) {
            val interval = Interval.compound.augmented.let(it.initialize)

            interval.size shouldBe it.expectedSize + 7
            interval.offset shouldBe 1
        }
    }

    test("Equality") {
        val getInterval = { Interval.compound.minor.third }

        val first = getInterval()
        val second = getInterval()

        first shouldBe second
    }
})