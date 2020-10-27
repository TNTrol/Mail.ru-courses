import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NomenclatureDAO implements DAO<Nomenclature>{
    final Connection connection;

    public NomenclatureDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Nomenclature get(int id) {
        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM NOMENCLATURE WHERE ID_NOMENCLATURE = " + id)) {
                while (rs.next()) {
                    return new Nomenclature(rs.getInt("ID_NOMENCLATURE"), rs.getString("NAME"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        throw new IllegalStateException("Record with id " + id + "not found");
    }

    @Override
    public List<Nomenclature> getAll() {
        final List<Nomenclature> result = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM NOMENCLATURE")) {
                while (rs.next()) {
                    result.add(new Nomenclature(rs.getInt("ID_NOMENCLATURE"), rs.getString("NAME")));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public void save(Nomenclature entity) {
        try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO NOMENCLATURE(ID_NOMENCLATURE, NAME) VALUES(?,?)")) {
            preparedStatement.setInt(1, entity.getIdNomenclature());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Nomenclature entity) {
        try(PreparedStatement preparedStatement = connection.prepareStatement("UPDATE NOMENCLATURE SET NAME = ? WHERE ID_NOMENCLATURE  = ?")) {
            int cnt = 1;
            preparedStatement.setString(cnt++, entity.getName());
            preparedStatement.setInt(cnt++, entity.getIdNomenclature());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Nomenclature entity) {
        try(PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM NOMENCLATURE WHERE ID_NOMENCLATURE = ?")) {
            preparedStatement.setInt(1, entity.getIdNomenclature());
            if (preparedStatement.executeUpdate() == 0) {
                throw new IllegalStateException("Record with id = " + entity.getIdNomenclature() + " not found");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
