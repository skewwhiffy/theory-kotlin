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
}