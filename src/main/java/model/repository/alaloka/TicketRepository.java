package model.repository.alaloka;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.entity.Carro;
import model.entity.Cliente;
import model.entity.Ticket;
import model.entity.Vaga;
import model.repository.Banco;

public class TicketRepository {

    public Ticket salvar(Ticket novoTicket) {
        String query = "INSERT INTO Ticket (idVaga, idCarro, idCliente, dataEntrada, dataSaida, valorPago) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Banco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, novoTicket.getVaga().getId());
            stmt.setInt(2, novoTicket.getCarro().getIdCarro());
            stmt.setInt(3, novoTicket.getCliente().getIdCliente());
            stmt.setTimestamp(4, Timestamp.valueOf(novoTicket.getDataEntrada()));
            stmt.setTimestamp(5, novoTicket.getDataSaida() != null ? Timestamp.valueOf(novoTicket.getDataSaida()) : null);
            stmt.setDouble(6, novoTicket.getValorPago());

            stmt.executeUpdate();
            ResultSet resultado = stmt.getGeneratedKeys();

            if (resultado.next()) {
                novoTicket.setId(resultado.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar ticket: " + e.getMessage(), e);
        }
        return novoTicket;
    }

    public boolean excluir(int idTicket) {
        String query = "DELETE FROM Ticket WHERE id = ?";
        try (Connection conn = Banco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idTicket);
            int linhasAfetadas = stmt.executeUpdate();

            return linhasAfetadas > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar ticket: " + e.getMessage(), e);
        }
    }

    public boolean alterar(Ticket ticket) {
        String query = "UPDATE Ticket SET idVaga = ?, idCarro = ?, idCliente = ?, dataEntrada = ?, dataSaida = ?, valorPago = ? WHERE id = ?";
        try (Connection conn = Banco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, ticket.getVaga().getId());
            stmt.setInt(2, ticket.getCarro().getIdCarro());
            stmt.setInt(3, ticket.getCliente().getIdCliente());
            stmt.setTimestamp(4, Timestamp.valueOf(ticket.getDataEntrada()));
            stmt.setTimestamp(5, ticket.getDataSaida() != null ? Timestamp.valueOf(ticket.getDataSaida()) : null);
            stmt.setDouble(6, ticket.getValorPago());
            stmt.setInt(7, ticket.getId());

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar ticket: " + e.getMessage(), e);
        }
    }

    public Ticket consultarPorId(int idTicket) {
        String query = "SELECT * FROM Ticket WHERE id = ?";
        try (Connection conn = Banco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idTicket);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Buscar objetos Vaga, Carro e Cliente pelos IDs correspondentes
                    Vaga vaga = new VagaRepository().consultarPorId(rs.getInt("idVaga"));
                    Carro carro = new CarroRepository().consultarPorId(rs.getInt("idCarro"));
                    Cliente cliente = new ClienteRepository().consultarPorId(rs.getInt("idCliente"));

                    Ticket ticket = new Ticket(vaga, carro, cliente, null, null, idTicket); // TODO perguntar ao professor
                    ticket.setId(rs.getInt("id"));
                    ticket.setDataEntrada(rs.getTimestamp("dataEntrada").toLocalDateTime());
                    if (rs.getTimestamp("dataSaida") != null) {
                        ticket.setDataSaida(rs.getTimestamp("dataSaida").toLocalDateTime());
                    }
                    ticket.setValorPago(rs.getDouble("valorPago"));
                    return ticket;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar ticket por ID: " + e.getMessage(), e);
        }
    }
    
    public ArrayList<Ticket> consultarTodos() {
        List<Ticket> tickets = new ArrayList<>();
        String query = "SELECT * FROM Ticket";
        try (Connection conn = Banco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                // Buscar objetos Vaga, Carro e Cliente pelos IDs correspondentes
                Vaga vaga = new VagaRepository().consultarPorId(rs.getInt("idVaga"));
                Carro carro = new CarroRepository().consultarPorId(rs.getInt("idCarro"));
                Cliente cliente = new ClienteRepository().consultarPorId(rs.getInt("idCliente"));

                Ticket ticket = new Ticket(vaga, carro, cliente, null, null, 0); // TODO perguntar ao professor
                ticket.setId(rs.getInt("id"));
                ticket.setDataEntrada(rs.getTimestamp("dataEntrada").toLocalDateTime());
                if (rs.getTimestamp("dataSaida") != null) {
                    ticket.setDataSaida(rs.getTimestamp("dataSaida").toLocalDateTime());
                }
                ticket.setValorPago(rs.getDouble("valorPago"));
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar tickets: " + e.getMessage(), e);
        }
        return (ArrayList<Ticket>) tickets; 
    }

}