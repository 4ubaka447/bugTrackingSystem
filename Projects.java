
package bugtrackingsystem;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


public class Projects {
    
    public int id;
    public String name_p;
    
    
    public Projects(int id, String name_p) throws SQLException, ClassNotFoundException{
        
        this.id = id;
        this.name_p = name_p;
        // Выводим сообщения в логи
        //System.err.println("Projects()");
    }
    // Выводим информацию по ПРОЕКТУ
    @Override
    public String toString() {
        return String.format("ID: %s | Проект: %s |",
                this.id, this.name_p);
    }
}
