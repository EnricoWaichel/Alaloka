package controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import model.entity.Cliente;
import model.repository.alaloka.ClienteRepository;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class clienteController {

    @Inject
    private ClienteRepository clienteRepository;

    @GET
    public List<Cliente> listarClientes() {
        return clienteRepository.consultarTodos();
    }

    @GET
    @Path("/{id}")
    public Response buscarClientePorId(@PathParam("id") int id) {
        Cliente cliente = clienteRepository.consultarPorId(id);
        if (cliente != null) {
            return Response.ok(cliente).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response cadastrarCliente(Cliente cliente, @Context UriInfo uriInfo) {
        Cliente novoCliente = clienteRepository.salvar(cliente);
        URI uri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(novoCliente.getIdCliente())).build();
        return Response.created(uri).entity(novoCliente).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizarCliente(@PathParam("id") int id, Cliente cliente) {
        cliente.setIdCliente(id);
        if (clienteRepository.alterar(cliente)) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletarCliente(@PathParam("id") int id) {
        if (clienteRepository.excluir(id)) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
