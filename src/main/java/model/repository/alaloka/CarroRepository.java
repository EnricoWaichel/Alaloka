package model.repository.alaloka;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.Carro;
import model.repository.Banco;

public class CarroRepository {

    public Carro salvar(Carro novoCarro) {
        String query = "INSERT INTO Carro (placa, tipoCarro) VALUES (?, ?)";
        try (Connection conn = Banco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
             ResultSet resultado = stmt.executeQuery()) {

            stmt.setString(1, novoCarro.getPlaca());
            stmt.setString(2, novoCarro.getTipoVeiculo());

            stmt.executeUpdate();

            if (resultado.next()) {
                novoCarro.setIdCarro(resultado.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar carro: " + e.getMessage(), e);
        }
        return novoCarro;
    }

    public boolean excluir(int idCarro) {
        String query = "DELETE FROM Carro WHERE id = ?";
        try (Connection conn = Banco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idCarro);
            int linhasAfetadas = stmt.executeUpdate();

            return linhasAfetadas > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar carro: " + e.getMessage(), e);
        }
    }

    public boolean alterar(Carro carro) {
        String query = "UPDATE Carro SET placa = ?, tipoCarro = ? WHERE id = ?";
        try (Connection conn = Banco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, carro.getPlaca());
            stmt.setString(2, carro.getTipoVeiculo());
            stmt.setInt(3, carro.getIdCarro());
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar carro: " + e.getMessage(), e);
        }
    }

    public Carro consultarPorId(int idCarro) {
        String query = "SELECT * FROM Carro WHERE id = ?";
        try (Connection conn = Banco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idCarro);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Carro carro = new Carro();
                    carro.setIdCarro(rs.getInt("id"));
                    carro.setPlaca(rs.getString("placa"));
                    carro.setTipoVeiculo(rs.getString("tipoCarro"));
                    return carro;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar carro por ID: " + e.getMessage(), e);
        }
    }

    public ArrayList<Carro> consultarTodos() {
        List<Carro> carros = new ArrayList<>();
        String query = "SELECT * FROM Carro";
        try (Connection conn = Banco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Carro carro = new Carro();
                carro.setIdCarro(rs.getInt("id"));
                carro.setPlaca(rs.getString("placa"));
                carro.setTipoVeiculo(rs.getString("tipoCarro"));
                carros.add(carro);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar carros: " + e.getMessage(), e);
        }
        return (ArrayList<Carro>) carros; 
    }
}