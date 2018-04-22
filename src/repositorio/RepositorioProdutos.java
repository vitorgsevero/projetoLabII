package repositorio;

import java.util.ArrayList;
import java.util.List;
import model.Produtos;

public class RepositorioProdutos {
    private List<Produtos> produtos;
    private static RepositorioProdutos instance = null;
    
private RepositorioProdutos(){
        produtos = new ArrayList<Produtos>();
}
    
    public static RepositorioProdutos getinstance(){
        if (instance == null) instance = new RepositorioProdutos();
        return instance;
    }    
    
    public boolean add(Produtos produto) {
        return (produtos.add(produto));
    }
    
    public boolean estaVazio(){
        return produtos.isEmpty();
    }

    public List<Produtos> getContas() {
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
         
}
