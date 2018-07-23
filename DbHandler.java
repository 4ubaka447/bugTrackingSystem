package bugtrackingsystem;

import org.sqlite.JDBC;

import java.sql.*;
import java.util.*;

public class DbHandler {

    // Константа, в которой хранится адрес подключения к БД
    private static final String CON_STR = "jdbc:sqlite:D:\\JAVA\\ReadyProj\\BugTrackingSystem\\bugtrackingsystem.s3db";

    // Используем щаблон одиночка, чтобы не плодить множество
    // экземпляров класса DbHandler
    private static DbHandler instance = null;

    public static synchronized DbHandler getInstance() throws SQLException {
        if (instance == null)
            instance = new DbHandler();
        return instance;
    }

    // Объект, в котором будет храниться соединение с БД
    private Connection connection;

    private DbHandler() throws SQLException {
        // Регистриуем драйвер, с которым будем работать
        // в нашем случае Sqlite
        DriverManager.registerDriver(new JDBC());
        
        // Выполняем подключение к базе данных
        this.connection = DriverManager.getConnection(CON_STR);
    }

    // Выборка всех пользователей
    public List<Users> getAllUsers() throws ClassNotFoundException{

        // Statement используется для того, чтобы выполнить sql-запрос
        try (Statement statement = this.connection.createStatement()) {
            // В данный список будем загружать наши продукты, полученные из БД
            List<Users> users = new ArrayList<Users>();
            // В resultSet будет храниться результат нашего запроса,
            // который выполняется командой statement.executeQuery()
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users ");
            // Проходимся по нашему resultSet и заносим данные в products
            while (resultSet.next()) {
                users.add(new Users(resultSet.getInt("id"),
                                            (resultSet.getString("name_u"))));
            }
            // Возращаем наш список
            return users;

        } catch (SQLException e) {
            e.printStackTrace();
            // Если произошла ошибка - возвращаем пустую коллекцию
            return Collections.emptyList();
        }
    }
    // Выборка всех СВОБОДНЫХ ОТ ЗАДАЧ пользователей ДЛЯ УДАЛЕНИЯ 
    public List<Users> getAllUsersFree() throws ClassNotFoundException{

        // Statement используется для того, чтобы выполнить sql-запрос
        try (Statement statement = this.connection.createStatement()) {
            // В данный список будем загружать наши ПОЛЬЗОВАТЕЛИ, полученные из БД
            List<Users> users = new ArrayList<Users>(); //Занятые в проекте
            List<Users> users2 = new ArrayList<Users>(); //Все пользователи
            
            // В resultSet будет храниться результат нашего запроса,
            // который выполняется командой statement.executeQuery()
            ResultSet resultSet = statement.executeQuery("SELECT u.id,u.name_u FROM users u, tasks t WHERE t.id_user=u.id");
            // Проходимся по нашему resultSet и заносим данные в products
            while (resultSet.next()) {
                users.add(new Users(resultSet.getInt("id"),(resultSet.getString("name_u"))));    
            }
            ResultSet resultSet2 = statement.executeQuery("SELECT * FROM users");
            while (resultSet2.next()) {
                users2.add(new Users(resultSet2.getInt("id"),(resultSet2.getString("name_u"))));
            }
            for(int i=0; i<users2.size();i++){
                for(int j=0; j<users.size();j++){
                    if(users.get(j).id == users2.get(i).id){
                       users2.remove(i);
                    }
                }
            }
            // Возращаем наш список
            return users2;

        } catch (SQLException e) {
            e.printStackTrace();
            // Если произошла ошибка - возвращаем пустую коллекцию
            return Collections.emptyList();
        }
    }
    // Выборка всех СВОБОДНЫХ ОТ ЗАДАЧ проектов ДЛЯ УДАЛЕНИЯ 
    public List<Projects> getAllProjectsFree() throws ClassNotFoundException{

        // Statement используется для того, чтобы выполнить sql-запрос
        try (Statement statement = this.connection.createStatement()) {
            // В данный список будем загружать наши продукты, полученные из БД
            List<Projects> projects = new ArrayList<Projects>();
            List<Projects> projects2 = new ArrayList<Projects>();
            // В resultSet будет храниться результат нашего запроса,
            // который выполняется командой statement.executeQuery()
            ResultSet resultSet = statement.executeQuery("SELECT p.id,p.name_p "
                    + "FROM projects p, tasks t WHERE t.id_proj=p.id");
            // Проходимся по нашему resultSet и заносим данные в products
            while (resultSet.next()) {
                projects.add(new Projects(resultSet.getInt("id"),resultSet.getString("name_p")));
            }
            ResultSet resultSet2 = statement.executeQuery("SELECT * FROM projects");
            while (resultSet2.next()) {
                projects2.add(new Projects(resultSet2.getInt("id"),(resultSet2.getString("name_p"))));
            }
            for(int i=0; i<projects2.size();i++){
                for(int j=0; j<projects.size();j++){
                    if(projects.get(j).id == projects2.get(i).id){
                        projects2.remove(i);
                    }
                }
            }
            // Возращаем наш список
            return projects2;

        } catch (SQLException e) {
            e.printStackTrace();
            // Если произошла ошибка - возвращаем пустую коллекцию
            return Collections.emptyList();
        }
    }
    
