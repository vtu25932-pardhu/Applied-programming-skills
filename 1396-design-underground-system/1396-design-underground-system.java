import java.util.*;

class UndergroundSystem {

    private Map<Integer, Pair> checkInMap;


    private Map<String, int[]> travelMap;

    class Pair {
        String station;
        int time;

        Pair(String station, int time) {
            this.station = station;
            this.time = time;
        }
    }

    public UndergroundSystem() {
        checkInMap = new HashMap<>();
        travelMap = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        checkInMap.put(id, new Pair(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        Pair p = checkInMap.get(id);

        String route = p.station + "-" + stationName;
        int travelTime = t - p.time;

        travelMap.putIfAbsent(route, new int[2]);

        travelMap.get(route)[0] += travelTime;
        travelMap.get(route)[1] += 1;          

        checkInMap.remove(id);
    }

    public double getAverageTime(String startStation, String endStation) {
        String route = startStation + "-" + endStation;
        int[] data = travelMap.get(route);

        return (double) data[0] / data[1];
    }
}