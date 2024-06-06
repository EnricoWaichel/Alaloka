package controller;

import java.util.List;

import exception.AlalokaException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entity.Ticket;
import service.TicketService;

@Path("/tickets")
public class ticketController {

    private final TicketService service = new TicketService();

    @GET
    @Path("/todos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ticket> listarTickets() {
        return service.consultarTodosTickets();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Ticket buscarTicketPorId(@PathParam("id") int id) {
        return service.consultarTicketPorId(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Ticket cadastrarTicket(Ticket ticket) throws AlalokaException {
        return service.salvarTicket(ticket);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean atualizarTicket(@PathParam("id") int id, Ticket ticket) throws AlalokaException {
        ticket.setId(id);
        return service.atualizarTicket(ticket);
    }

    @DELETE
    @Path("/{id}")
    public boolean deletarTicket(@PathParam("id") int id) throws AlalokaException {
        return service.excluirTicket(id);
    }
}
