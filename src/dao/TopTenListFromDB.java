/*  Hussein Adem 2024-01-29
    Hussein.adem@hotmail.com

    this class only returns a toString for displaying toptenList!
 */

package dao;

import java.util.ArrayList;

public class TopTenListFromDB {

        private ArrayList<PlayerAverage> topList;

        public TopTenListFromDB(ArrayList<PlayerAverage> topList) {
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
