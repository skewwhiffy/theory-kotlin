package org.skewwhiffy.theory

import org.junit.jupiter.params.provider.MethodSource
import org.skewwhiffy.theory.org.skewwhiffy.theory.Interval
import org.skewwhiffy.theory.org.skewwhiffy.theory.NonPerfectIntervalBuilder
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.skewwhiffy.theory.org.skewwhiffy.theory.IntervalBuilder
import org.skewwhiffy.theory.org.skewwhiffy.theory.PerfectIntervalBuilder

class IntervalTest {
    companion object {
        @JvmStatic
        fun canInitializeNonPerfectInterval(): List<CanInitializeNonPerfectIntervalTestCase> {
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

        @JvmStatic
        fun canInitializePerfectInterval(): List<CanInitializePerfectIntervalTestCase> {
            fun test(
                expectedSize: Int,
                initialize: (builder: PerfectIntervalBuilder) -> Interval
            ) = CanInitializePerfectIntervalTestCase(initialize, expectedSize)
            return listOf(
                test(1) { it.unison },
                test(4) { it.fourth },
                test(5) { it.fifth },
                test(8) { it.octave },
            )
        }

        @JvmStatic
        fun canInitializeInterval(): List<CanInitializeIntervalTestCase> {
            fun test(
                expectedSize: Int,
                initialize: (builder: IntervalBuilder) -> Interval
            ) = CanInitializeIntervalTestCase(initialize, expectedSize)
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
    }

    @ParameterizedTest
    @MethodSource("canInitializeNonPerfectInterval")
    fun canInitializeMajorInterval(testCase: CanInitializeNonPerfectIntervalTestCase) {
        val interval = Interval.major.let(testCase.initialize)

        assertThat(interval.size).isEqualTo(testCase.expectedSize)
        assertThat(interval.offset).isEqualTo(0)
    }

    @ParameterizedTest
    @MethodSource("canInitializeNonPerfectInterval")
    fun canInitializeMinorInterval(testCase: CanInitializeNonPerfectIntervalTestCase) {
        val interval = Interval.minor.let(testCase.initialize)

        assertThat(interval.size).isEqualTo(testCase.expectedSize)
        assertThat(interval.offset).isEqualTo(-1)
    }

    @ParameterizedTest
    @MethodSource
    fun canInitializePerfectInterval(testCase: CanInitializePerfectIntervalTestCase) {
        val interval = Interval.perfect.let(testCase.initialize)

        assertThat(interval.size).isEqualTo(testCase.expectedSize)
        assertThat(interval.offset).isEqualTo(0)
    }

    @ParameterizedTest
    @MethodSource("canInitializePerfectInterval")
    fun canInitializeDiminishedPerfectInterval(testCase: CanInitializePerfectIntervalTestCase) {
        val interval = Interval.diminished.let(testCase.initialize)

        assertThat(interval.size).isEqualTo(testCase.expectedSize)
        assertThat(interval.offset).isEqualTo(-1)
    }

    @ParameterizedTest
    @MethodSource("canInitializeNonPerfectInterval")
    fun canInitializeDiminishedNonPerfectInterval(testCase: CanInitializeNonPerfectIntervalTestCase) {
        val interval = Interval.diminished.let(testCase.initialize)

        assertThat(interval.size).isEqualTo(testCase.expectedSize)
        assertThat(interval.offset).isEqualTo(-2)
    }

    @ParameterizedTest
    @MethodSource("canInitializeInterval")
    fun canInitializeAugmentedInterval(testCase: CanInitializeIntervalTestCase) {
        val interval = Interval.augmented.let(testCase.initialize)

        assertThat(interval.size).isEqualTo(testCase.expectedSize)
        assertThat(interval.offset).isEqualTo(1)
    }

    @ParameterizedTest
    @MethodSource("canInitializeInterval")
    fun canInitializeCompoundInterval(testCase: CanInitializeIntervalTestCase) {
        val interval = Interval.compound.augmented.let(testCase.initialize)

        assertThat(interval.size).isEqualTo(testCase.expectedSize + 7)
        assertThat(interval.offset).isEqualTo(1)
    }
}

class CanInitializeNonPerfectIntervalTestCase(
    val initialize: (builder: NonPerfectIntervalBuilder) -> Interval,
    val expectedSize: Int
)

class CanInitializePerfectIntervalTestCase(
    val initialize: (builder: PerfectIntervalBuilder) -> Interval,
    val expectedSize: Int
)

class CanInitializeIntervalTestCase(
    val initialize: (builder: IntervalBuilder) -> Interval,
    val expectedSize: Int
)