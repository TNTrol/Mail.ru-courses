import org.flywaydb.core.Flyway;

import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write URL");
        String url = scanner.next();
        System.out.println("Write name of user");
        String user = scanner.next();
        System.out.println("Write password");
        String password = scanner.next();
        final Flyway flyway=Flyway.configure().dataSource(url,user,password )
                .locations("db")
                .load();
        flyway.clean();
        flyway.migrate();
        System.out.println("Check workable");
    }
}
