import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WaybillDAO implements DAO<Waybill> {
    final Connection connection;

    public WaybillDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Waybill get(int id) {
        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM WAYBILL WHERE ID_WAYBILL = " + id)) {
                while (rs.next()) {
                    return new Waybill(rs.getInt("ID_WAYBILL"), rs.getDate("DATE"),
                            rs.getInt("ID_ORGANIZATION"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        throw new IllegalStateException("Record with id " + id + "not found");
    }

    @Override
    public List<Waybill> getAll() {
        final List<Waybill> result = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM POSITION")) {
                while (rs.next()) {
                    result.add(new Waybill(rs.getInt("ID_WAYBILL"), rs.getDate("DATE"),
                            rs.getInt("ID_ORGANIZATION")));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public void save(Waybill entity) {
        try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO WAYBILL(ID_WAYBILL, DATE, ID_ORGANIZATION) VALUES(?,?,?)")) {
            preparedStatement.setInt(1, entity.getIdWaybill());
            preparedStatement.setDate(2, entity.getDate());
            preparedStatement.setInt(3, entity.getIdOrganization());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Waybill entity) {
        try(PreparedStatement preparedStatement = connection.prepareStatement("UPDATE WAYBILL SET DATE = ?, ID_ORGANIZATION = ? WHERE ID_WAYBILL  = ?")) {
            int cnt = 1;
            preparedStatement.setDate(cnt++, entity.getDate());
            preparedStatement.setDouble(cnt++, entity.getIdOrganization());
            preparedStatement.setInt(cnt++, entity.getIdWaybill());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Waybill entity) {
        try(PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM WAYBILL WHERE ID_WAYBILL = ?")) {
            preparedStatement.setInt(1, entity.getIdWaybill());
            if (preparedStatement.executeUpdate() == 0) {
                throw new IllegalStateException("Record with id = " + entity.getIdWaybill() + " not found");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
