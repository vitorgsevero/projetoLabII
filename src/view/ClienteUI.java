package view;

import dao.BD.ClienteDaoBD;
import dao.ClienteDAO;
import java.util.List;
import model.Clientes;
import repositorio.RepositorioClientes;
import util.Console;
import util.DateUtil;

public class ClienteUI {

    private ClienteDaoBD clienteDao;

    public ClienteUI() {
        clienteDao = new ClienteDaoBD();
    }

    public void menuCliente() {
        int op = 0;
        do {

            try {
                op = Console.scanInt("\nBem-vindo ao Menu de Cliente! Informe uma opção: \n1) Cadastrar clientes \n2) Mostrar todos clientes \n3) Buscar clientes por nome \n4) Remover clientes \n5) Atualizar dados \n0) Voltar para o menu anterior ");

                switch (op) {

                    case 1:
                        this.cadastrarClientes();
                        break;

                    case 2:
                        this.mostrarClientes();
                        break;

                    case 3:
                        this.consultarClientesPorNome();
                        break;

                    case 4:
                        this.removerClientes();
                        break;

                    case 5:
                        this.atualizar();

                    case 0:
                        System.out.println("\nVoltando para o menu principal...");
                        break;

                    default:
                        System.out.println("Opção inválida!");
                }

            } catch (Exception e) {
                System.out.println("Não foi possível acessar as opções do Menu de Clientes! Por favor, informe uma opção válida.");
            }
        } while (op != 0);

    }

    public void cadastrarClientes() {

        try {
            System.out.println("\nCadastrando Clientes...");

            String nome = Console.scanString("Informe o nome do cliente: ");
            String cpf = Console.scanString("Informe o CPF do cliente: ");
            String email = Console.scanString("Informe o endereço de e-mail do cliente: ");
            String numConta = Console.scanString(nome.toUpperCase() + ", Informe o número da sua conta para cadastrar: ");
            // double saldoConta = Console.scanDouble(nome.toUpperCase() + ", Informe o saldo da sua conta: ");
            String dataString = Console.scanString("Informe a sua data de nascimento: ");

            clienteDao.cadastrarClientes(new Clientes(nome, cpf, email, numConta, DateUtil.stringToDate(dataString)));

        } catch (Exception e) {
            System.out.println("Não foi possível cadastrar cliente. Algum valor inválido foi informado.");

        }

    }

    public void mostrarClientes() {
        List<Clientes> listaClientes = clienteDao.listar();
        mostrarClientes(listaClientes);
    }

    public void removerClientes() {

        String cpfCliente = Console.scanString("Informe o CPF para encontrar o cliente que deseja remover: ");

        Clientes clientes = clienteDao.procurarPorCpf(cpfCliente);

        if (UIUtil.getConfirmacao("Você tem certeza que deseja excluir o cliente?")) {
            clienteDao.removerClientes(clientes);
            System.out.println("Cliente excluído com sucesso!");
        } else {
            System.out.println("Operação cancelada!");
        }

    }

    public void atualizar() {

        String cpfCliente = Console.scanString("CPF do cliente a ser alterado: ");

        Clientes clientes = clienteDao.procurarPorCpf(cpfCliente);

        System.out.println("Informe os dados que deseja alterar, caso não queira, deixe em branco.");

        String nomeCliente = Console.scanString("Nome: ");
        String dataString = Console.scanString("Data de Nascimento: ");

        if (!nomeCliente.isEmpty()) { // Se o nome não estiver em branco, o nome é atualizado
            clientes.setNomeCliente(nomeCliente);
        }
        if (!dataString.isEmpty()) { // Se a data de nascimento não estiver em branco, o data é atualizada
            clientes.setDataNascimento(DateUtil.stringToDate(dataString));
        }

        clienteDao.atualizarDados(clientes);
        System.out.println("Dados atualizados com sucesso!");

    }

    private void consultarClientesPorNome() {
        String nomeCliente = Console.scanString("Informe o nome do cliente que deseja buscar: ");
        List<Clientes> listaClientes = clienteDao.listarPorNome(nomeCliente);
        this.mostrarClientes(listaClientes);

    }

    private void mostrarClientes(List<Clientes> listaClientes) {
        if (listaClientes.isEmpty()) {
            System.out.println("Nenhum cliente encontrado!");
        } else {
            System.out.println("-----------------------------\n");
            System.out.println(String.format("%-10s", "CPF") + "\t"
                    + String.format("%-20s", "|NOME") + "\t"
                    + String.format("%-20s", "|DATA DE NASCIMENTO"));
            for (Clientes clientes : listaClientes) {
                System.out.println(String.format("%-10s", clientes.getCpfCliente()) + "\t"
                        + String.format("%-20s", "|" + clientes.getNomeCliente()) + "\t"
                        + String.format("%-20s", "|" + DateUtil.dateToString(clientes.getDataNascimento())));
            }
        }
    }

}
