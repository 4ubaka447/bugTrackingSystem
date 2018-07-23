package bugtrackingsystem;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

public class BugTrackingSystem {

    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, SQLException, UnsupportedEncodingException, IOException{

        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in,"Cp1251"));
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in,"utf-8"));
        clearConsole();//прокручиваем консоль(очищаем)
        // Определяем файл в который будем писать лог
        System.setErr(new PrintStream(new File("log.txt")));
        
        
        //>Вход как администратор
        String s = "";
        if(false)
        while(!s.equals("admin")){//Пароль для входа, можно поменять
            System.out.println("Введите пароль администратора(admin)");
            s = sc.nextLine();
            System.err.println("Введеный пароль:" + s);
        }
        //<
        // Выводим сообщения в логи
        System.err.println("Вы вошли в программу в программы:" + date());
                
        int a = -1;
        // ЦИКЛ ГЛАВНОГО МЕНЮ
        while(a != 0){
            menuGlavnoe();//отображает главное меню
            DbHandler dbHandler = DbHandler.getInstance();
            a = sc.nextInt();
            // МЕНЮ ЗАДАЧИ
            if(a == 1){
                a = -1;
                while(a != 5){
                    clearConsole();
                    System.out.println("Введено номер пункта:");
                    System.out.println("1 - Получить список всех Задач");
                    System.out.println("2 - Создание Задачи");
                    System.out.println("3 - Удаление Задачи");
                    System.out.println("0 - Назад");
                    a = sc.nextInt();
                    // ВЫВОД ВСЕХ ЗАДАЧ
                    if(a == 1){
                        // Выводим сообщения в логи
                        clearConsole();
                        // Вывод пользователей
                        // Получаем все записи и выводим их на консоль
                            List<Tasks> tasks = dbHandler.getAllTasks();
                            for (Tasks task : tasks) {
                                System.out.println(task.toString());
                            }
                        // Выводим сообщения в логи
                        System.err.println("ВЫВОД ВСЕХ ЗАДАЧ "+ date());
                    // ДОБАВЛЕНИЕ ЗАДАЧИ
                    }else if(a == 2){
                        // Выводим сообщения в логи
                        System.err.println("ДОБАВЛЕНИЕ ЗАДАЧИ");

                        System.out.println("Введите Тему Задачи:");
                        String title = br.readLine();
                        
                        //Вывод всех проектов для выбора
                        List<Projects> projects = dbHandler.getAllProjects();
                            for (Projects project : projects) {
                                System.out.println(project.toString());
                            }
                            
                        System.out.println("К какому проекту относится задача(Выберите ID проекта):");
                        int id_proj = sc.nextInt();
                        
                        System.out.println("Введите Тип Задачи:");
                        String type = br.readLine();
                        
                        System.out.println("Введите Приоритет Задачи:");
                        String priority = br.readLine();
                        
                        // Вывод всех исполнителей ДЛЯ ВЫБОРА
                        List<Users> users = dbHandler.getAllUsers();
                        for (Users user : users) {
                            System.out.println(user.toString());
                        }
                        
                        System.out.println("Выберите исполнителя Задачи(ID):");
                        int id_user = sc.nextInt();
                        
                        System.out.println("Опишите задачу:");
                        String desc = br.readLine();
                        
                        dbHandler.addTask(new Tasks(0,id_proj,"",title,type,priority,id_user,"",desc));
                        // Выводим сообщения в логи
                        System.err.println("вы создали задачу "+ date());
                    // УДОЛЕНИЕ ЗАДАЧИ
                    }else if(a == 3){
                        
                        clearConsole();
                        // Вывод пользователей
                        // Получаем все записи и выводим их на консоль
                            List<Tasks> tasks = dbHandler.getAllTasks();
                            for (Tasks task : tasks) {
                                System.out.println(task.toString());
                            }
                        System.out.println("Введите ID Задачи для удаления:");
                        int id = sc.nextInt();
                        dbHandler.deleteTask(id); 
                        System.err.println("вы удолили задачу id = "+id+" "+ date());
                    // при вводе 0 выход в главное меню
                    }else if(a == 0){
                        a = 5;
                    }
                    if(a != 5){
                        System.out.println("Введите любую цифру для продолжения");
                        int k = sc.nextInt();
                    }
                }
            }else if(a == 2){
                a = -1;
                while(a != 5){
                    clearConsole();
                    System.out.println("Введено номер пункта:");
                    System.out.println("1 - Получить список всех Проектов");
                    System.out.println("2 - Создание Проекта");
                    System.out.println("3 - Удаление Проекта");
                    System.out.println("4 - Получить список всех Задач в Проекте");
                    System.out.println("0 - Назад");
                    a = sc.nextInt();
                    // ПРОСМОТР ВСЕХ ПРОЕКТОВ
                    if(a == 1){
                        // Выводим сообщения в логи
                        System.err.println("просмотр всех проектов "+date());
                        clearConsole();
                        // Вывод проектов
                            // Получаем все записи и выводим их на консоль
                            List<Projects> projects = dbHandler.getAllProjects();
                            for (Projects project : projects) {
                                System.out.println(project.toString());
                            }
                        
                    // ДОБАВЛЕНИЕ ПРОЕКТА
                    }else if(a == 2){
                        
                        // Создание пользователя
                        System.out.println("Введите Название проекта:");
                        //sc.nextLine();
                        String project = br.readLine();
                        //System.out.println(user);
                        dbHandler.addProject(new Projects(0,project));
                        // Выводим сообщения в логи
                        System.err.println("просмотр всех проектов "+ date());
                    // УДОЛЕНИЕ ПРОЕКТОВ КОТОРЫЕ НЕ ЗАДЕЙСТВОВАНЫ В ЗАДАЧАХ
                    }else if(a == 3){
                        
                        
                        clearConsole();
                        // Добавляем запись
                            //dbHandler.addProduct(new Product(7,"Музей", 200, "Развлечения"));
                            List<Integer> free_projects = new ArrayList<Integer>();
                            // Получаем все записи и выводим их на консоль
                            List<Projects> projects = dbHandler.getAllProjectsFree();
                            for (Projects project : projects) {
                                free_projects.add(project.id);
                                System.out.println(project.toString());
                            }
                        System.out.println("Введите ID Проекта для удаления из списка или 0 для выхода:");
                        int id = -1;
                        while(id != 0){
                            id = sc.nextInt();
                            for(int i=0; i<free_projects.size();i++){
                                if(free_projects.get(i) == id){
                                    dbHandler.deleteProject(id);
                                    // Выводим сообщения в логи
                                    System.err.println("удаление проекта ID = "+id+" "+date());
                                    break;
                                }
                            }
                        }
                    //получить список всех задач в проекте    
                    }else if(a == 4){
                        clearConsole();
                        // Добавляем запись
                        // Получаем все записи и выводим их на консоль
                        List<Projects> projects = dbHandler.getAllProjects();
                        for (Projects project : projects) {
                            System.out.println(project.toString());
                        }
                        
                        System.out.println("Введите ID Проекта для отображения его задачь:");
                        int id = sc.nextInt();
                        clearConsole();
                        // Вывод пользователей
                        // Получаем все ЗАДАЧИ ПРОЕКТА и выводим их на консоль
                        List<Tasks> tasks = dbHandler.getAllTasksInProj(id);
                        for (Tasks task : tasks) {
                            System.out.println(task.toString());
                            System.out.println();
                        }
                    }else if(a == 0){
                        a = 5;
                    }
                    if(a != 5){
                        System.out.println("Введите любую цифру для продолжения");
                        int k = sc.nextInt();
                    }
                }
            }else if(a == 3){
                a = -1;
                while(a != 5){
                    clearConsole();
                    System.out.println("Введено номер пункта:");
                    System.out.println("1 - Получить список всех пользователей");
                    System.out.println("2 - Создание Пользователя");
                    System.out.println("3 - Удаление пользователя");
                    System.out.println("4 - Получить список всех задач, назначенных пользователя");
                    System.out.println("0 - Назад");
                    a = sc.nextInt();
                    //Получить список всех пользователей
                    if(a == 1){
                        // Выводим сообщения в логи
                        System.err.println("Получить список всех пользователей "+ date());
                        clearConsole();
                        
                        // Получаем все записи и выводим их на консоль
                        List<Users> users = dbHandler.getAllUsers();
                        for (Users user : users) {
                            System.out.println(user.toString());
                        }
                    //Создание Пользователя
                    }else if(a == 2){
                        

                        // Создание пользователя
                        System.out.println("Введите Имя пользователя:");
                        //sc.nextLine();
                        //String user = sc.next();
                        String user = br.readLine();
                        //System.out.println(user);
                        dbHandler.addUser(new Users(0,user));
                        // Выводим сообщения в логи
                        System.err.println("Создание Пользователя "+user+" "+date());
                    // удоление пользователя
                    }else if(a == 3){
                        

                        clearConsole();
                        // Добавляем запись
                            //dbHandler.addProduct(new Product(7,"Музей", 200, "Развлечения"));
                            List<Integer> free_users = new ArrayList<Integer>();
                            // Получаем все записи и выводим их на консоль
                            List<Users> users = dbHandler.getAllUsersFree();
                            for (Users user : users) {
                                free_users.add(user.id);
                                System.out.println(user.toString());
                            }
                        System.out.println("Введите ID Пользователя для удаления из списка или 0 для выхода:");
                        int id = -1;
                        while(id != 0){
                            id = sc.nextInt();
                            for(int i=0; i<free_users.size();i++){
                                if(free_users.get(i) == id){
                                    dbHandler.deleteUser(id);
                                    // Выводим сообщения в логи
                                    System.err.println("удоление пользователя id="+id+" "+date());
                                    break;
                                }
                            }
                            
                        }
                    //o	получить список всех задач, назначенных на конкретного исполнителя
                    }else if(a == 4){
                        clearConsole();
                       
                        // Получаем все записи и выводим их на консоль
                        List<Users> users = dbHandler.getAllUsers();
                        for (Users user : users) {
                            System.out.println(user.toString());
                        }
                        System.out.println("Введите ID Исполнителя для отображения его задачь:");
                        int id_user = sc.nextInt();
                        clearConsole();
                        // Вывод пользователей
                        // Получаем все записи и выводим их на консоль
                        List<Tasks> tasks = dbHandler.getAllTasksInUser(id_user);
                        for (Tasks task : tasks) {
                            System.out.println(task.toString());
                            System.out.println();
                        }
                    }else if(a == 0){
                        a = 5;
                    }
                    if(a != 5){
                        System.out.println("Введите любую цифру для продолжения");
                        int k = sc.nextInt();
                    }
                }
                
            }
            clearConsole();
            if((a != 0)){
                System.out.println("Выбран пункт:" + a);
                // Выводим сообщения в логи
                System.err.println("Выбран пункт:" + a);
            }
            else {
                System.out.println("Вы вышли из программы:" + date()); 
                // Выводим сообщения в логи
                System.err.println("Вы вышли из программы:" + date());
                return;
            }
           
        }
        sc.close();
        
    }
    /**
     * Очищает консоль (прокручивает вниз на 50 строк)
     */
    public final static void clearConsole()
    {
        try
        {
            for (int i = 0; i < 50; ++i) System.out.println();
        }
        catch (final Exception e)
        {
            //  Handle any exceptions.
        }
    }
      
    public static String date(){
        //Для вывода времени и даты в логи
        GregorianCalendar gcalendar = new GregorianCalendar();
        String months[] = {"Янв", "Фев", "Мар", "Апр", "Май", "Июн", "Июл", "Авг", "Сен", 
         "Окт", "Ноя", "Дек"};
        
        return months[gcalendar.get(Calendar.MONTH)] +
                        " " + gcalendar.get(Calendar.DATE) + " "+
                        gcalendar.get(Calendar.YEAR)+" "+
                        gcalendar.get(Calendar.HOUR) + ":"+
                        gcalendar.get(Calendar.MINUTE) + ":"+
                        gcalendar.get(Calendar.SECOND);
    }
    // Главное меню
    public static void menuGlavnoe(){
        System.out.println("Введено номер пункта:");
        System.out.println("1 - Задачи");
        System.out.println("2 - Проекты");
        System.out.println("3 - Пользователи");
        System.out.println("0 - Выход");
    }
    
}
