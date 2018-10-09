package pro.thaipad;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class CardDealTest {

    final static private String PNG_DIRECTORY = "D:/tmp/java_test_task/imgs/";
    static private Map<String, Card[]> mapImages = new HashMap<>();

    static {
        mapImages.put("20180821_055341.782_0x26080126.png",
                new Card[]{ new Card(Suits.s, "6"), new Card(Suits.h, "5"), new Card(Suits.h, "10")});
        mapImages.put("20180821_060003.065_0x173E0290.png",
                new Card[]{ new Card(Suits.s, "5"), new Card(Suits.d, "8"), new Card(Suits.h, "9")});
        mapImages.put("20180821_062523.457_0x21840198.png",
                new Card[]{ new Card(Suits.d, "A"), new Card(Suits.h, "5"), new Card(Suits.d, "8"), new Card(Suits.s, "8")});
        mapImages.put("20180821_064009.827_0x1934025E.png",
                new Card[]{ new Card(Suits.s, "J"), new Card(Suits.h, "A"), new Card(Suits.s, "10"), new Card(Suits.s, "Q"), new Card(Suits.c, "K")});
        mapImages.put("20180821_072633.514_0x356801A2.png",
                new Card[]{ new Card(Suits.d, "A"), new Card(Suits.d, "5"), new Card(Suits.s, "5")});
        mapImages.put("20180821_080219.418_0x13910258.png",
                new Card[]{ new Card(Suits.h, "Q"), new Card(Suits.c, "7"), new Card(Suits.s, "6")});
        mapImages.put("20180821_084134.865_0x240C023E.png",
                new Card[]{ new Card(Suits.h, "K"), new Card(Suits.d, "3"), new Card(Suits.d, "2"), new Card(Suits.c, "10")});
        mapImages.put("20180821_091126.795_0x0EDA02B0.png",
                new Card[]{ new Card(Suits.h, "7"), new Card(Suits.s, "Q"), new Card(Suits.s, "5")});
        mapImages.put("20180821_091220.884_0x0EDA02B0.png",
                new Card[]{ new Card(Suits.s, "Q"), new Card(Suits.c, "7"), new Card(Suits.h, "A")});
        mapImages.put("20180821_091328.300_0x0EDA02B0.png",
                new Card[]{ new Card(Suits.h, "9"), new Card(Suits.c, "9"), new Card(Suits.s, "A")});
        mapImages.put("20180821_091532.020_0x0EDA02B0.png",
                new Card[]{ new Card(Suits.h, "Q"), new Card(Suits.c, "8"), new Card(Suits.c, "K")});
        mapImages.put("20180821_092407.903_0x0EDA02B0.png",
                new Card[]{ new Card(Suits.s, "A"), new Card(Suits.h, "4"), new Card(Suits.d, "9")});
    }

    @Test
    public void getDeal() throws Exception {
        for (Map.Entry<String, Card[]> item: mapImages.entrySet()) {
            CardDeal cardDeal = new CardDeal(new File(PNG_DIRECTORY + item.getKey()));
            List<Card> deal = cardDeal.getDeal();
            deal.forEach(System.out::print);
            System.out.println();
            assertEquals(item.getValue().length, deal.size());
            assertArrayEquals(item.getKey(), item.getValue(), deal.toArray());
        }

    }
}