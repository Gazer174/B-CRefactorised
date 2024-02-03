/*  Hussein Adem 2024-01-29
    Hussein.adem@hotmail.com

    interface for implementing a databas connection for game
 */

package dao;

import org.junit.jupiter.api.function.Executable;

public interface GameDAOImpl {
    int getUserId(String name);
    TopTenListFromDB getTopList();
    void saveResult(int nGuess, int id);
}
