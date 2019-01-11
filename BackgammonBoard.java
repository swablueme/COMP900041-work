//package backgammonboard;

import java.util.*;

public class BackgammonBoard {

    private static final int pointCount = 24;
    private HashMap<Object, ArrayList> board = new HashMap<>();

    BackgammonBoard() {
        for (int i = 0; i < pointCount; i++) {
            board.put(i, new ArrayList(Arrays.asList(null, 0)));

        }
        board.put("bar", new ArrayList(Arrays.asList(0, 0)));
    }

    public int getBarBlackCount() {
        return (int) board.get("bar").get(1);
    }

    public int getBarWhiteCount() {
        return (int) board.get("bar").get(0);
    }

    public int getPointCount(int point) {
        return board.containsKey(point) ? (int) board.get(point).get(1) : 0;
    }

    public boolean getPointBlack(int point) {
        return board.containsKey(point) ? String.valueOf(board.get(point).get(0)).equals("true") : false;
    }

    public void setPoint(int point, int count, boolean black) {
        //System.out.println("point setting " + point + " | count to set " + count + " | is black? " + black);
        if (point <= 23 && point >= 0 && count >= 0) {
            board.get(point).set(1, count);
            if (count != 0) {
                board.get(point).set(0, black);
            } else {
                //System.out.println("Setting to null");
                //System.out.println(board);
                //System.out.println(board.get(point));
                board.get(point).set(0, null);
            }
        }

    }

    public void move(int fromPoint, int toPoint) {
        if (fromPoint <= 23 && fromPoint >= 0
                && toPoint <= 23 && toPoint >= 0) {
            //System.out.println(board);
            boolean fromColour = getPointBlack(fromPoint);
            int fromPointCount = getPointCount(fromPoint);

            boolean toColour = getPointBlack(toPoint);
            int toPointCount = getPointCount(toPoint);
            int colourInt = toColour ? 1 : 0;

            if (((fromColour && (fromPoint > toPoint))
                    || (!fromColour && (fromPoint < toPoint)))
                    && Math.abs(fromPoint - toPoint) <= 6
                    && fromPointCount > 0) {
                //System.out.println("SUCCEEDED BARRIER");

                if (toColour == fromColour && toPointCount != 0) {
                    //System.out.println("from colour is equal to to colour");
                    setPoint(toPoint, toPointCount + 1, fromColour);
                    setPoint(fromPoint, fromPointCount - 1, fromColour);
                } else if ((toColour != fromColour) && (toPointCount == 1)) {
                    //System.out.println("from colour is different from to colour and can be taken");
                    setPoint(toPoint, 1, fromColour);
                    board.get("bar").set(colourInt, (int) board.get("bar").get(colourInt) + 1);
                    setPoint(fromPoint, fromPointCount - 1, fromColour);
                } else if (toPointCount == 0) {
                    //System.out.println("0 people there");
                    setPoint(toPoint, toPointCount + 1, fromColour);
                    setPoint(fromPoint, fromPointCount - 1, fromColour);
                }

            }
        }
        //System.out.println("END");
        //System.out.println(board);
        //System.out.println("");
    }

    @Override
    public String toString() {
        return board.toString();

    }

}
