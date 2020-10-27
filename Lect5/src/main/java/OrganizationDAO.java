import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrganizationDAO implements DAO<Organization>{
    final Connection connection;

    public OrganizationDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Organization get(int id) {
        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM ORGANIZATION WHERE ID_ORGANIZATION = " + id)) {
                while (rs.next()) {
                    return new Organization(rs.getInt("ID_ORGANIZATION"), rs.getInt("INN"), rs.getInt("CHECKING_ACCOUNT"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        throw new IllegalStateException("Record with id " + id + "not found");
    }

    @Override
    public List<Organization> getAll() {
        final List<Organization> result = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM ORGANIZATION")) {
                while (rs.next()) {
                    result.add(new Organization(rs.getInt("ID_ORGANIZATION"), rs.getInt("INN"), rs.getInt("CHECKING_ACCOUNT")));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public void save(Organization entity) {
        try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ORGANIZATION(ID_ORGANIZATION, INN, CHECKING_ACCOUNT) VALUES(?,?,?)")) {
            preparedStatement.setInt(1, entity.getIdOrganization());
            preparedStatement.setInt(2, entity.getInn());
            preparedStatement.setInt(3, entity.getCheckingAccount());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Organization entity) {
        try(PreparedStatement preparedStatement = connection.prepareStatement("UPDATE ORGANIZATION SET INN = ?, CHECKING_ACCOUNT = ? WHERE ID_ORGANIZATION = ?")) {
            int cnt = 1;
            preparedStatement.setInt(cnt++, entity.getInn());
            preparedStatement.setInt(cnt++, entity.getCheckingAccount());
            preparedStatement.setInt(cnt++, entity.getIdOrganization());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Organization entity) {
        try(PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM ORGANIZATION WHERE ID_ORGANIZATION = ?")) {
            preparedStatement.setInt(1, entity.getIdOrganization());
            if (preparedStatement.executeUpdate() == 0) {
                throw new IllegalStateException("Record with id = " + entity.getIdOrganization() + " not found");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
