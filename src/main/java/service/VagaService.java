package service;

import java.util.List;

import exception.AlalokaException;
import model.entity.Vaga;
import model.repository.alaloka.VagaRepository;

public class VagaService {

    private VagaRepository vagaRepository = new VagaRepository();

    public Vaga salvarVaga(Vaga novaVaga) throws AlalokaException {
        validarCamposObrigatoriosVaga(novaVaga);
        return vagaRepository.salvar(novaVaga);
    }

    public boolean atualizarVaga(Vaga vagaEditada) throws AlalokaException {
        validarCamposObrigatoriosVaga(vagaEditada);
        return vagaRepository.alterar(vagaEditada);
    }

    public boolean excluirVaga(int id) throws AlalokaException {
        if (!vagaRepository.consultarPorId(id).isDisponivel()) {
            throw new AlalokaException("Vaga não pode ser excluída, pois está ocupada.");
        }
        return vagaRepository.excluir(id);
    }

    public Vaga consultarVagaPorId(int id) {
        return vagaRepository.consultarPorId(id);
    }

    public List<Vaga> consultarTodasVagas() {
        return vagaRepository.consultarTodos();
    }

    private void validarCamposObrigatoriosVaga(Vaga vaga) throws AlalokaException {
        String mensagemValidacao = "";
        if (vaga.getNumeroVaga() == null || vaga.getNumeroVaga() <= 0) {
            mensagemValidacao += " - informe o número da vaga \n";
        }
        if (vaga.getTipoVaga() == null || vaga.getTipoVaga().isEmpty()) {
            mensagemValidacao += " - informe o tipo da vaga \n";
        }
        if (!mensagemValidacao.isEmpty()) {
            throw new AlalokaException("Preencha o(s) seguinte(s) campo(s) \n " + mensagemValidacao);
        }
    }
}

