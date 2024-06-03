package controller;

import java.net.URI;
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
import model.entity.Vaga;
import model.repository.alaloka.VagaRepository;

@Path("/vagas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class vagaController {

    @Inject
    private VagaRepository vagaRepository;

    @GET
    public List<Vaga> listarVagas() {
        return vagaRepository.consultarTodos(); 
    }

    @GET
    @Path("/{id}")
    public Response buscarVagaPorId(@PathParam("id") int id) {
        Vaga vaga = vagaRepository.consultarPorId(id);
        if (vaga != null) {
            return Response.ok(vaga).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build(); 
        }
    }

    @POST
    public Response cadastrarVaga(Vaga vaga, @Context UriInfo uriInfo) {
        Vaga novaVaga = vagaRepository.salvar(vaga);
        URI uri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(novaVaga.getId())).build();
        return Response.created(uri).entity(novaVaga).build(); 
    }

    @PUT
    @Path("/{id}")
    public Response atualizarVaga(@PathParam("id") int id, Vaga vaga) {
        vaga.setId(id); 
        if (vagaRepository.alterar(vaga)) {
            return Response.ok().build(); 
        } else {
            return Response.status(Response.Status.NOT_FOUND).build(); 
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletarVaga(@PathParam("id") int id) {
        if (vagaRepository.excluir(id)) {
            return Response.ok().build(); 
        } else {
            return Response.status(Response.Status.NOT_FOUND).build(); 
        }
    }
}
