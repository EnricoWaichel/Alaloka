package model.repository.alaloka;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.entity.Cancela;
import model.repository.Banco;
import model.repository.BaseRepository;

public class CancelaRepository implements BaseRepository<Cancela> {

    @Override
    public Cancela salvar(Cancela novaCancela) {
        String sql = "INSERT INTO Cancela(status) VALUES (?)";
        Connection conexao = Banco.getConnection();
        PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);

        try {
            stmt.setString(1, novaCancela.getStatus());

            stmt.execute();
            ResultSet resultado = stmt.getGeneratedKeys();
            if (resultado.next()) {
                novaCancela.setIdCancela(resultado.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar nova cancela");
            System.out.println("Erro: " + e.getMessage());
        } finally {
            Banco.closePreparedStatement(stmt);
            Banco.closeConnection(conexao);
        }

        return novaCancela;
    }

    @Override
    public boolean excluir(int id) {
        Connection conn = Banco.getConnection();
        String query = "DELETE FROM Cancela WHERE id = ?";
        boolean excluiu = false;

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            excluiu = stmt.executeUpdate() > 0;
        } catch (SQLException erro) {
            System.out.println("Erro ao excluir cancela");
            System.out.println("Erro: " + erro.getMessage());
        } finally {
            Banco.closeConnection(conn);
        }

        return excluiu;
    }

    @Override
    public boolean alterar(Cancela cancelaEditada) {
        String query = "UPDATE Cancela SET status = ? WHERE id = ?";
        boolean alterou = false;
        Connection conn = Banco.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, cancelaEditada.getStatus());
            stmt.setInt(2, cancelaEditada.getIdCancela());
            alterou = stmt.executeUpdate() > 0;
        } catch (SQLException erro) {
            System.out.println("Erro ao atualizar cancela");
            System.out.println("Erro: " + erro.getMessage());
        } finally {
            Banco.closeConnection(conn);
        }

        return alterou;
    }

    @Override
    public Cancela consultarPorId(int id) {
        Connection conn = Banco.getConnection();
        String query = "SELECT * FROM Cancela WHERE id = ?";
        Cancela cancela = null;

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet resultado = stmt.executeQuery()) {
                if (resultado.next()) {
                    cancela = new Cancela();
                    cancela.setIdCancela(resultado.getInt("id"));
                    cancela.setStatus(resultado.getString("status"));
                }
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao consultar cancela com o id: " + id);
            System.out.println("Erro: " + erro.getMessage());
        } finally {
            Banco.closeConnection(conn);
        }

        return cancela;
    }

    @Override
    public ArrayList<Cancela> consultarTodos() {
        ArrayList<Cancela> cancelas = new ArrayList<>();
        Connection conn = Banco.getConnection();
        String query = "SELECT * FROM Cancela";

        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet resultado = stmt.executeQuery()) {

            while (resultado.next()) {
                Cancela cancela = new Cancela();
                cancela.setIdCancela(resultado.getInt("id"));
                cancela.setStatus(resultado.getString("status"));
                cancelas.add(cancela);
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao consultar todas as cancelas");
            System.out.println("Erro: " + erro.getMessage());
        } finally {
            Banco.closeConnection(conn);
        }

        return cancelas;
    }
}
