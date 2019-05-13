package com.tomekl007;


import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class TakeWhileTest {


    @Test
    public void givenStream_whenTakeWhile_shouldTakeElementsAsLongAsPredicateIsMet() {
        //given
        Stream<Integer> integerStream = Stream.of(1, 5, 10);

        //when
        List<Integer> collect = integerStream
                .sorted(Comparator.reverseOrder())
                .takeWhile(v -> v > 1)//beware of ordering stream!! takeWhile only gives deterministic results when stream is ordered
                .collect(Collectors.toList());

        //then
        assertThat(collect).hasSameElementsAs(List.of(10, 5));
    }

    @Test
    public void givenStream_whenDropWhile_shouldDropElementsAsLongAsPredicateIsMet() {
        //given
        Stream<Integer> integerStream = Stream.of(5, 4, 3, 2, 1);
        
        //when
        List<Integer> collect = integerStream
                .sorted()
                .dropWhile(v -> v < 4)//beware of ordering stream!!
                .collect(Collectors.toList());

        //then
        assertThat(collect).hasSameElementsAs(List.of(4, 5));
    }
}
