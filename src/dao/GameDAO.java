package dao;

public interface GameDAO {
    int getUserId(String name);
    TopTenListFromDB getTopList();
    void saveResult(int nGuess, int id);
}
