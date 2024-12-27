package org.skewwhiffy.theory

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.skewwhiffy.theory.org.skewwhiffy.theory.Interval
import org.skewwhiffy.theory.org.skewwhiffy.theory.Note

class PitchTest {
    @Test
    fun canAddMajorInterval() {
        val expected = Note.D.aboveMiddleC

        val actual = Note.middleC + Interval.major.second

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun canAddMinorInterval() {
        val expected = Note.G.aboveMiddleC

        val actual = Note.E.aboveMiddleC + Interval.minor.third

        assertThat(actual).isEqualTo(expected)
    }
}