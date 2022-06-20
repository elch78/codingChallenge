package com.example.codingchallenge

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class MergerTest {
    @ParameterizedTest
    @MethodSource
    fun test(intervals: List<Pair<Int, Int>>, expectedResult: List<Pair<Int, Int>>) {
        // Arrange

        // Act
        assertThat(merge(intervals)).isEqualTo(expectedResult)

        // Assert
    }

    companion object {
        @JvmStatic
        fun test(): Stream<Arguments> = Stream.of(
                Arguments.of( listOf(Pair(1,2)), listOf(Pair(1,2))),
                Arguments.of( listOf(Pair(1,2),Pair(4,5)), listOf(Pair(1,2),Pair(4,5))),
                Arguments.of( listOf(Pair(1,2),Pair(3,4)), listOf(Pair(1,4))),
                Arguments.of( listOf(Pair(1,2),Pair(5,6),Pair(3,4)), listOf(Pair(1,6))),
                Arguments.of( listOf(Pair(1,2),Pair(8,9),Pair(4,5)), listOf(Pair(1,2), Pair(4,5), Pair(8,9))),
                Arguments.of( listOf(Pair(8,9),Pair(4,5),Pair(1,2)), listOf(Pair(1,2), Pair(4,5), Pair(8,9))),
                Arguments.of( listOf(Pair(8,9),Pair(3,4),Pair(1,2)), listOf(Pair(1,4), Pair(8,9))),

        )
    }
}
