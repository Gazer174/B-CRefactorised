/*  Hussein Adem 2024-01-29
    Hussein.adem@hotmail.com

    interface for implementing a databas connection for game
 */

package dao;

public interface GameDAO {
    int getUserId(String name);
    TopTenListFromDB getTopList();
    void saveResult(int nGuess, int id);
}
