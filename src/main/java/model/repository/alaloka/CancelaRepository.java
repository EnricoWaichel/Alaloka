package model.repository.alaloka;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.Cancela;
import model.repository.Banco;

public class CancelaRepository {

    public Cancela salvar(Cancela novaCancela) {
        String query = "INSERT INTO Cancela (status) VALUES (?)";
        try (Connection conn = Banco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, novaCancela.getStatus());

            stmt.executeUpdate();
            ResultSet resultado = stmt.getGeneratedKeys();

            if (resultado.next()) {
                novaCancela.setIdCancela(resultado.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar cancela: " + e.getMessage(), e);
        }
        return novaCancela;
    }

    public boolean excluir(int idCancela) {
        String query = "DELETE FROM Cancela WHERE id = ?";
        try (Connection conn = Banco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idCancela);
            int linhasAfetadas = stmt.executeUpdate();

            return linhasAfetadas > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar cancela: " + e.getMessage(), e);
        }
    }

    public boolean alterar(Cancela cancela) {
        String query = "UPDATE Cancela SET status = ? WHERE id = ?";
        try (Connection conn = Banco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, cancela.getStatus());
            stmt.setInt(2, cancela.getIdCancela());
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar cancela: " + e.getMessage(), e);
        }
    }

    public Cancela consultarPorId(int idCancela) {
        String query = "SELECT * FROM Cancela WHERE id = ?";
        try (Connection conn = Banco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idCancela);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Cancela cancela = new Cancela();
                    cancela.setIdCancela(rs.getInt("id"));
                    cancela.setStatus(rs.getString("status"));
                    return cancela;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar cancela por ID: " + e.getMessage(), e);
        }
    }

    public ArrayList<Cancela> consultarTodos() {
        List<Cancela> cancelas = new ArrayList<>();
        String query = "SELECT * FROM Cancela";
        try (Connection conn = Banco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cancela cancela = new Cancela();
                cancela.setIdCancela(rs.getInt("id"));
                cancela.setStatus(rs.getString("status"));
                cancelas.add(cancela);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar cancelas: " + e.getMessage(), e);
        }
        return (ArrayList<Cancela>) cancelas; 
    }
}