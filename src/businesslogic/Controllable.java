package businesslogic;

import java.sql.SQLException;

public interface Controllable {
    void run() throws SQLException, InterruptedException;
}
