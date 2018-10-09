package pro.thaipad;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardDeal {

    final static public Color COLOR_TILE = new Color(255, 255, 255);
    final static public Color COLOR_TILE_SHADOW = new Color(120, 120, 120);
    final static public Color COLOR_RED = new Color(205, 73, 73);
    final static public Color COLOR_RED_SHADOW = new Color(96, 34, 34);
    final static public Color COLOR_BLACK = new Color(35, 35, 38);
    final static public Color COLOR_BLACK_SHADOW = new Color(16, 16, 18);

    final static public int CARD_STEP = 72;

    final static public int X_TILE = 199;
    final static public int Y_TILE = 593;

    final static public int X_CENTER_SUIT = 184;
    final static public int Y_CENTER_SUIT = 650;

    final static public int X_WHITE_IF_SPADE = 178;
    final static public int Y_WHITE_IF_SPADE = 638;

    final static public int X_WHITE_IF_DIAMOND = 173;
    final static public int Y_WHITE_IF_DIAMOND = 643;


    final static private Point[] TWO = new Point[] {
            new Point(153, 612), new Point(166, 599), new Point(156, 612), new Point(166, 612), new Point(156, 594)};
    final static private Point[] THREE = new Point[] {
            new Point(153, 592), new Point(161, 597), new Point(160, 612), new Point(154, 610), new Point(162, 603)};
    final static private Point[] FOUR = new Point[] {
            new Point(153, 605), new Point(165, 599), new Point(162, 607), new Point(159, 599), new Point(169, 608)};
    final static private Point[] FIVE = new Point[] {
            new Point(154, 592), new Point(155, 598), new Point(157, 593), new Point(166, 594), new Point(158, 602)};
    final static private Point[] SIX = new Point[] {
            new Point(153, 602), new Point(161, 600), new Point(157, 596), new Point(165, 595), new Point(165, 610), new Point(166, 611), new Point(160, 592)};
    final static private Point[] SEVEN = new Point[] {
            new Point(153, 592), new Point(157, 613), new Point(164, 598), new Point(167, 593), new Point(164, 598), new Point(158, 608)};
    final static private Point[] EIGHT = new Point[] {
            new Point(153, 606), new Point(165, 599), new Point(160, 602), new Point(160, 593), new Point(159, 612), new Point(155, 594)};
    final static private Point[] NINE = new Point[] {
            new Point(155, 595), new Point(163, 603), new Point(155, 603), new Point(164, 595), new Point(165, 609), new Point(167, 602)};
    final static private Point[] TEN = new Point[] {
            new Point(150, 594), new Point(167, 594), new Point(154, 603), new Point(159, 603), new Point(174, 602)};
    final static private Point[] JACK = new Point[] {
            new Point(164, 592), new Point(164, 597), new Point(164, 602), new Point(157, 612), new Point(152, 610)};
    final static private Point[] QUEEN = new Point[] {
            new Point(154, 603), new Point(162, 593), new Point(172, 602), new Point(163, 613), new Point(166, 606)};
    final static private Point[] KING = new Point[] {
            new Point(155, 592), new Point(154, 613), new Point(169, 612), new Point(169, 593), new Point(162, 602)};
    final static private Point[] ACE = new Point[] {
            new Point(151, 612), new Point(168, 612), new Point(159, 595), new Point(166, 606), new Point(162, 606)};

    final static private Map<String, Point[]> RANKS = new HashMap<>();

    static {
        RANKS.put("2", TWO);
        RANKS.put("3", THREE);
        RANKS.put("4", FOUR);
        RANKS.put("5", FIVE);
        RANKS.put("6", SIX);
        RANKS.put("7", SEVEN);
        RANKS.put("8", EIGHT);
        RANKS.put("9", NINE);
        RANKS.put("10", TEN);
//        RANKS.put("J", JACK);
        RANKS.put("Q", QUEEN);
        RANKS.put("K", KING);
        RANKS.put("A", ACE);
    }


    public static List<Card> getDeal(File file)  {
        List<Card> deal = new ArrayList<>();

        try {
            BufferedImage imageDeal = ImageIO.read(file);
            for (int i = 0; i < 5; ++i) {
                if (hasCard(imageDeal, i)) {
                    deal.add(new Card(getSuit(imageDeal, i), getRank(imageDeal, i)));
                }
            }
        } catch(IOException e) {
            // writing stacktrace to log file must be here
        }
        return deal;
    }

    private static Color getColor(BufferedImage img, int x, int y) {
        int rgb = img.getRGB( x, y);
//        int alpha = (rgb >> 24) & 0xFF;
        int red = (rgb >> 16) & 0xFF;
        int green = (rgb >> 8) & 0xFF;
        int blue = (rgb) & 0xFF;
        return new Color(red, green, blue);
    }

    private static boolean isWhite(BufferedImage img, int x, int y) {
        return COLOR_TILE.equals(getColor(img, x, y)) ||
                COLOR_TILE_SHADOW.equals(getColor(img, x, y));
    }

    private static boolean isRed(BufferedImage img, int x, int y) {
//        System.out.println(getColor(img, x, y));
        return COLOR_RED.equals(getColor(img, x, y)) ||
                COLOR_RED_SHADOW.equals(getColor(img, x, y));
    }

    private static boolean isBlack(BufferedImage img, int x, int y) {
        return COLOR_BLACK.equals(getColor(img, x, y)) ||
                COLOR_BLACK_SHADOW.equals(getColor(img, x, y));
    }

    private static boolean hasCard(BufferedImage img, int numCard) {
        return isWhite(img, X_TILE + CARD_STEP * numCard, Y_TILE);
    }

    private static Suits getSuit(BufferedImage img, int numCard) {
        if (isRed(img, X_CENTER_SUIT + CARD_STEP * numCard, Y_CENTER_SUIT)) {
            if (isWhite(img, X_WHITE_IF_DIAMOND + CARD_STEP * numCard, Y_WHITE_IF_DIAMOND)) {
                return Suits.d;
            } else {
                return Suits.h;
            }
        } else {
            if (isWhite(img, X_WHITE_IF_SPADE + CARD_STEP * numCard, Y_WHITE_IF_SPADE)) {
                return Suits.s;
            } else {
                return Suits.c;
            }
        }
    }

    private static boolean isAllNotWhite(BufferedImage img, Point[] points, int numCard) {
        for (int i = 0; i < points.length; ++i) {
            if (isWhite(img, points[i].x + CARD_STEP * numCard, points[i].y)) {
                return false;
            }
        }
        return true;
    }

    private static String getRank(BufferedImage img, int numCard) {
        for (Map.Entry<String, Point[]> item : RANKS.entrySet()) {
            if (isAllNotWhite(img, item.getValue(), numCard)) {
                return item.getKey();
            }
        }
        return "J";
    }

}
