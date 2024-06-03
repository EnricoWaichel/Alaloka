package service;

import java.util.List;

import exception.AlalokaException;
import model.entity.Carro;
import model.repository.alaloka.CarroRepository;

public class CarroService {

    private CarroRepository carroRepository = new CarroRepository();

    public Carro salvarCarro(Carro novoCarro) throws AlalokaException {
        validarCamposObrigatoriosCarro(novoCarro);
        return carroRepository.salvar(novoCarro);
    }

    public boolean atualizarCarro(Carro carroEditado) throws AlalokaException {
        validarCamposObrigatoriosCarro(carroEditado);
        return carroRepository.alterar(carroEditado);
    }

    public boolean excluirCarro(int id) throws AlalokaException {
        return carroRepository.excluir(id);
    }

    public Carro consultarCarroPorId(int id) {
        return carroRepository.consultarPorId(id);
    }

    public List<Carro> consultarTodosCarros() {
        return carroRepository.consultarTodos();
    }

    private void validarCamposObrigatoriosCarro(Carro carro) throws AlalokaException {
        String mensagemValidacao = "";
        if (carro.getPlaca() == null || carro.getPlaca().isEmpty()) {
            mensagemValidacao += " - informe a placa do carro \n";
        }
        if (carro.getTipoVeiculo() == null || carro.getTipoVeiculo().isEmpty()) {
            mensagemValidacao += " - informe o tipo do veículo \n";
        }
        if (!mensagemValidacao.isEmpty()) {
            throw new AlalokaException("Preencha o(s) seguinte(s) campo(s) \n " + mensagemValidacao);
        }
    }
}
