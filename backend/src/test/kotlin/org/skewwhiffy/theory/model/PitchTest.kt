package org.skewwhiffy.theory.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.skewwhiffy.theory.org.skewwhiffy.theory.model.Interval
import org.skewwhiffy.theory.org.skewwhiffy.theory.model.Note

class PitchTest {
    @Test
    fun `Can add major interval`() {
        assertThat(Note.middleC + Interval.major.second).isEqualTo(Note.D.aboveMiddleC)
    }

    @Test
    fun `Can add minor interval`() {
        assertThat(Note.E.aboveMiddleC + Interval.minor.third).isEqualTo(Note.G.aboveMiddleC)
    }

    @Test
    fun `Can add perfect interval`() {
        assertThat(Note.F.aboveMiddleC + Interval.perfect.fourth).isEqualTo(Note.B.flat.aboveMiddleC)
    }

    @Test
    fun `Can add augmented interval`() {
        assertThat(Note.A.aboveMiddleC + Interval.augmented.fifth).isEqualTo(Note.E.sharp.octavesAboveMiddleC(1))
    }

    @Test
    fun `Can add diminished non-perfect interval`() {
        assertThat(Note.E.aboveMiddleC + Interval.diminished.sixth).isEqualTo(Note.C.flat.octavesAboveMiddleC(1))
    }

    @Test
    fun `Can add compound interval`() {
        assertThat(Note.A.flat.aboveMiddleC + Interval.compound.augmented.seventh.augmented)
            .isEqualTo(Note.G.sharp.sharp.octavesAboveMiddleC(2))
    }
}