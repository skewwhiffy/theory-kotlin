package org.skewwhiffy.theory

import io.kotest.core.spec.style.FunSpec
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.skewwhiffy.theory.org.skewwhiffy.theory.Interval
import org.skewwhiffy.theory.org.skewwhiffy.theory.Note

class PitchTest : StringSpec({
    "Can add major interval" {
        Note.middleC + Interval.major.second shouldBe Note.D.aboveMiddleC
    }

    "Can add minor interval" {
        Note.E.aboveMiddleC + Interval.minor.third shouldBe Note.G.aboveMiddleC
    }

    "Can add perfect interval" {
        Note.F.aboveMiddleC + Interval.perfect.fourth shouldBe Note.B.flat.aboveMiddleC
    }

    "Can add augmented interval" {
        Note.A.aboveMiddleC + Interval.augmented.fifth shouldBe Note.E.sharp.octavesAboveMiddleC(1)
    }

    "Can add diminished non-perfect interval" {
        Note.E.aboveMiddleC + Interval.diminished.sixth shouldBe Note.C.flat.octavesAboveMiddleC(1)
    }

    "Can add compound interval" {
        Note.A.flat.aboveMiddleC + Interval.compound.augmented.seventh.augmented shouldBe
                Note.G.sharp.sharp.octavesAboveMiddleC(2)
    }
})