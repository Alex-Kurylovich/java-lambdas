package com.insightfullogic.java8.book.chapter8;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collection;
import java.util.List;

import static com.insightfullogic.java8.book.chapter8.DependencyInversionPrinciple.HeadingFinder;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class DependencyInversionPrincipleTest {


    private final HeadingFinder finder;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{{new DependencyInversionPrinciple.NoDIP()}, {new DependencyInversionPrinciple.ExtractedDIP()}};
        return asList(data);
    }

    public DependencyInversionPrincipleTest(HeadingFinder finder) {
        this.finder = finder;
    }

    @Test
    public void correctHeadings() throws IOException {
        URL path = getClass().getResource("/test_file");
        Assert.assertNotNull(path);
        InputStreamReader reader = new InputStreamReader(path.openStream());
        List<String> headings = finder.findHeadings(reader);
        assertEquals(asList("Improve Content", "Cleanup", "Add Content", "Add to Streams Chapter"), headings);
    }

}
