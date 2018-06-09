package view;

import dao.BD.ProdutoDaoBD;
import model.Clientes;
import model.Produtos;
import model.VendaProduto;
import repositorio.RepositorioClientes;
import repositorio.RepositorioOperacaoMonetizacao;
import repositorio.RepositorioProdutos;
import repositorio.RepositorioVendaProduto;
import util.Console;

public class ProdutoUI {

    private ProdutoDaoBD produtoDao;

    public ProdutoUI() {
        produtoDao = new ProdutoDaoBD();
    }

    public void menuProdutos() {
        int op = 0;
        do {

            try {
                op = Console.scanInt("\nBem-vindo ao Menu de Produtos! Informe uma opção: \n1) Cadastrar Produtos \n2) Listar Produtos \n3) Buscar Produtos \n4) Comprar Produtos \n5) Remover Produtos \n0) Voltar para o menu principal");

                switch (op) {

                    case 1:
                        this.cadastrarProdutos();
                        break;

                    case 2:
                        this.listarProdutos();
                        break;

                    case 3:
                        this.buscarProdutos();
                        break;
                    case 4:
                        this.comprarProdutos();
                        break;
                    case 5:
                        this.removerProdutos();
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

        } while (op != 0);

    }

    public void cadastrarProdutos() {

        try {

            System.out.println("\nCadastrando Produtos...");

            String codigoProduto = Console.scanString("Informe o código do Produto: ");
            String nomeProduto = Console.scanString("Informe o nome do Produto: ");
            double precoProduto = Console.scanDouble("Informe o preço do Produto: ");

            produtoDao.cadastrarProdutos(new Produtos(codigoProduto, nomeProduto, precoProduto));

        } catch (Exception e) {

            System.out.println("Não foi possível cadastrar o produto, algum valor inválido foi informado.");

        }
    }

    public void removerProdutos() {

        String codProduto = Console.scanString("Informe o Código do Produto para encontrar o produto que deseja remover: ");

        Produtos produto = produtoDao.procurarPorCodProduto(codProduto);

        if (UIUtil.getConfirmacao("Você tem certeza que deseja excluir o produto?")) {
            produtoDao.removerProdutos(produto);
            System.out.println("Produto excluído com sucesso!");
        } else {
            System.out.println("Operação cancelada!");
        }
    }

    public void listarProdutos() {

        try {
            if (RepositorioProdutos.getInstance().estaVazio()) {
                System.out.println("\nNenhum produto cadastrado...");
            } else {
                System.out.println("\nProdutos cadastrados: ");
                for (Produtos produtos : RepositorioProdutos.getInstance().getProdutos()) {
                    System.out.println("------------------------");
                    System.out.println("Código do Produto: " + produtos.getCodProduto());
                    System.out.println("Nome do Produto: " + produtos.getNomeProduto().toUpperCase());
                    System.out.println("Preço do Produto: " + produtos.getPrecoProduto() + " R$");
                    System.out.println("------------------------");
                }
            }
        } catch (Exception e) {
            System.out.println("Não foi possível listar os produtos!");
        }

    }

    public void buscarProdutos() {

        System.out.println("\nBuscando Produto: ");
        String codigoProduto = Console.scanString("Informe o código do produto: ");
        RepositorioProdutos.getInstance().buscarProdutos(codigoProduto);

    }

    public void comprarProdutos() {

        try {
            String codigoProduto = Console.scanString("Informe o código do produto desejado: ");
            // Verificando se o código do produto existe e foi informado corretamente    
            for (Produtos produtos : RepositorioProdutos.getInstance().getProdutos()) {
                if (produtos.getCodProduto().equalsIgnoreCase(codigoProduto)) {
                    System.out.println("Produto encontado!");

                    System.out.println("------------------------");
                    System.out.println("Nome do Produto: " + produtos.getNomeProduto().toUpperCase());
                    System.out.println("Código do Produto: " + produtos.getCodProduto());
                    System.out.println("Preço do Produto: " + produtos.getPrecoProduto() + " R$");
                    System.out.println("------------------------");
                } else {
                    RepositorioProdutos.getInstance().estaVazio();
                    System.out.println("Produto não encontrado!");
                }

                String cpf = Console.scanString("Informe o CPF do cliente: ");
                // Verificando se o CPF informado existe e caso exista ele compra o produto e desconta o valor do produto
                for (Clientes clientes : RepositorioClientes.getInstance().getClientes()) {
                    if (clientes.getCpfCliente().equalsIgnoreCase(cpf)) {

                        System.out.println("\nCliente encontrado!");

                        System.out.println("------------------------");
                        System.out.println("Nome: " + clientes.getNomeCliente().toUpperCase());
                        System.out.println("CPF: " + clientes.getCpfCliente());
                        System.out.println("Nº de conta: " + clientes.getNumConta());
                        System.out.println("Saldo: " + clientes.getSaldoConta() + " R$");
                        System.out.println("------------------------");

                        if (clientes.getSaldoConta() < produtos.getPrecoProduto()) {
                            System.out.println("\nSaldo insuficiente...");

                        } else {

                            String codVenda = Console.scanString("Informe o código da venda: ");
                            int qtdeProdutos = Console.scanInt("Informe a quantidade do produto " + produtos.getNomeProduto() + ": ");
                            double totalVenda = qtdeProdutos * produtos.getPrecoProduto();

                            RepositorioVendaProduto.getInstance().add(new VendaProduto(codVenda, qtdeProdutos, totalVenda));

                            System.out.println("Parabéns, você comprou o(a) " + produtos.getNomeProduto() + "!");
                            System.out.println("Total da Venda: " + totalVenda);
                            System.out.println("Seu saldo anterior: " + clientes.getSaldoConta());

                            double novoSaldoConta = clientes.getSaldoConta() - produtos.getPrecoProduto();
                            clientes.setSaldoConta(novoSaldoConta);
                            System.out.println("Novo saldo: " + clientes.getSaldoConta());
                        }

                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Não foi possível comprar o produto desejado!");
        }

    }

}
