package com.tomekl007;


import org.junit.Test;

import java.util.*;

public class FavorCollectionsImmutabilityTest {

    @Test(expected = UnsupportedOperationException.class)
    public void shouldCreateImmutableSetOldWay() {
        //given
        Set<String> set = new HashSet<>();
        set.add("a");
        set.add("b");
        set.add("c");

        //when
        Set<String> strings = Collections.unmodifiableSet(set);
        strings.add("a");

    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldCreateImmutableSetNewWay() {
        //given
        Set<String> set = Set.of("a", "b", "c");

        //when
        set.add("a");
    }


    @Test(expected = UnsupportedOperationException.class)
    public void shouldCreateImmutableMapNewWay() {
        //given
        Map<Integer, String> map = Map.of(1, "a", 2, "b");

        //when
        map.put(3, "a");
    }


    @Test(expected = UnsupportedOperationException.class)
    public void shouldCreateImmutableListNewWay() {
        //given
        List<String> list = List.of("a", "b", "c");

        //when
        list.add("a");
    }

}