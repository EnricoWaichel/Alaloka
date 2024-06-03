package service;

import java.util.List;

import exception.AlalokaException;
import model.entity.Cliente;
import model.repository.alaloka.ClienteRepository;

public class ClienteService {

    private ClienteRepository clienteRepository = new ClienteRepository();

    public Cliente salvarCliente(Cliente novoCliente) throws AlalokaException {
        validarCamposObrigatoriosCliente(novoCliente);
        return clienteRepository.salvar(novoCliente);
    }

    public boolean atualizarCliente(Cliente clienteEditado) throws AlalokaException {
        validarCamposObrigatoriosCliente(clienteEditado);
        return clienteRepository.alterar(clienteEditado);
    }

    public boolean excluirCliente(int id) throws AlalokaException {
        return clienteRepository.excluir(id);
    }

    public Cliente consultarClientePorId(int id) {
        return clienteRepository.consultarPorId(id);
    }

    public List<Cliente> consultarTodosClientes() {
        return clienteRepository.consultarTodos();
    }

    private void validarCamposObrigatoriosCliente(Cliente cliente) throws AlalokaException {
        String mensagemValidacao = "";
        if (cliente.getNome() == null || cliente.getNome().isEmpty()) {
            mensagemValidacao += " - informe o nome \n";
        }
        if (cliente.getCpf() == null || cliente.getCpf().isEmpty() || cliente.getCpf().length() != 11) {
            mensagemValidacao += " - informe o CPF \n";
        }
        if (cliente.getTelefone() == null || cliente.getTelefone().isEmpty()) {
            mensagemValidacao += " - informe o telefone \n";
        }
        if (!mensagemValidacao.isEmpty()) {
            throw new AlalokaException("Preencha o(s) seguinte(s) campo(s) \n " + mensagemValidacao);
        }
    }
}

