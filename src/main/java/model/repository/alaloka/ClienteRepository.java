package model.repository.alaloka;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.Cliente;
import model.repository.Banco;
import model.repository.BaseRepository;

public class ClienteRepository implements BaseRepository<Cliente> {

    public Cliente salvar(Cliente novoCliente) {
        String query = "INSERT INTO Cliente (nome, cpf, telefone) VALUES (?, ?, ?)";
        try (Connection conn = Banco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
             ResultSet resultado = stmt.executeQuery()) {

            stmt.setString(1, novoCliente.getNome());
            stmt.setString(2, novoCliente.getCpf());
            stmt.setString(3, novoCliente.getTelefone());

            stmt.executeUpdate();

            if (resultado.next()) {
                novoCliente.setIdCliente(resultado.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar cliente: " + e.getMessage(), e);
        }
        return novoCliente;
    }

    public boolean excluir(int idCliente) {
        String query = "DELETE FROM Cliente WHERE id = ?";
        try (Connection conn = Banco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idCliente);
            int linhasAfetadas = stmt.executeUpdate();

            return linhasAfetadas > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar cliente: " + e.getMessage(), e);
        }
    }

    public boolean alterar(Cliente cliente) {
        String query = "UPDATE Cliente SET nome = ?, cpf = ?, telefone = ? WHERE id = ?";
        try (Connection conn = Banco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getTelefone());
            stmt.setInt(4, cliente.getIdCliente());
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar cliente: " + e.getMessage(), e);
        }
    }

    public Cliente consultarPorId(int idCliente) {
        String query = "SELECT * FROM Cliente WHERE id = ?";
        try (Connection conn = Banco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idCliente);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setIdCliente(rs.getInt("id"));
                    cliente.setNome(rs.getString("nome"));
                    cliente.setCpf(rs.getString("cpf"));
                    cliente.setTelefone(rs.getString("telefone"));
                    return cliente;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar cliente por ID: " + e.getMessage(), e);
        }
    }

    public ArrayList<Cliente> consultarTodos() {
        List<Cliente> clientes = new ArrayList<>();
        String query = "SELECT * FROM Cliente";
        try (Connection conn = Banco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
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
            throw new RuntimeException("Erro ao listar clientes: " + e.getMessage(), e);
        }
        return (ArrayList<Cliente>) clientes; 
    }
}