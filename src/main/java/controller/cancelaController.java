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
import model.entity.Cancela;
import service.CancelaService;

@Path("/cancelas")
public class cancelaController {

    private final CancelaService service = new CancelaService();

    @GET
    @Path("/todas")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cancela> listarCancelas() {
        return service.consultarTodasCancelas();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Cancela buscarCancelaPorId(@PathParam("id") int id) {
        return service.consultarCancelaPorId(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Cancela cadastrarCancela(Cancela cancela) throws AlalokaException {
        return service.salvarCancela(cancela);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean atualizarCancela(@PathParam("id") int id, Cancela cancela) throws AlalokaException {
        cancela.setIdCancela(id);
        return service.atualizarCancela(cancela);
    }

    @DELETE
    @Path("/{id}")
    public boolean deletarCancela(@PathParam("id") int id) throws AlalokaException {
        return service.excluirCancela(id);
    }
}
