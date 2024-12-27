package org.skewwhiffy.theory.org.skewwhiffy.theory

data class Pitch(private val note: Note, private val octave: Octave) {
    private fun addMajorSecond(): Pitch {
        val newNote = when (note.noteName) {
            NoteName.C -> Note(NoteName.D, note.accidental)
            NoteName.D -> Note(NoteName.E, note.accidental)
            NoteName.E -> Note(NoteName.F, note.accidental.sharp)
            NoteName.F -> Note(NoteName.G, note.accidental)
            NoteName.G -> Note(NoteName.A, note.accidental)
            NoteName.A -> Note(NoteName.B, note.accidental)
            NoteName.B -> Note(NoteName.C, note.accidental.sharp)
        }
        val newOctave = if (note.noteName == NoteName.B) octave.up else octave
        return Pitch(newNote, newOctave)
    }

    operator fun plus(interval: Interval): Pitch {
        val defaultResult = when (interval.size) {
            2 -> addMajorSecond()
            3 -> this + Interval.major.second + Interval.major.second
            4 -> this + Interval.major.third + Interval.minor.second
            else -> TODO()
        }
        return when (interval.offset) {
            0 -> defaultResult
            -1 -> Pitch(defaultResult.note, defaultResult.octave).flat
            else -> TODO()
        }
    }

    val upOctave
        get() = Pitch(note, octave.up)

    private val flat
        get() = Note(note.noteName, note.accidental.flat).let { Pitch(it, octave) }
}

data class Octave(private val octave: Int) {
    companion object {
        val default = Octave(0)
    }

    val up
        get() = Octave(octave + 1)
}

data class Note(val noteName: NoteName, val accidental: Accidental) {
    private constructor(noteName: NoteName) : this(noteName, Accidental(0))

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