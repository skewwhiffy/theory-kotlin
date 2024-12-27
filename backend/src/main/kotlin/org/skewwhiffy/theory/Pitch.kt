package org.skewwhiffy.theory.org.skewwhiffy.theory

data class Pitch(private val note: Note, private val octave: Octave) {
    operator fun plus(interval: Interval): Pitch {
        TODO()
    }
}

data class Octave(private val octave: Int) {
    companion object {
        val default = Octave(0)
    }
}

data class Note(private val noteName: NoteName, private val accidental: Accidental) {
    constructor(noteName: NoteName) : this(noteName, Accidental(0))

    companion object {
        val C = Note(NoteName.C)
        val D = Note(NoteName.D)
        val E = Note(NoteName.E)
        val F = Note(NoteName.F)
        val G = Note(NoteName.G)
        val A = Note(NoteName.A)
        val B = Note(NoteName.B)
        val middleC = C.aboveMiddleC
    }

    val aboveMiddleC
        get() = Pitch(this, Octave.default)

    val sharp
        get() = Note(noteName, accidental.sharp)
    val flat
        get() = Note(noteName, accidental.flat)
}

data class Accidental(private val offset: Int) {
    val sharp
        get() = Accidental(offset + 1)
    val flat
        get() = Accidental(offset - 1)
}

enum class NoteName {
    C,
    D,
    E,
    F,
    G,
    A,
    B
}