    // ВЫБОРКА ВСЕХ ПРЕКТОВ
    public List<Projects> getAllProjects() throws ClassNotFoundException{

        // Statement используется для того, чтобы выполнить sql-запрос
        try (Statement statement = this.connection.createStatement()) {
            // В данный список будем загружать наши продукты, полученные из БД
            List<Projects> projects = new ArrayList<Projects>();
            // В resultSet будет храниться результат нашего запроса,
            // который выполняется командой statement.executeQuery()
            ResultSet resultSet = statement.executeQuery("SELECT * FROM projects");
            // Проходимся по нашему resultSet и заносим данные в products
            while (resultSet.next()) {
                projects.add(new Projects(resultSet.getInt("id"),
                                            resultSet.getString("name_p")));
            }
            // Возращаем наш список
            return projects;

        } catch (SQLException e) {
            e.printStackTrace();
            // Если произошла ошибка - возвращаем пустую коллекцию
            return Collections.emptyList();
        }
    }
    // ВЫБОРКА ВСЕХ ЗАДАЧ
    public List<Tasks> getAllTasks() throws ClassNotFoundException{

        // Statement используется для того, чтобы выполнить sql-запрос
        try (Statement statement = this.connection.createStatement()) {
            // В данный список будем загружать наши продукты, полученные из БД
            List<Tasks> tasks = new ArrayList<Tasks>();
            // В resultSet будет храниться результат нашего запроса,
            // который выполняется командой statement.executeQuery()
            ResultSet resultSet = statement.executeQuery("SELECT t.id, p.id as id_proj, u.id as id_user, p.name_p as id_proj_n, t.title, t.type, t.priority, u.name_u as id_user_n, t.desc"
                    + " FROM tasks t, projects p,users u WHERE t.id_proj=p.id AND t.id_user=u.id");
            // Проходимся по нашему resultSet и заносим данные в products
            while (resultSet.next()) {
                tasks.add(new Tasks(resultSet.getInt("id"), resultSet.getInt("id_proj"),
                        resultSet.getString("id_proj_n"),resultSet.getString("title"),
                        resultSet.getString("type"),resultSet.getString("priority"),
                        resultSet.getInt("id_user"),resultSet.getString("id_user_n"),
                        resultSet.getString("desc")
                ));
            }
            // Возращаем наш список
            return tasks;

        } catch (SQLException e) {
            e.printStackTrace();
            // Если произошла ошибка - возвращаем пустую коллекцию
            return Collections.emptyList();
        }
    }
    // ВЫБОРКА ВСЕХ ЗАДАЧ ПРОЕКТА
    public List<Tasks> getAllTasksInProj(int id_proj) throws ClassNotFoundException{

        // Statement используется для того, чтобы выполнить sql-запрос
        try (Statement statement = this.connection.createStatement()) {
            // В данный список будем загружать наши продукты, полученные из БД
            List<Tasks> tasks = new ArrayList<Tasks>();
            // В resultSet будет храниться результат нашего запроса,
            // который выполняется командой statement.executeQuery()
            ResultSet resultSet = statement.executeQuery("SELECT t.id, p.id as id_proj, u.id as id_user, p.name_p as id_proj_n, t.title, t.type, t.priority, u.name_u as id_user_n, t.desc"
                    + " FROM tasks t, projects p,users u WHERE t.id_proj=p.id AND t.id_user=u.id AND t.id_proj = "+id_proj);
            // Проходимся по нашему resultSet и заносим данные в products
            int empty = 0;
            while (resultSet.next()) {
                tasks.add(new Tasks(resultSet.getInt("id"), resultSet.getInt("id_proj"),resultSet.getString("id_proj_n"),resultSet.getString("title"),resultSet.getString("type"),resultSet.getString("priority"),resultSet.getInt("id_user"),resultSet.getString("id_user_n"),resultSet.getString("desc")
                        
                                            ));empty++;
            }
            if(empty==0){
                System.out.println("У проекта задач нет");
                System.out.println();
            }
            // Возращаем наш список
            return tasks;

        } catch (SQLException e) {
            e.printStackTrace();
            // Если произошла ошибка - возвращаем пустую коллекцию
            return Collections.emptyList();
        }
    }
    // ВЫБОРКА ВСЕХ ЗАДАЧ ИСПОЛНИТЕЛЯ
    public List<Tasks> getAllTasksInUser(int id_user) throws ClassNotFoundException{

        // Statement используется для того, чтобы выполнить sql-запрос
        try (Statement statement = this.connection.createStatement()) {
            // В данный список будем загружать наши продукты, полученные из БД
            List<Tasks> tasks = new ArrayList<Tasks>();
            // В resultSet будет храниться результат нашего запроса,
            // который выполняется командой statement.executeQuery()
            ResultSet resultSet = statement.executeQuery("SELECT t.id, p.id as id_proj, u.id as id_user, p.name_p as id_proj_n, t.title, t.type, t.priority, u.name_u as id_user_n, t.desc"
                    + " FROM tasks t, projects p,users u WHERE t.id_proj=p.id AND t.id_user=u.id AND t.id_user = "+id_user);
            // Проходимся по нашему resultSet и заносим данные в products
            int empty = 0;
            while (resultSet.next()) {
                tasks.add(new Tasks(resultSet.getInt("id"), resultSet.getInt("id_proj"),
                        resultSet.getString("id_proj_n"),resultSet.getString("title"),
                        resultSet.getString("type"),resultSet.getString("priority"),
                        resultSet.getInt("id_user"),resultSet.getString("id_user_n"),
                        resultSet.getString("desc")
                ));
                empty++;
            }
            if(empty==0){
                System.out.println("У Исполнителя задач нет");
                System.out.println();
            }
            // Возращаем наш список
            return tasks;

        } catch (SQLException e) {
            e.printStackTrace();
            // Если произошла ошибка - возвращаем пустую коллекцию
            return Collections.emptyList();
        }
    }

