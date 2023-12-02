package days

import Day

object Day1 : Day {
    override val day = 1

    override fun part1(input: List<String>) =
        input.sumOf {
            val a = it.first { it.isDigit() }
            val b = it.last { it.isDigit() }
            return@sumOf "$a$b".toInt()
        }

    val Digits = listOf(
        "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
        1, 2, 3, 4, 5, 6, 7, 8, 9
    ).withIndex().associate { (i, d) -> d.toString() to (if (d is Int) d else (i + 1)) }

    override fun part2(input: List<String>): Int {
        fun solve(line: String, reverse: Boolean = false): Char {
            val indexes = Digits
                .mapNotNull { (d, value) ->
                    (if (!reverse) line.indexOf(d) else line.lastIndexOf(d)).takeUnless { it == -1 }
                        ?.let { value to it }
                }
            val (value) = if (!reverse) indexes.minBy { (_, v) -> v } else indexes.maxBy { (_, v) -> v }
            return value.toString().first()
        }
        return input.sumOf {
            val a = solve(it)
            val b = solve(it, reverse = true)
            return@sumOf "$a$b".toInt()
        }
    }
}