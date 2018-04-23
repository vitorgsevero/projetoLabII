package view;

import model.Produtos;
import repositorio.RepositorioProdutos;
import util.Console;

public class ProdutoUI {
    
    
    public void menuProdutos() {
        int op = 0;
        do {

            try {
                op = Console.scanInt("\nBem-vindo ao Menu de Produtos! Informe uma opção: \n1) Cadastrar Produtos \n2) Listar Produtos \n3) Buscar Produtos \n0)Voltar para o menu principal");

                switch (op) {

                    case 1:
                        this.cadastrarProdutos();
                        break;

                    case 2:
                        this.listarProdutos();
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
    
    
    
    public void cadastrarProdutos(){
        
         try {

            System.out.println("\nCadastrando Produtos...");
            String nomeProduto = Console.scanString("Informe o nome do Produto: ");
            String codigoProduto = Console.scanString("Informe o código do Produto: ");
            double precoProduto = Console.scanDouble("Informe o preço do Produto: ");
            RepositorioProdutos.getInstance().add(new Produtos(nomeProduto, codigoProduto, precoProduto));


        } catch (Exception e) {

            System.err.println("Não foi possível cadastrar o cliente, algum valor inválido foi informado.");

        }
    }
    
    public void listarProdutos(){
        if(RepositorioProdutos.getInstance().estaVazio()){
            System.out.println("Nenhum produto cadastrado...");
        }else{
            System.out.println("\nProdutos cadastrados: ");
            for(Produtos produtos : RepositorioProdutos.getInstance().getProdutos()){
                System.out.println("\nCódigo do Produto: " + produtos.getCodProduto());
                System.out.println("Nome do Produto: " + produtos.getNomeProduto().toUpperCase());
                System.out.println("Preço do Produto: " + produtos.getPrecoProduto());
            }
        }
    }
    
    public void buscarClientes(){
        
        System.out.println("\nBUSCANDO PRODUTO: ");
        String nomeProduto = Console.scanString("Informe o nome do produto: ");
        RepositorioProdutos.getInstance().buscarProdutos(nomeProduto);
  
    }
    
    
    
    
}
