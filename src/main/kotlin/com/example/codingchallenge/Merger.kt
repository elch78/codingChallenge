package com.example.codingchallenge

import java.util.*
import kotlin.math.max
import kotlin.math.min

fun merge(intervals: List<Pair<Int, Int>>): List<Pair<Int, Int>> {
    val merged: MutableList<Pair<Int, Int>> = LinkedList();
    intervals.forEach {
        val existingInterval = findOverlappingInterval(it, merged)
        val mergedInterval: Pair<Int, Int>
        if(existingInterval == null) {
            mergedInterval = it
        } else {
            mergedInterval = Pair(min(it.first, existingInterval.first), max(it.second, existingInterval.second))
            merged.remove(existingInterval)
        }
        merged.add(mergedInterval)
    }
    return merged
}

fun findOverlappingInterval(interval: Pair<Int,Int>, intervals: List<Pair<Int, Int>>) =
    intervals.find { doOverlapOrTouch(it, interval)   }

/** does pair a overlap with pair b or touch each other */
fun doOverlapOrTouch(a: Pair<Int, Int>, b: Pair<Int, Int>) =
        isIncludedOrAdjacent(a.first, b) || isIncludedOrAdjacent(a.second, b)

/** is x included in interval */
fun isIncludedOrAdjacent(x: Int, interval: Pair<Int, Int>) = interval.first - 1 <= x  && interval.second + 1 >= x;
