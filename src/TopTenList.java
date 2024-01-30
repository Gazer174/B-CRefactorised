import java.util.ArrayList;

public class TopTenList {

        private ArrayList<PlayerAverage> topList;

        public TopTenList(ArrayList<PlayerAverage> topList) {
            this.topList = topList;
        }

        @Override
        public String toString() {
            StringBuilder result = new StringBuilder("Top Ten List\n    Player     Average\n");
            int pos = 1;
            topList.sort((p1,p2) -> Double.compare(p1.average, p2.average));
            for (PlayerAverage p : topList) {
                result.append(String.format("%3d %-10s%5.2f%n", pos, p.name, p.average));
                if (pos++ == 10) break;
            }
            return result.toString();
        }
}

class PlayerAverage {
    String name;
    double average;
    public PlayerAverage(String name, double average) {
        this.name = name;
        this.average = average;
    }

}
