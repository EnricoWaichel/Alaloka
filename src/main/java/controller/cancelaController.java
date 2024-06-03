package controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import model.entity.Cancela;
import model.repository.alaloka.CancelaRepository;

import java.net.URI;
import java.util.List;

@Path("/cancelas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class cancelaController {

    @Inject
    private CancelaRepository cancelaRepository;

    @GET
    public List<Cancela> listarCancelas() {
        return cancelaRepository.consultarTodos();
    }

    @GET
    @Path("/{id}")
    public Response buscarCancelaPorId(@PathParam("id") int id) {
        Cancela cancela = cancelaRepository.consultarPorId(id);
        if (cancela != null) {
            return Response.ok(cancela).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response cadastrarCancela(Cancela cancela, @Context UriInfo uriInfo) {
        Cancela novaCancela = cancelaRepository.salvar(cancela);
        URI uri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(novaCancela.getIdCancela())).build();
        return Response.created(uri).entity(novaCancela).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizarCancela(@PathParam("id") int id, Cancela cancela) {
        cancela.setIdCancela(id); 
        if (cancelaRepository.alterar(cancela)) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletarCancela(@PathParam("id") int id) {
        if (cancelaRepository.excluir(id)) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
