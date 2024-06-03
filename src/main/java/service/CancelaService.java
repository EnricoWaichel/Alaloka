package service;

import java.util.List;

import exception.AlalokaException;
import model.entity.Cancela;
import model.repository.alaloka.CancelaRepository;

public class CancelaService {

    private CancelaRepository cancelaRepository = new CancelaRepository();

    public Cancela salvarCancela(Cancela novaCancela) throws AlalokaException {
        validarCamposObrigatoriosCancela(novaCancela);
        return cancelaRepository.salvar(novaCancela);
    }

    public boolean atualizarCancela(Cancela cancelaEditada) throws AlalokaException {
        validarCamposObrigatoriosCancela(cancelaEditada);
        return cancelaRepository.alterar(cancelaEditada);
    }

    public boolean excluirCancela(int id) throws AlalokaException {
        return cancelaRepository.excluir(id);
    }

    public Cancela consultarCancelaPorId(int id) {
        return cancelaRepository.consultarPorId(id);
    }

    public List<Cancela> consultarTodasCancelas() {
        return cancelaRepository.consultarTodos();
    }

    private void validarCamposObrigatoriosCancela(Cancela cancela) throws AlalokaException {
        String mensagemValidacao = "";
        if (cancela.getStatus() == null || cancela.getStatus().isEmpty()) {
            mensagemValidacao += " - informe o status da cancela \n";
        }
        if (!mensagemValidacao.isEmpty()) {
            throw new AlalokaException("Preencha o(s) seguinte(s) campo(s) \n " + mensagemValidacao);
        }
    }
}

