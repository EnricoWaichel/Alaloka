package controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import model.entity.Carro;
import model.repository.alaloka.CarroRepository;

import java.net.URI;
import java.util.List;

@Path("/carros")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class carroController {

    @Inject
    private CarroRepository carroRepository;

    @GET
    public List<Carro> listarCarros() {
        return carroRepository.consultarTodos();
    }

    @GET
    @Path("/{id}")
    public Response buscarCarroPorId(@PathParam("id") int id) {
        Carro carro = carroRepository.consultarPorId(id);
        if (carro != null) {
            return Response.ok(carro).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response cadastrarCarro(Carro carro, @Context UriInfo uriInfo) {
        Carro novoCarro = carroRepository.salvar(carro);
        URI uri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(novoCarro.getIdCarro())).build();
        return Response.created(uri).entity(novoCarro).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizarCarro(@PathParam("id") int id, Carro carro) {
        carro.setIdCarro(id); 
        if (carroRepository.alterar(carro)) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletarCarro(@PathParam("id") int id) {
        if (carroRepository.excluir(id)) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
