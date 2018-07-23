
package bugtrackingsystem;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


public class Users {
    
    public int id;
    public String name_u;
    
    public Users(int id, String name_u){
        
        this.id = id;
        this.name_u = name_u;
        // Выводим сообщения в логи
        //System.err.println("Users()");
    }
    // Выводим информацию по ИСПОЛНИТЕЛЯМ
    @Override
    public String toString() {
        return String.format("ID: %s | Пользователь: %s |",
                this.id, this.name_u);
    }
    
}
