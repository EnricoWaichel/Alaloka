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

import model.entity.Carro;
import service.CarroService;

@Path("/carros")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class carroController {

	private final CarroService service = new CarroService();

	@GET
	@Path("/todos")
	public List<Carro> consultarTodos(){
		 return service.consultarTodosCarros();
	}

    @GET
	@Path("/{id}")
	public Carro consultarPorId(@PathParam("id") int id){
		 return service.consultarCarroPorId(id);
	}
	
    @POST
    public Carro salvar (Carro carro) throws AlalokaException {
    	return service.salvarCarro(carro);
    }

    @PUT
	public boolean atualizar(Carro carroEditado) throws AlalokaException{
		 return service.atualizarCarro(carroEditado);
	}
    

    @DELETE
    @Path("/{id}")
    public boolean deletarCarro(@PathParam("id") int id) throws AlalokaException {
    	return service.excluirCarro(id);
    }
}
