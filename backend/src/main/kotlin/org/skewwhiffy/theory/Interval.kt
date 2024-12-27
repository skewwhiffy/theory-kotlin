package org.skewwhiffy.theory.org.skewwhiffy.theory

class Interval(internal val size: Int, internal val offset: Int) {
    constructor(size: Int) : this(size, 0)

    companion object : TopLevelIntervalBuilder {
        override val compound: TopLevelIntervalBuilder
            get() = Interval.withPostProcess { Interval(it.size + 7, it.offset) }
        override val major: NonPerfectIntervalBuilder
            get() = MajorIntervalBuilder()
        override val minor
            get() = major.withPostProcess { Interval(it.size, it.offset - 1) }
        override val perfect: PerfectIntervalBuilder
            get() = BasePerfectIntervalBuilder()
        override val augmented
            get() = combine(perfect, major).withPostProcess { Interval(it.size, it.offset + 1) }
        override val diminished
            get() = combine(perfect, minor).withPostProcess { Interval(it.size, it.offset - 1) }
    }
}

interface TopLevelIntervalBuilder {
    val compound: TopLevelIntervalBuilder
    val major: NonPerfectIntervalBuilder
    val minor: NonPerfectIntervalBuilder
    val perfect: PerfectIntervalBuilder
    val augmented: IntervalBuilder
    val diminished: IntervalBuilder
}

interface PerfectIntervalBuilder {
    val unison: Interval
    val fourth: Interval
    val fifth: Interval
    val octave: Interval
}

interface NonPerfectIntervalBuilder {
    val second: Interval
    val third: Interval
    val sixth: Interval
    val seventh: Interval
}

interface IntervalBuilder : PerfectIntervalBuilder, NonPerfectIntervalBuilder

private class BasePerfectIntervalBuilder : PerfectIntervalBuilder {
    override val unison: Interval
        get() = Interval(1)
    override val fourth: Interval
        get() = Interval(4)
    override val fifth: Interval
        get() = Interval(5)
    override val octave: Interval
        get() = Interval(8)
}

private class PerfectIntervalBuilderWrapper(
    private val source: PerfectIntervalBuilder,
    private val postProcess: (source: Interval) -> Interval
) : PerfectIntervalBuilder {
    override val unison: Interval
        get() = source.unison.let(postProcess)
    override val fourth: Interval
        get() = source.fourth.let(postProcess)
    override val fifth: Interval
        get() = source.fifth.let(postProcess)
    override val octave: Interval
        get() = source.octave.let(postProcess)
}

private class NonPerfectIntervalBuilderWrapper(
    private val source: NonPerfectIntervalBuilder,
    private val postProcess: (source: Interval) -> Interval
) : NonPerfectIntervalBuilder {
    override val second
        get() = source.second.let(postProcess)
    override val third
        get() = source.third.let(postProcess)
    override val sixth
        get() = source.sixth.let(postProcess)
    override val seventh
        get() = source.seventh.let(postProcess)
}

private class IntervalBuilderWrapper(
    private val source: IntervalBuilder,
    private val postProcess: (source: Interval) -> Interval
) : IntervalBuilder {
    override val unison: Interval
        get() = source.unison.let(postProcess)
    override val second
        get() = source.second.let(postProcess)
    override val third
        get() = source.third.let(postProcess)
    override val fourth: Interval
        get() = source.fourth.let(postProcess)
    override val fifth: Interval
        get() = source.fifth.let(postProcess)
    override val sixth
        get() = source.sixth.let(postProcess)
    override val seventh
        get() = source.seventh.let(postProcess)
    override val octave: Interval
        get() = source.octave.let(postProcess)
}

private class TopLevelIntervalBuilderWrapper(
    private val source: TopLevelIntervalBuilder,
    private val postProcess: (source: Interval) -> Interval
) : TopLevelIntervalBuilder {
    override val compound: TopLevelIntervalBuilder
        get() = source.compound.withPostProcess(postProcess)
    override val major: NonPerfectIntervalBuilder
        get() = source.major.withPostProcess(postProcess)
    override val minor: NonPerfectIntervalBuilder
        get() = source.minor.withPostProcess(postProcess)
    override val perfect: PerfectIntervalBuilder
        get() = source.perfect.withPostProcess(postProcess)
    override val augmented: IntervalBuilder
        get() = source.augmented.withPostProcess(postProcess)
    override val diminished: IntervalBuilder
        get() = source.diminished.withPostProcess(postProcess)
}

private fun NonPerfectIntervalBuilder.withPostProcess(
    postProcess: (source: Interval) -> Interval
): NonPerfectIntervalBuilder = NonPerfectIntervalBuilderWrapper(this, postProcess)

private fun PerfectIntervalBuilder.withPostProcess(
    postProcess: (source: Interval) -> Interval
): PerfectIntervalBuilder = PerfectIntervalBuilderWrapper(this, postProcess)

private fun IntervalBuilder.withPostProcess(
    postProcess: (source: Interval) -> Interval
): IntervalBuilder = IntervalBuilderWrapper(this, postProcess)

private fun TopLevelIntervalBuilder.withPostProcess(
    postProcess: (source: Interval) -> Interval
): TopLevelIntervalBuilder = TopLevelIntervalBuilderWrapper(this, postProcess)

private class MajorIntervalBuilder : NonPerfectIntervalBuilder {
    override val second
        get() = Interval(2)
    override val third
        get() = Interval(3)
    override val sixth
        get() = Interval(6)
    override val seventh
        get() = Interval(7)
}

private fun combine(
    perfect: PerfectIntervalBuilder,
    nonPerfect: NonPerfectIntervalBuilder,
): IntervalBuilder =
    object : IntervalBuilder, PerfectIntervalBuilder by perfect, NonPerfectIntervalBuilder by nonPerfect {}