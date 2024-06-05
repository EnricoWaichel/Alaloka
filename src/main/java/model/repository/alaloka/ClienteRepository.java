package model.repository.alaloka;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.entity.Cliente;
import model.repository.Banco;
import model.repository.BaseRepository;

public class ClienteRepository implements BaseRepository<Cliente> {

    @Override
    public Cliente salvar(Cliente novoCliente) {
        String query = "INSERT INTO Cliente (nome, cpf, telefone) VALUES (?, ?, ?)";
        Connection conn = Banco.getConnection();
        PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, query);

        try {
            stmt.setString(1, novoCliente.getNome());
            stmt.setString(2, novoCliente.getCpf());
            stmt.setString(3, novoCliente.getTelefone());

            stmt.execute();
            ResultSet resultado = stmt.getGeneratedKeys();
            if (resultado.next()) {
                novoCliente.setIdCliente(resultado.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar cliente");
            System.out.println("Erro: " + e.getMessage());
        } finally {
            Banco.closePreparedStatement(stmt);
            Banco.closeConnection(conn);
        }

        return novoCliente;
    }

    @Override
    public boolean excluir(int idCliente) {
        String query = "DELETE FROM Cliente WHERE id = ?";
        boolean excluiu = false;
        Connection conn = Banco.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idCliente);
            excluiu = stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir cliente");
            System.out.println("Erro: " + e.getMessage());
        } finally {
            Banco.closeConnection(conn);
        }

        return excluiu;
    }

    @Override
    public boolean alterar(Cliente clienteEditado) {
        String query = "UPDATE Cliente SET nome = ?, cpf = ?, telefone = ? WHERE id = ?";
        boolean alterou = false;
        Connection conn = Banco.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, clienteEditado.getNome());
            stmt.setString(2, clienteEditado.getCpf());
            stmt.setString(3, clienteEditado.getTelefone());
            stmt.setInt(4, clienteEditado.getIdCliente());
            alterou = stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar cliente");
            System.out.println("Erro: " + e.getMessage());
        } finally {
            Banco.closeConnection(conn);
        }

        return alterou;
    }

    @Override
    public Cliente consultarPorId(int idCliente) {
        String query = "SELECT * FROM Cliente WHERE id = ?";
        Cliente cliente = null;
        Connection conn = Banco.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idCliente);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente();
                    cliente.setIdCliente(rs.getInt("id"));
                    cliente.setNome(rs.getString("nome"));
                    cliente.setCpf(rs.getString("cpf"));
                    cliente.setTelefone(rs.getString("telefone"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar cliente com o id: " + idCliente);
            System.out.println("Erro: " + e.getMessage());
        } finally {
            Banco.closeConnection(conn);
        }

        return cliente;
    }

    @Override
    public ArrayList<Cliente> consultarTodos() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        String query = "SELECT * FROM Cliente";
        Connection conn = Banco.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setTelefone(rs.getString("telefone"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar todos os clientes");
            System.out.println("Erro: " + e.getMessage());
        } finally {
            Banco.closeConnection(conn);
        }

        return clientes;
    }
}
