import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;

public class GameDAOImpl implements GameDAO{
    private Connection connection;
    private PreparedStatement getIdByName, getAllPlayers, getResultsById, saveResult;

    public GameDAOImpl(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/moohussein", "root", "Java23hello");
            getIdByName = connection.prepareStatement("select id,name from players where name = ?");
            getAllPlayers = connection.prepareStatement("select * from players");
            getResultsById = connection.prepareStatement("select * from results where playerid = ? ");
            saveResult = connection.prepareStatement("INSERT INTO results (result, playerid) values (?, ?)");
        } catch (SQLException e){
            throw new RuntimeException("fail inn " + e);
        }
    }

    public int getUserId(String name){
        int id = 0;
        try{
            getIdByName.setString(1,name);
            ResultSet rs = getIdByName.executeQuery();
            if (rs.next()){
                id = rs.getInt("id");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }
    public TopTenList getTopList(){
        ArrayList<PlayerAverage> topList = new ArrayList();
        try {
            ResultSet rs = getAllPlayers.executeQuery();

            int id;
            while(rs.next()) {
                id = rs.getInt("id");
                String name = rs.getString("name");
                getResultsById.setInt(1,id);
                ResultSet rs2 = getResultsById.executeQuery();
                int nGames = 0;

                int totalGuesses;
                for(totalGuesses = 0; rs2.next();
                    totalGuesses += rs2.getInt("result")) {
                    ++nGames;
                }

                if (nGames > 0) {
                    topList.add(new PlayerAverage(name, (double)totalGuesses / (double)nGames));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        topList.sort(Comparator.comparingDouble(p -> p.average));
        return new TopTenList(topList);
    }
    public void saveResult(int nGuess, int id){
        try{
            saveResult.setInt(1,nGuess);
            saveResult.setInt(2,id);
            saveResult.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