    // Добавление ПОЛЬЗОВАТЕЛЯ в БД
    public void addUser(Users user) {
        // Создадим подготовленное выражение, чтобы избежать SQL-инъекций
        try (PreparedStatement statement = this.connection.prepareStatement(
                        "INSERT INTO users (name_u) " +
                         "VALUES(?)")) {
            
            statement.setObject(1, (new String(user.name_u)));
            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            
        }
    }
    // Добавление ПРОЕКТА в БД
    public void addProject(Projects project) {
        // Создадим подготовленное выражение, чтобы избежать SQL-инъекций
        try (PreparedStatement statement = this.connection.prepareStatement(
                        "INSERT INTO projects (name_p) " +
                         "VALUES(?)")) {
            
            statement.setObject(1, project.name_p);
            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            
        }
    }
    // Добавление ЗАДАЧИ в БД
    public void addTask(Tasks task) {
        // Создадим подготовленное выражение, чтобы избежать SQL-инъекций
        try (PreparedStatement statement = this.connection.prepareStatement(
                        "INSERT INTO 'tasks' ('id_proj','title','type','priority','id_user','desc') " +
                         "VALUES(?,?,?,?,?,?)")) {
           
            statement.setObject(1,task.id_proj);
            statement.setObject(2,task.title);
            statement.setObject(3,task.type);
            statement.setObject(4,task.priority);
            statement.setObject(5,task.id_user);
            statement.setObject(6,task.desc);
            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            
        }
    }

    // Удаление ПОЛЬЗОВАТЕЛЯ по id
    public void deleteUser(int id) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM users WHERE id = ?")) {
            statement.setObject(1, id);
            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Удаление ПРОЕКТА по id
    public void deleteProject(int id) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM projects WHERE id = ?")) {
            statement.setObject(1, id);
            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Удаление ЗАДАЧИ по id
    public void deleteTask(int id) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM tasks WHERE id = ?")) {
            statement.setObject(1, id);
            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
