package com.example.codingchallenge

import kotlin.math.max
import kotlin.math.min

/**
 * merges intervals
 * @param intervals the intervals to merge
 * @return merged intervals
 */
fun merge(intervals: List<Pair<Int, Int>>): List<Pair<Int, Int>> {
    var merged: IntervalNode? = null
    intervals.forEach intervals@ {
        if(merged == null) {
            // no nodes yet. The very first interval just becomes the first node
            merged = IntervalNode(interval = it, next = null, previous = null)
        } else {
            var node: IntervalNode? = merged
            var lastNode: IntervalNode?
            // traverse through the nodes
            do {
                // if an interval of a node intersects or is adjacent merge the intervals.
                if(node!!.intersectOrAdjacent(it)) {
                    node.merge(it)
                    return@intervals
                }
                lastNode = node
                node = node.next
            } while(node != null && node.interval.first < it.second)
            // insert a new node if no node intersected
            // this makes the list ordered
            // handle special case that lastNode is the first node and the new interval needs
            // to become the first node
            if(it.first > lastNode!!.interval.second) {
                lastNode.insertAfter(it)
            } else {
                lastNode.insertBefore(it)
                merged = lastNode.previous
            }

        }
    }
    return merged?.intervals() ?: emptyList()
}

/**
 * A hand crafted linked List since we want to manipulate the pointers to remove merged nodes from the list
 * and I from the back of my head don't know of a way to do it with the standard linked list ...
 */
class IntervalNode(var interval: Pair<Int, Int>, var next: IntervalNode?, var previous: IntervalNode?) {
    fun intersectOrAdjacent(other: Pair<Int, Int>) = intervalsIntersectOrAdjacent(this.interval, other)

    /**
     * merge the interval of this node with the given interval.
     */
    fun merge(other: Pair<Int, Int>) {
        // merge the interval
        interval = mergeIntervals(this.interval, other)
        // an expansion of the interval can cause the merge of consecutive intervals
        var node = next
        while (node != null
                && node.intersectOrAdjacent(this.interval)) {
            interval = mergeIntervals(this.interval, node.interval)
            next = node.next // remove the merged node from the list
            node = node.next
        }
    }

    fun insertBefore(interval: Pair<Int, Int>) {
        val node = IntervalNode(interval = interval, next = this, previous = this.previous)
        previous = node
        previous?.next = node
    }

    fun insertAfter(interval: Pair<Int, Int>) {
        val node = IntervalNode(interval = interval, next = this.next, previous = this)
        next = node
        next?.previous = node
    }

    /**
     * Constructs the result as a list of pairs
     */
    fun intervals(): List<Pair<Int, Int>> {
        val intervals = ArrayList<Pair<Int,Int>>()
        var node: IntervalNode? = this
        while(node != null) {
            intervals.add(node.interval)
            node = node.next
        }
        return intervals
    }
}

fun mergeIntervals(a: Pair<Int, Int>, b: Pair<Int, Int>) =
        Pair(min(a.first, b.first), max(a.second, b.second))

/** does pair a overlap with pair b or touch each other */
fun intervalsIntersectOrAdjacent(a: Pair<Int, Int>, b: Pair<Int, Int>) =
        isIncludedOrAdjacent(a.first, b) || isIncludedOrAdjacent(a.second, b)

/** is x included in interval */
fun isIncludedOrAdjacent(x: Int, interval: Pair<Int, Int>) = interval.first - 1 <= x  && interval.second + 1 >= x
