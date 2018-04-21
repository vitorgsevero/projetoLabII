package view;

import model.Clientes;
import model.ContaCliente;
import repositorio.RepositorioClientes;
import repositorio.RepositorioContasClientes;
import util.Console;

public class ClienteUI {
     
    
    

    

    public void cadastrarClientes(){
        try {

            System.out.println("\nCadastrando Clientes...");
            String nome = Console.scanString("Informe o nome do cliente: ");
            String cpf = Console.scanString("Informe o CPF do cliente: ");
            String email = Console.scanString("Informe o endereço de e-mail do cliente: ");
            Clientes clientes = new Clientes(nome, cpf, email);
            RepositorioClientes.getInstance().add(clientes);

            int numConta = Console.scanInt(clientes.getNomeCliente() + ", Informe o número da sua conta para cadastrar: ");
            double saldoConta = Console.scanDouble(clientes.getNomeCliente() + ", Informe o saldo da sua conta: ");
            ContaCliente contas = new ContaCliente(numConta, saldoConta);
            RepositorioContasClientes.getinstance().add(contas);

        } catch (Exception e) {

            System.err.println("Não foi possível cadastrar o cliente, algum valor inválido foi informado.");

        }

    }
    
}
