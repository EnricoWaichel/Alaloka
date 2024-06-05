package controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import model.entity.Ticket;
import model.repository.alaloka.TicketRepository;

import java.net.URI;
import java.util.List;

@Path("/tickets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ticketController {

    @Inject
    private TicketRepository ticketRepository;

    @GET
    @Path("/todos")
    public List<Ticket> listarTickets() {
        return ticketRepository.consultarTodos();
    }

    @GET
    @Path("/{id}")
    public Response buscarTicketPorId(@PathParam("id") int id) {
        Ticket ticket = ticketRepository.consultarPorId(id);
        if (ticket != null) {
            return Response.ok(ticket).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response cadastrarTicket(Ticket ticket, @Context UriInfo uriInfo) {
        Ticket novoTicket = ticketRepository.salvar(ticket);
        URI uri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(novoTicket.getId())).build();
        return Response.created(uri).entity(novoTicket).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizarTicket(@PathParam("id") int id, Ticket ticket) {
        ticket.setId(id);
        if (ticketRepository.alterar(ticket)) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletarTicket(@PathParam("id") int id) {
        if (ticketRepository.excluir(id)) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
