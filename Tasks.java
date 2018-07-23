
package bugtrackingsystem;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


public class Tasks {
    
    public int id;
    public int id_proj;
    public String id_proj_n;
    public String title;
    public String type;
    public String priority;
    public int id_user;
    public String id_user_n;
    public String desc;
    
    public Tasks(int id, int id_proj,String id_proj_n,String title,
            String type,String priority,int id_user,String id_user_n,String desc){
        this.id = id;
        this.id_proj = id_proj;
        this.id_proj_n = id_proj_n;
        this.title = title;
        this.type = type;
        this.priority = priority;
        this.id_user = id_user;
        this.id_user_n = id_user_n;
        this.desc = desc;
    }
    
    // Выводим информацию по продукту
    @Override
    public String toString() {
        return String.format("ID: %s | Проект: %s | Тема: %s | Тип: %s | "
                            + "Приоритет: %s | Исполнитель: %s | Описание: %s |",
                this.id,
                this.id_proj_n + "(id="+this.id_proj+")",
                this.title,
                this.type,
                this.priority,
                this.id_user_n + "(id="+this.id_user+")",
                this.desc);
    }
}
