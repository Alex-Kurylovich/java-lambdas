package com.insightfullogic.java8.book.chapter4.statics;

import com.insightfullogic.java8.book.domain.Album;
import com.insightfullogic.java8.book.domain.Track;

import java.util.IntSummaryStatistics;

public class Primitives {

    public static void printTrackLengthStatistics(Album album) {
        IntSummaryStatistics trackLengthStats
                = album.getTracks()
                .mapToInt(Track::getLength)
                .summaryStatistics();

        System.out.printf("Max: %d, Min: %d, Ave: %f, Sum: %d",
                trackLengthStats.getMax(),
                trackLengthStats.getMin(),
                trackLengthStats.getAverage(),
                trackLengthStats.getSum());
    }

}
