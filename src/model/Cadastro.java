package model;

import java.util.ArrayList;
import util.Console;

public class Cadastro {

    ArrayList<Clientes> listaClientes = new ArrayList<>();
    ArrayList<ContaCliente> listaContas = new ArrayList<>();
    ArrayList<Produtos> listaProdutos = new ArrayList<>();

    public void cadastrarCliente() {

        try {

            System.out.println("\nCadastrando Clientes...");
            String nome = Console.scanString("Informe o nome do cliente: ");
            String cpf = Console.scanString("Informe o CPF do cliente: ");
            String email = Console.scanString("Informe o endereço de e-mail do cliente: ");
            Clientes clientes = new Clientes(nome, cpf, email);
            listaClientes.add(clientes);

            int numConta = Console.scanInt(clientes.getNomeCliente() + ", Informe o número da sua conta para cadastrar: ");
            double saldoConta = Console.scanDouble(clientes.getNomeCliente() + ", Informe o saldo da sua conta: ");
            ContaCliente conta = new ContaCliente(numConta, saldoConta);
            listaContas.add(conta);

        } catch (Exception e) {

            System.err.println("Não foi possível cadastrar o cliente, algum valor inválido foi informado.");

        }

    }

    public void exibirDadosCliente() {

        if (listaClientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado!");
        } else {

            for (Clientes c : listaClientes) { // varrendo o arraylist com os clientes

                System.out.println("\nNome do cliente: " + c.getNomeCliente());
                System.out.println("CPF do cliente: " + c.getCpfCliente());
                System.out.println("Endereço de e-mail: " + c.getEmailCliente());

            }

        }
    }

    public void exibirDadosConta() {
        if (listaContas.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada!");
        }
        for (ContaCliente cc : listaContas) { // varrendo o arraylist com as contas

            System.out.println("\nNúmero da conta: " + cc.getNumConta());
            System.out.println("Saldo da conta: " + cc.getSaldoConta());

        }
    }

    public void cadastrarProduto() {

        try {

            System.out.println("\nCadastrando produtos...");
            String nomeProduto = Console.scanString("Informe o nome do produto: ");
            String codProduto = Console.scanString("Informe o código do produto: ");
            double precoProduto = Console.scanDouble("Informe o preço do produto: ");
            Produtos produtos = new Produtos(nomeProduto, codProduto, precoProduto);
            listaProdutos.add(produtos);

        } catch (Exception e) {
            System.err.println("Não foi possível cadastrar o produto, algum valor inválido foi informado.");
        }

    }

    public void exibirProduto() {
        if (listaProdutos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado!");
        } else {
            for (Produtos p : listaProdutos) {
                System.out.println("\nNome do produto: " + p.getNomeProduto());
                System.out.println("Código do produto: " + p.getCodProduto());
                System.out.println("Preço do produto: " + p.getPrecoProduto());
            }

        }
    }

}
