package view;

import model.Clientes;
import model.ContaCliente;
import repositorio.RepositorioClientes;
import util.Console;

public class ClienteUI {
    
    public void menuCliente() {
        int op = 0;
        do {

            try {
                op = Console.scanInt("\nBem-vindo! Informe uma opção: \n1) Cadastrar clientes \n2) Exibir dados de clientes cadastrados \n3) Buscar Clientes \n0)Voltar para o menu anterior ");

                switch (op) {

                    case 1:
                        this.cadastrarClientes();
                        break;

                    case 2:
                        this.listarClientes();
                        break;
                    
                    case 3:
                        this.buscarClientes();
                     break;

                    case 0:
                        System.out.println("\nVoltando para o menu principal...");
                        break;

                    default:
                        System.out.println("Opção inválida!");
                }

            } catch (Exception e) {
                System.out.println("Não foi possível acessar as opções do Menu de Clientes! Por favor, informe uma opção válida.");
            }

        } while (op!=0);

    }

    public void cadastrarClientes() {

        try {

            System.out.println("\nCadastrando Clientes...");
            String nome = Console.scanString("Informe o nome do cliente: ");
            String cpf = Console.scanString("Informe o CPF do cliente: ");
            String email = Console.scanString("Informe o endereço de e-mail do cliente: ");
            String numConta = Console.scanString(nome.toUpperCase() + ", Informe o número da sua conta para cadastrar: ");
            double saldoConta = Console.scanDouble(nome.toUpperCase() + ", Informe o saldo da sua conta: ");

            RepositorioClientes.getInstance().add(new Clientes(nome, cpf, email, numConta, saldoConta));

        } catch (Exception e) {

            System.out.println("Não foi possível cadastrar o cliente, algum valor inválido foi informado.");

        }

    }

    public void listarClientes() {

        if (RepositorioClientes.getInstance().estaVazio()) {
            System.out.println("Nenhum cliente cadastrado...");
        } else {
            System.out.println("\nClientes cadastrados: ");
            for (Clientes clientes : RepositorioClientes.getInstance().getClientes()) {
                System.out.println("\nInformações do(a) Cliente: " + clientes.getNomeCliente());
                System.out.println("\n------------------------");
                System.out.println("Nome: " + clientes.getNomeCliente().toUpperCase());
                System.out.println("CPF: " + clientes.getCpfCliente());
                System.out.println("E-mail: " + clientes.getEmailCliente().toLowerCase());
                System.out.println("Nº de conta: " + clientes.getNumConta());
                System.out.println("Saldo: " + clientes.getSaldoConta());
                System.out.println("------------------------");

            }
        }

    }
    
    public void buscarClientes(){
        
        System.out.println("\nBUSCANDO CLIENTE: ");
        String cpf = Console.scanString("Informe o CPF do cliente: ");
        RepositorioClientes.getInstance().buscarClientes(cpf);
  
    }
    
}
