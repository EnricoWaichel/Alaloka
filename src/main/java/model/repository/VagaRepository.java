package model.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.entity.Vaga;

public class VagaRepository implements BaseRepository<Vaga> {

	@Override
	public Vaga salvar(Vaga novaVaga) {
	    String query = "INSERT INTO Vaga (numeroVaga, tipoVaga, disponivel) VALUES (?, ?, ?)";
	    try (Connection conn = Banco.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
	         ResultSet resultado = stmt.executeQuery()) {

	        stmt.setInt(1, novaVaga.getNumeroVaga());
	        stmt.setString(2, novaVaga.getTipoVaga());
	        stmt.setBoolean(3, novaVaga.isDisponivel());

	        stmt.executeUpdate();

	        if (resultado.next()) {
	            novaVaga.setId(resultado.getInt(1));
	        }

	    } catch (SQLException e) {
	        throw new RuntimeException("Erro ao salvar vaga: " + e.getMessage(), e);
	    }
	    return novaVaga;
	}

	@Override
	public boolean excluir(int idVaga) {
	        String query = "DELETE FROM Vaga WHERE id = ?";
	        try (Connection conn = Banco.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {

	            stmt.setInt(1, idVaga);
	            stmt.executeUpdate();

	        } catch (SQLException e) {
	            throw new RuntimeException("Erro ao deletar vaga: " + e.getMessage(), e);
	        }
		return false;
	}

	@Override
	public boolean alterar(Vaga vaga) {
        String query = "UPDATE Vaga SET numeroVaga = ?, tipoVaga = ?, disponivel = ? WHERE id = ?";
        try (Connection conn = Banco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, vaga.getNumeroVaga());
            stmt.setString(2, vaga.getTipoVaga());
            stmt.setBoolean(3, vaga.isDisponivel());
            stmt.setInt(4, vaga.getId());
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar vaga: " + e.getMessage(), e);
        }
	}

	@Override
	public Vaga consultarPorId(int idVaga) {
        String query = "SELECT * FROM Vaga WHERE id = ?";
        try (Connection conn = Banco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idVaga);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Vaga vaga = new Vaga();
                    vaga.setId(rs.getInt("id"));
                    vaga.setNumeroVaga(rs.getInt("numeroVaga"));
                    vaga.setTipoVaga(rs.getString("tipoVaga"));
                    vaga.setDisponivel(rs.getBoolean("disponivel"));
                    return vaga;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar vaga por ID: " + e.getMessage(), e);
        }
	}

	@Override
	public ArrayList<Vaga> consultarTodos() {
	    List<Vaga> vagas = new ArrayList<>();
	    String query = "SELECT * FROM Vaga";
	    try (Connection conn = Banco.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            Vaga vaga = new Vaga();
	            vaga.setId(rs.getInt("id"));
	            vaga.setNumeroVaga(rs.getInt("numeroVaga"));
	            vaga.setTipoVaga(rs.getString("tipoVaga"));
	            vaga.setDisponivel(rs.getBoolean("disponivel"));
	            vagas.add(vaga);
	        }
	    } catch (SQLException e) {
	        throw new RuntimeException("Erro ao listar vagas: " + e.getMessage(), e);
	    }
	    return vagas; 
	}

}
