package iav.interview;

import java.util.Arrays;
import java.util.Comparator;

class Interval {
    int start;
    int end;
    boolean startOpen;
    boolean endOpen;

    public Interval(int start, int end, boolean startOpen, boolean endOpen) {
        if (start > end || (start == end && (startOpen || endOpen))) {
            throw new IllegalArgumentException("Invalid interval endpoints");
        }
        this.start = start;
        this.end = end;
        this.startOpen = startOpen;
        this.endOpen = endOpen;
    }
}

class IntervalOverlapChecker {

    public static boolean doIntervalsOverlap(Interval a, Interval b) {
        int start1 = a.start;
        int end1 = a.end;
        int start2 = b.start;
        int end2 = b.end;

        if (a.startOpen) start1++;
        if (a.endOpen) end1--;
        if (b.startOpen) start2++;
        if (b.endOpen) end2--;

        return (start1 <= end2) && (start2 <= end1);
    }

    public static Object[] returnTrueAndPointIfNonEmptyOverlap(Interval a, Interval b) {
        int start1 = a.start;
        int end1 = a.end;
        int start2 = b.start;
        int end2 = b.end;

        boolean boolResult = false;
        Integer intResult = null;
        if (a.startOpen && a.endOpen && b.startOpen && b.endOpen) {
            start1++;
            start2++;
            end1--;
            end2--;
            if ((start1 <= end2) && (start2 <= end1)) {
                intResult = end1;
                boolResult = true;
            }
        }
        return new Object[]{boolResult, intResult};
    }
    public static boolean checkOverlappingOfAllIntervals(Interval[] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return true;
        }
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval.start));

        // Check for overlap
        for (int i = 1; i < intervals.length; i++) {
            if (doIntervalsOverlap(intervals[i-1],intervals[i])) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        Interval intervalA = new Interval(4, 8, true, true);
        Interval intervalB = new Interval(8, 13, true, true);
        Interval intervalC = new Interval(10, 19, true, true);
        Interval[] intervals= {intervalA,intervalB,intervalC};

        boolean overlap = doIntervalsOverlap(intervalA, intervalB);
        Object[] result = returnTrueAndPointIfNonEmptyOverlap(intervalA, intervalB);
        System.out.println(Arrays.toString(result));
        System.out.println(checkOverlappingOfAllIntervals(intervals));

        if (overlap) {
            System.out.println("Intervals overlap.");
        } else {
            System.out.println("Intervals do not overlap.");
        }
    }
}

