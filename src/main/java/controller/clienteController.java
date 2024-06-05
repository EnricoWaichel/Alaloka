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
import model.entity.Cliente;
import service.ClienteService;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class clienteController {

    private final ClienteService service = new ClienteService();

    @GET
    @Path("/todos")
    public List<Cliente> listarClientes() {
        return service.consultarTodosClientes();
    }

    @GET
    @Path("/{id}")
    public Cliente buscarClientePorId(@PathParam("id") int id) {
        return service.consultarClientePorId(id);
    }

    @POST
    public Cliente cadastrarCliente(Cliente cliente) throws AlalokaException {
        return service.salvarCliente(cliente);
    }

    @PUT
    public boolean atualizarCliente(Cliente clienteEditado) throws AlalokaException {
        return service.atualizarCliente(clienteEditado);
    }

    @DELETE
    @Path("/{id}")
    public boolean deletarCliente(@PathParam("id") int id) throws AlalokaException {
        return service.excluirCliente(id);
    }
}
