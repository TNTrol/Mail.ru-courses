import org.flywaydb.core.Flyway;

import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {
        final Flyway flyway = Flyway.configure()
                .dataSource("jdbc:postgresql://127.0.0.1:5438/l04", "postgres", "PgSQL12")
                .locations("db")
                .load();
        flyway.clean();
        flyway.migrate();

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5438/l04", "postgres", "PgSQL12"))
        {
            Scanner scanner = new Scanner(System.in);
            try (Statement stmt = connection.createStatement()) {
                try (ResultSet rs = stmt.executeQuery("SELECT * " +
                        "FROM ORGANIZATION " +
                        "JOIN WAYBILL ON WAYBILL.ID_ORGANIZATION = ORGANIZATION.ID_ORGANIZATION" +
                        "ORDER BY (SELECT SUM(COUNT) FROM POSITION WHERE WAYBILL.ID_WAYBILL = POSITION.ID_WAYBILL) LIMIT 10;")) {
                    while (rs.next()) {
                        int id = rs.getInt("ID_ORGANIZATION");
                        int inn = rs.getInt("INN");
                        int checkingAccount = rs.getInt("CHECKING_ACCOUNT");
                        System.out.println("ROW id:" + id + " inn:" + inn + " CHECKING_ACCOUNT:" + checkingAccount);
                    }
                }
                ////_______________________________________________________________________________\\\\
                System.out.println("Write minimum sum");
                int sum = scanner.nextInt();
                try (ResultSet rs = stmt.executeQuery("SELECT * " +
                        "FROM ORGANIZATION" +
                        "JOIN WAYBILL ON WAYBILL.ID_ORGANIZATION = ORGANIZATION.ID_ORGANIZATION" +
                        "WHERE " + sum + " < (SELECT SUM(COUNT * COST) FROM POSITION WHERE WAYBILL.ID_WAYBILL = POSITION.ID_WAYBILL);")) {
                    while (rs.next()) {
                        int id = rs.getInt("ID_ORGANIZATION");
                        int inn = rs.getInt("INN");
                        int checkingAccount = rs.getInt("CHECKING_ACCOUNT");
                        System.out.println("ROW id:" + id + " inn:" + inn + " CHECKING_ACCOUNT:" + checkingAccount);
                    }
                }

                //____________________________________________\\
                try (ResultSet rs = stmt.executeQuery("SELECT DATE, SUM(POSITION.COUNT), SUM(POSITION.COST*POSITION.COUNT) " +
                        "FROM WAYBILL" +
                        "JOIN POSITION ON WAYBILL.ID_WAYBILL = POSITION.ID_WAYBILL" +
                        "GROUP BY WAYBILL.DATE;")) {
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int numcols = rsmd.getColumnCount();
                    for (int i = 1; i <= numcols; i++) {
                        System.out.print(rsmd.getColumnLabel(i) + " " );
                    }
                }
                //___________________________________________\\
                try (ResultSet rs = stmt.executeQuery("SELECT ORGANIZATION.ID_ORGANIZATION , SUM(POSITION.COST * POSITION.COUNT)" +
                        "FROM ORGANIZATION" +
                        "JOIN WAYBILL ON WAYBILL.ID_ORGANIZATION = ORGANIZATION.ID_ORGANIZATION" +
                        "JOIN POSITION ON WAYBILL.ID_WAYBILL = POSITION.ID_WAYBILL;")) {
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int numcols = rsmd.getColumnCount();
                    for (int i = 1; i <= numcols; i++) {
                        System.out.print(rsmd.getColumnLabel(i) + " " );
                    }
                }
                //_____________________________________________\\

             /* System.out.println("Write two dates");
              String str = scanner.nextLine();
              String str2 = scanner.nextLine();
              try (ResultSet rs = stmt.executeQuery("SELECT AVG(COUNT * COST) " +
                        "FROM POSITION" +
                        "JOIN WAYBILL ON WAYBILL.ID_WAYBILL = POSITION.ID_WAYBILL" +
                        "WHERE DATE BETWEEN '"+ str+"' AND '"+str2+"';")) {
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int numcols = rsmd.getColumnCount();
                    for (int i = 1; i <= numcols; i++) {
                        System.out.print(rsmd.getColumnLabel(i) + " " );
                    }
                }*/
            }
        }
        catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }


        System.out.println("Hello world.");

    }
}
