package model;

import java.util.ArrayList;
import util.Console;

public class Cadastro {

    ArrayList<Clientes> listaClientes = new ArrayList<>();
    ArrayList<ContaCliente> listaContas = new ArrayList<>();
    ArrayList<Produtos> listaProdutos = new ArrayList<>();


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
