package view;

import model.Produtos;
import repositorio.RepositorioProdutos;
import util.Console;

public class ProdutoUI {
    
// falta adicionar o menu de produtos    
    
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
    
    
    
    
}
