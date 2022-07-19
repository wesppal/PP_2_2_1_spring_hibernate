package hiber;

import hiber.config.AppConfig;
import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        userService.add(new User("User1", "Lastname1", "user1@mail.ru", null));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru", null));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru", null));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru", null));
        Car car1 = new Car("BMW", 535);
        Car car2 = new Car("citroen", 15);
        User user11 = new User("test", "test2", "test@mail.ru", car1);
        User user22 = new User("testtt", "testtt", "testtt@mail.ru", car2);
        userService.add(user11);
        userService.add(user22);
        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }

        User user1111 = userService.userByCar("BMW", 535);
        printUser(user1111);
        User user2222 = userService.userByCar("citroen", 15);
        printUser(user2222);


        context.close();
    }

    private static void printUser(User user1111) {
        if (user1111 != null) {
        System.out.println("___________________________________");
        System.out.println("Id = " + user1111.getId());
        System.out.println("First Name = " + user1111.getFirstName());
        System.out.println("Last Name = " + user1111.getLastName());
        System.out.println("Email = " + user1111.getEmail());
        System.out.println();
        }
    }
}
