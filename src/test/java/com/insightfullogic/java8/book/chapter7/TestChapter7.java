package com.insightfullogic.java8.book.chapter7;

import com.insightfullogic.java8.book.chapter7.statics.Testing;
import com.insightfullogic.java8.book.domain.Album;
import com.insightfullogic.java8.book.domain.OrderDomain;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;

public class TestChapter7 {

    // Examples, OrderDomainExampleTest

    // BEGIN can_count_albums
    @Test
    public void canCountFeatures() {
        OrderDomain order = new OrderDomain(asList(
                newAlbum("Exile on Main St."),
                newAlbum("Beggars Banquet"),
                newAlbum("Aftermath"),
                newAlbum("Let it Bleed")));

        assertEquals(8, order.countFeature(album -> 2));
    }
    // END can_count_albums

    private Album newAlbum(String name) {
        return new Album(name, emptyList(), emptyList());
    }

    // Examples, TestingTest

    // BEGIN to_uppercase
    @Test
    public void multipleWordsToUppercase() {
        List<String> input = Arrays.asList("a", "b", "hello");
        List<String> result = Testing.allToUpperCase(input);
        assertEquals(asList("A", "B", "HELLO"), result);
    }
    // END to_uppercase

    // BEGIN twoLetterStringConvertedToUppercaseLambdas
    @Test
    public void twoLetterStringConvertedToUppercaseLambdas() {
        List<String> input = Collections.singletonList("ab");
        List<String> result = Testing.elementFirstToUpperCaseLambdas(input);
        assertEquals(Collections.singletonList("Ab"), result);
    }
}
