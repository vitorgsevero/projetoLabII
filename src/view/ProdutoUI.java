package view;

import dao.BD.ProdutoDaoBD;
import java.util.List;
import model.Clientes;
import model.Produtos;
import model.VendaProduto;
import repositorio.RepositorioClientes;
import repositorio.RepositorioOperacaoMonetizacao;
import repositorio.RepositorioProdutos;
import repositorio.RepositorioVendaProduto;
import util.Console;
import util.DateUtil;

public class ProdutoUI {

    private ProdutoDaoBD produtoDao;

    public ProdutoUI() {
        produtoDao = new ProdutoDaoBD();
    }

    public void menuProdutos() {
        int op = 0;
        do {

            try {
                op = Console.scanInt("\nBem-vindo ao Menu de Produtos! Informe uma opção: \n1) Cadastrar Produtos \n2) Listar Todos Produtos \n3) Listar por maior preço \n4) Listar por nome \n5) Comprar Produtos \n6) Remover Produtos \n7) Atualizar Produtos \n0) Voltar para o menu principal");

                switch (op) {

                    case 1:
                        this.cadastrarProdutos();
                        break;

                    case 2:
                        this.mostrarProdutos();
                        break;

                    case 3:
                        this.listarPorPreco();
                        break;
                    case 4:
                        this.buscarProdutos();
                        break;
                    case 5:
                        this.comprarProdutos();
                        break;
                    case 6:
                        this.removerProdutos();
                        break;
                    case 7:
                        this.atualizar();
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
            if (codigoProduto.isEmpty() || nomeProduto.isEmpty() || precoProduto < 0) {
                System.out.println("Todos os campos são de preenchimento obrigatório. Tente cadastrar novamente sem valores em branco!");
            } else {
                produtoDao.cadastrarProdutos(new Produtos(codigoProduto, nomeProduto, precoProduto));
            }

        } catch (Exception e) {

            System.out.println("Não foi possível cadastrar o produto, algum valor inválido foi informado.");

        }
    }

    public void atualizar() {

        String codigoProduto = Console.scanString("Informe o código do produto que você deseja alterar: ");
        if (codigoProduto.isEmpty()) {
            System.out.println("Todos os campos são de preenchimento obrigatório. Tente cadastrar novamente sem valores em branco!");
        } else {
            
            Produtos produto = produtoDao.procurarPorCodProduto(codigoProduto);

            System.out.println("Informe os dados que deseja alterar, caso não queira, deixe em branco.");

            String nomeProduto = Console.scanString("Nome do Produto: ");
            double precoProduto = Console.scanDouble("Preço do Produto: ");

            if (!nomeProduto.isEmpty()) { // Se o nome não estiver em branco, o nome é atualizado
                produto.setNomeProduto(nomeProduto);
            }
            if (precoProduto > 0) {
                produto.setPrecoProduto(precoProduto);
            }

            produtoDao.atualizarDados(produto);
            System.out.println("Dados atualizados com sucesso!");
        }
    }

    public void removerProdutos() {

        String codProduto = Console.scanString("Informe o Código do Produto para encontrar o produto que deseja remover: ");
        if (codProduto.isEmpty()) {
            System.out.println("Todos os campos são de preenchimento obrigatório. Tente cadastrar novamente sem valores em branco!");
        } else {
            Produtos produto = produtoDao.procurarPorCodProduto(codProduto);

            if (UIUtil.getConfirmacao("Você tem certeza que deseja excluir o produto?")) {
                produtoDao.removerProdutos(produto);
                System.out.println("Produto excluído com sucesso!");
            } else {
                System.out.println("Operação cancelada!");
            }
        }
    }

    public void mostrarProdutos() {
        List<Produtos> listaProdutos = produtoDao.listar();
        mostrarProdutos(listaProdutos);
    }

    public void buscarProdutos() {
        String nomeProduto = Console.scanString("\nInforme o nome do produto: ");
        List<Produtos> listaProdutos = produtoDao.listarPorNome(nomeProduto);
        mostrarProdutos(listaProdutos);

    }

    public void listarPorPreco() {
        List<Produtos> listaProdutos = produtoDao.listarPorMaiorPreco();
        mostrarProdutos(listaProdutos);
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

    private void mostrarProdutos(List<Produtos> listaProdutos) {
        if (listaProdutos.isEmpty()) {
            System.out.println("Nenhum produto encontrado!");
        } else {
            System.out.println("------------------------------------------------\n");
            System.out.println(String.format("%-10s", "Código") + "\t"
                    + String.format("%-20s", "|Nome") + "\t"
                    + String.format("%-20s", "|Preço"));
            for (Produtos produto : listaProdutos) {
                System.out.println(String.format("%-10s", produto.getCodProduto()) + "\t"
                        + String.format("%-20s", "|" + produto.getNomeProduto()) + "\t"
                        + String.format("%-20s", "|" + produto.getPrecoProduto()));
            }
        }
    }

}
