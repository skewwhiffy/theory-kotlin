package org.skewwhiffy.theory.org.skewwhiffy.theory.model

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
            5 -> this + Interval.perfect.fourth + Interval.major.second
            6 -> this + Interval.perfect.fifth + Interval.major.second
            7 -> this + Interval.major.sixth + Interval.major.second
            else -> return this.upOctave + Interval(interval.size - 7, interval.offset)
        }
        val offset = interval.offset
        return if (offset <= 0) (1..-offset).fold(defaultResult) { acc, _ -> acc.flat }
        else (1..offset).fold(defaultResult) { acc, _ -> acc.sharp }
    }

    val upOctave
        get() = Pitch(note, octave.up)

    private val flat
        get() = Pitch(Note(note.noteName, note.accidental.flat), octave)

    private val sharp
        get() = Pitch(Note(note.noteName, note.accidental.sharp), octave)

    override fun toString() = "$note$octave"
}

data class Octave(private val octave: Int) {
    companion object {
        val default = Octave(0)
    }

    val up
        get() = Octave(octave + 1)

    override fun toString() = "Oct($octave)"
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

    fun octavesAboveMiddleC(octaves: Int) = Pitch(this, Octave(octaves))

    val sharp
        get() = Note(noteName, accidental.sharp)
    val flat
        get() = Note(noteName, accidental.flat)

    override fun toString() = "$noteName$accidental"
}

data class Accidental(private val offset: Int) {
    val sharp
        get() = Accidental(offset + 1)
    val flat
        get() = Accidental(offset - 1)

    override fun toString() = when {
        offset > 0 -> "x".repeat(offset / 2) + "#".repeat(offset % 2)
        offset < 0 -> "b".repeat(-offset)
        else -> ""
    }
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