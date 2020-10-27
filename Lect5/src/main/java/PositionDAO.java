import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PositionDAO implements DAO<Position> {
    final Connection connection;

    public PositionDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Position get(int id) {
        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM POSITION WHERE ID_POSITION = " + id)) {
                while (rs.next()) {
                    //return new City (rs.getInt("id"), rs.getString("name"));
                    return new Position(rs.getInt("ID_POSITION"), rs.getInt("ID_WAYBILL"), rs.getInt("ID_NOMENCLATURE"),
                            rs.getInt("COUNT"), rs.getDouble("COST"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        throw new IllegalStateException("Record with id " + id + "not found");
    }

    @Override
    public List<Position> getAll() {
        final List<Position> result = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM POSITION")) {
                while (rs.next()) {
                    result.add(new Position(rs.getInt("ID_POSITION"), rs.getInt("ID_WAYBILL"), rs.getInt("ID_NOMENCLATURE"),
                            rs.getInt("COUNT"), rs.getDouble("COST")));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public void save(Position entity) {
        try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO  POSITION(ID_POSITION ,ID_WAYBILL, ID_NOMENCLATURE, COUNT, COST) VALUES(?,?,?,?,?)")) {
            int ind = 1;
            preparedStatement.setInt(ind++, entity.getIdPosition());
            preparedStatement.setInt(ind++, entity.getIdWaybill());
            preparedStatement.setInt(ind++, entity.getIdNomenclature());
            preparedStatement.setInt(ind++, entity.getCount());
            preparedStatement.setDouble(ind++, entity.getCost());
            //preparedStatement.setString(2, entity.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Position entity) {
        try(PreparedStatement preparedStatement = connection.prepareStatement("UPDATE POSITION SET ID_WAYBILL = ?, ID_NOMENCLATURE = ?, COST = ?, COUNT = ? WHERE ID_POSITION  = ?")) {
            int cnt = 1;
            preparedStatement.setInt(cnt++, entity.getIdWaybill());
            preparedStatement.setInt(cnt++, entity.getIdNomenclature());
            preparedStatement.setDouble(cnt++, entity.getCost());
            preparedStatement.setInt(cnt++, entity.getCount());
            preparedStatement.setInt(cnt++, entity.getIdPosition());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Position entity) {
        try(PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM POSITION WHERE ID_POSITION = ?")) {
            preparedStatement.setInt(1, entity.getIdPosition());
            if (preparedStatement.executeUpdate() == 0) {
                throw new IllegalStateException("Record with id = " + entity.getIdPosition() + " not found");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
