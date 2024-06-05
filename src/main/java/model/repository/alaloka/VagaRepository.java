package model.repository.alaloka;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.entity.Vaga;
import model.repository.Banco;
import model.repository.BaseRepository;

public class VagaRepository implements BaseRepository<Vaga> {

    @Override
    public Vaga salvar(Vaga novaVaga) {
        String sql = "INSERT INTO Vaga(numeroVaga, tipoVaga, disponivel) VALUES (?, ?, ?)";
        Connection conexao = Banco.getConnection();
        PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);

        try {
            stmt.setInt(1, novaVaga.getNumeroVaga());
            stmt.setString(2, novaVaga.getTipoVaga());
            stmt.setBoolean(3, novaVaga.isDisponivel());

            stmt.execute();
            ResultSet resultado = stmt.getGeneratedKeys();
            if (resultado.next()) {
                novaVaga.setId(resultado.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar nova vaga");
            System.out.println("Erro: " + e.getMessage());
        } finally {
            Banco.closePreparedStatement(stmt);
            Banco.closeConnection(conexao);
        }

        return novaVaga;
    }

    @Override
    public boolean excluir(int id) {
        Connection conn = Banco.getConnection();
        String query = "DELETE FROM Vaga WHERE id = ?";
        boolean excluiu = false;

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            excluiu = stmt.executeUpdate() > 0;
        } catch (SQLException erro) {
            System.out.println("Erro ao excluir vaga");
            System.out.println("Erro: " + erro.getMessage());
        } finally {
            Banco.closeConnection(conn);
        }

        return excluiu;
    }

    @Override
    public boolean alterar(Vaga vagaEditada) {
        String query = "UPDATE Vaga SET numeroVaga = ?, tipoVaga = ?, disponivel = ? WHERE id = ?";
        boolean alterou = false;
        Connection conn = Banco.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, vagaEditada.getNumeroVaga());
            stmt.setString(2, vagaEditada.getTipoVaga());
            stmt.setBoolean(3, vagaEditada.isDisponivel());
            stmt.setInt(4, vagaEditada.getId());
            alterou = stmt.executeUpdate() > 0;
        } catch (SQLException erro) {
            System.out.println("Erro ao atualizar vaga");
            System.out.println("Erro: " + erro.getMessage());
        } finally {
            Banco.closeConnection(conn);
        }

        return alterou;
    }

    @Override
    public Vaga consultarPorId(int id) {
        Connection conn = Banco.getConnection();
        String query = "SELECT * FROM Vaga WHERE id = ?";
        Vaga vaga = null;

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet resultado = stmt.executeQuery()) {
                if (resultado.next()) {
                    vaga = new Vaga();
                    vaga.setId(resultado.getInt("id"));
                    vaga.setNumeroVaga(resultado.getInt("numeroVaga"));
                    vaga.setTipoVaga(resultado.getString("tipoVaga"));
                    vaga.setDisponivel(resultado.getBoolean("disponivel"));
                }
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao consultar vaga com o id: " + id);
            System.out.println("Erro: " + erro.getMessage());
        } finally {
            Banco.closeConnection(conn);
        }

        return vaga;
    }

    @Override
    public ArrayList<Vaga> consultarTodos() {
        ArrayList<Vaga> vagas = new ArrayList<>();
        Connection conn = Banco.getConnection();
        String query = "SELECT * FROM Vaga";

        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet resultado = stmt.executeQuery()) {

            while (resultado.next()) {
                Vaga vaga = new Vaga();
                vaga.setId(resultado.getInt("id"));
                vaga.setNumeroVaga(resultado.getInt("numeroVaga"));
                vaga.setTipoVaga(resultado.getString("tipoVaga"));
                vaga.setDisponivel(resultado.getBoolean("disponivel"));
                vagas.add(vaga);
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao consultar todas as vagas");
            System.out.println("Erro: " + erro.getMessage());
        } finally {
            Banco.closeConnection(conn);
        }

        return vagas;
    }
}
