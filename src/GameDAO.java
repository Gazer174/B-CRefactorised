public interface GameDAO {
    int getUserId(String name);
    TopTenList getTopList();
    void saveResult(int nGuess, int id);
}
