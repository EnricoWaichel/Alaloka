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
import model.entity.Vaga;
import service.VagaService;

@Path("/vagas")
public class vagaController {

	private final VagaService service = new VagaService();

	@GET
	@Path("/todas")
	public List<Vaga> consultarTodas(){
		 return service.consultarTodasVagas();
	}

    @GET
	@Path("/{id}")
	public Vaga consultarPorId(@PathParam("id") int id){
		 return service.consultarVagaPorId(id);
	}
	
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Vaga salvar (Vaga vaga) throws AlalokaException {
    	return service.salvarVaga(vaga);
    }

    @PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean atualizar(Vaga vagaEditada) throws AlalokaException{
		 return service.atualizarVaga(vagaEditada);
	}
    

    @DELETE
    @Path("/{id}")
    public boolean deletarVaga(@PathParam("id") int id) throws AlalokaException {
    	return service.excluirVaga(id);
    }
}
