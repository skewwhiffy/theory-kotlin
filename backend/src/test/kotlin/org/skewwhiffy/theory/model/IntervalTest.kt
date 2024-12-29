package org.skewwhiffy.theory.model

import org.skewwhiffy.theory.org.skewwhiffy.theory.model.Interval
import org.skewwhiffy.theory.org.skewwhiffy.theory.model.IntervalBuilder
import org.skewwhiffy.theory.org.skewwhiffy.theory.model.NonPerfectIntervalBuilder
import org.skewwhiffy.theory.org.skewwhiffy.theory.model.PerfectIntervalBuilder
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class TestCase<T>(
    val initialize: (builder: T) -> Interval,
    val expectedSize: Int
) {
    override fun toString() = "$expectedSize"
}

class IntervalTest {
    companion object {
        @JvmStatic
        private fun nonPerfectTestCases(): List<TestCase<NonPerfectIntervalBuilder>> {
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

        @JvmStatic
        private fun perfectTestCases(): List<TestCase<PerfectIntervalBuilder>> {
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

        @JvmStatic
        private fun intervalTestCases(): List<TestCase<IntervalBuilder>> {
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
    }

    @ParameterizedTest
    @MethodSource("nonPerfectTestCases")
    fun `Can instantiate major intervals`(testCase: TestCase<NonPerfectIntervalBuilder>) {
        val interval = Interval.major.let(testCase.initialize)

        assertThat(interval.size).isEqualTo(testCase.expectedSize)
        assertThat(interval.offset).isEqualTo(0)
    }

    @ParameterizedTest
    @MethodSource("nonPerfectTestCases")
    fun `Can instantiate minor intervals`(testCase: TestCase<NonPerfectIntervalBuilder>) {
        val interval = Interval.minor.let(testCase.initialize)

        assertThat(interval.size).isEqualTo(testCase.expectedSize)
        assertThat(interval.offset).isEqualTo(-1)
    }

    @ParameterizedTest
    @MethodSource("intervalTestCases")
    fun `Can instantiate augmented intervals`(testCase: TestCase<IntervalBuilder>) {
        val interval = Interval.augmented.let(testCase.initialize)

        assertThat(interval.size).isEqualTo(testCase.expectedSize)
        assertThat(interval.offset).isEqualTo(1)
    }

    @ParameterizedTest
    @MethodSource("nonPerfectTestCases")
    fun `Can instantiation non-perfect diminished intervals`(testCase: TestCase<NonPerfectIntervalBuilder>) {
        val interval = Interval.diminished.let(testCase.initialize)

        assertThat(interval.size).isEqualTo(testCase.expectedSize)
        assertThat(interval.offset).isEqualTo(-2)
    }

    @ParameterizedTest
    @MethodSource("perfectTestCases")
    fun `Can instantiation perfect diminished intervals`(testCase: TestCase<PerfectIntervalBuilder>) {
        val interval = Interval.diminished.let(testCase.initialize)

        assertThat(interval.size).isEqualTo(testCase.expectedSize)
        assertThat(interval.offset).isEqualTo(-1)
    }

    @ParameterizedTest
    @MethodSource("perfectTestCases")
    fun `Can instantiate perfect intervals`(testCase: TestCase<PerfectIntervalBuilder>) {
        val interval = Interval.perfect.let(testCase.initialize)

        assertThat(interval.size).isEqualTo(testCase.expectedSize)
        assertThat(interval.offset).isEqualTo(0)
    }

    @ParameterizedTest
    @MethodSource("intervalTestCases")
    fun `Can instantiate compound intervals`(testCase: TestCase<IntervalBuilder>) {
        val interval = Interval.compound.augmented.let(testCase.initialize)

        assertThat(interval.size).isEqualTo(testCase.expectedSize + 7)
        assertThat(interval.offset).isEqualTo(1)
    }

    @Test
    fun `Equality works`() {
        val getInterval = { Interval.compound.minor.third }

        val first = getInterval()
        val second = getInterval()

        assertThat(first).isEqualTo(second)
    }
}