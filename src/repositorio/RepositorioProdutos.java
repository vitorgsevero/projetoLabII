package repositorio;

import java.util.ArrayList;
import java.util.List;
import model.Produtos;

public class RepositorioProdutos {

    private List<Produtos> produtos;
    private static RepositorioProdutos instance = null;
    
    private RepositorioProdutos() {
        produtos = new ArrayList<Produtos>();
    }
    
    public static RepositorioProdutos getInstance() {
        if (instance == null) {
            instance = new RepositorioProdutos();
        }
        return instance;
    }    
    
    public boolean add(Produtos produto) {
        return (produtos.add(produto));
    }
    
    public boolean estaVazio() {
        return produtos.isEmpty();
    }
    
    public List<Produtos> getProdutos() {
        return produtos;
    }
    
    public boolean procuraProdutos(String nomeProduto) {
        for (Produtos produtos : produtos) {
            if (produtos.getNomeProduto().equalsIgnoreCase(nomeProduto)) {
                return true;
            }
        }
        return false;
    }
    
    public void buscarProdutos(String codigoProduto) {
        for (Produtos produtos : RepositorioProdutos.getInstance().getProdutos()) {  
            if (produtos.getCodProduto().equalsIgnoreCase(codigoProduto)) {
                System.out.println("Produto encontado!");
                System.out.println("\nInformações do Produto: " + produtos.getNomeProduto());
                System.out.println("\n------------------------");
                System.out.println("Nome do Produto: " + produtos.getNomeProduto().toUpperCase());
                System.out.println("Código do Produto: " + produtos.getCodProduto());
                System.out.println("Preço do Produto: " + produtos.getPrecoProduto());
                System.out.println("------------------------");
            } else {
                System.out.println("Nenhum produto encontrado...");
            }
        }
    }
    
    public boolean produtoIgual(String codigoProduto) {
        for (Produtos produtos : produtos) {
            if (produtos.getCodProduto().equalsIgnoreCase(codigoProduto)) {
                return true;
            }
        }
        return false;
    }    
    
}
