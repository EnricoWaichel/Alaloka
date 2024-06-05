package model.repository.alaloka;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.entity.Carro;
import model.repository.Banco;
import model.repository.BaseRepository;

public class CarroRepository implements BaseRepository<Carro> {

    @Override
    public Carro salvar(Carro novoCarro) {
        String sql = "INSERT INTO Carro (placa, tipoCarro) VALUES (?, ?)";
        Connection conexao = Banco.getConnection();
        PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);

        try {
            stmt.setString(1, novoCarro.getPlaca());
            stmt.setString(2, novoCarro.getTipoVeiculo());

            stmt.execute();
            ResultSet resultado = stmt.getGeneratedKeys();
            if (resultado.next()) {
                novoCarro.setIdCarro(resultado.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar novo carro");
            System.out.println("Erro: " + e.getMessage());
        } finally {
            Banco.closePreparedStatement(stmt);
            Banco.closeConnection(conexao);
        }

        return novoCarro;
    }

    @Override
    public boolean excluir(int id) {
        Connection conn = Banco.getConnection();
        String query = "DELETE FROM Carro WHERE id = ?";
        boolean excluiu = false;

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            excluiu = stmt.executeUpdate() > 0;
        } catch (SQLException erro) {
            System.out.println("Erro ao excluir carro");
            System.out.println("Erro: " + erro.getMessage());
        } finally {
            Banco.closeConnection(conn);
        }

        return excluiu;
    }

    @Override
    public boolean alterar(Carro carroEditado) {
        String query = "UPDATE Carro SET placa = ?, tipoCarro = ? WHERE id = ?";
        boolean alterou = false;
        Connection conn = Banco.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, carroEditado.getPlaca());
            stmt.setString(2, carroEditado.getTipoVeiculo());
            stmt.setInt(3, carroEditado.getIdCarro());
            alterou = stmt.executeUpdate() > 0;
        } catch (SQLException erro) {
            System.out.println("Erro ao atualizar carro");
            System.out.println("Erro: " + erro.getMessage());
        } finally {
            Banco.closeConnection(conn);
        }

        return alterou;
    }

    @Override
    public Carro consultarPorId(int id) {
        Connection conn = Banco.getConnection();
        String query = "SELECT * FROM Carro WHERE id = ?";
        Carro carro = null;

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet resultado = stmt.executeQuery()) {
                if (resultado.next()) {
                    carro = new Carro();
                    carro.setIdCarro(resultado.getInt("id"));
                    carro.setPlaca(resultado.getString("placa"));
                    carro.setTipoVeiculo(resultado.getString("tipoCarro"));
                }
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao consultar carro com o id: " + id);
            System.out.println("Erro: " + erro.getMessage());
        } finally {
            Banco.closeConnection(conn);
        }

        return carro;
    }

    @Override
    public ArrayList<Carro> consultarTodos() {
        ArrayList<Carro> carros = new ArrayList<>();
        Connection conn = Banco.getConnection();
        String query = "SELECT * FROM Carro";

        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet resultado = stmt.executeQuery()) {

            while (resultado.next()) {
                Carro carro = new Carro();
                carro.setIdCarro(resultado.getInt("id"));
                carro.setPlaca(resultado.getString("placa"));
                carro.setTipoVeiculo(resultado.getString("tipoCarro"));
                carros.add(carro);
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao consultar todos os carros");
            System.out.println("Erro: " + erro.getMessage());
        } finally {
            Banco.closeConnection(conn);
        }

        return carros;
    }
}
