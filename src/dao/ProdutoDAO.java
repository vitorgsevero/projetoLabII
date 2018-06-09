package dao;

import model.Produtos;
import java.util.List;

public interface ProdutoDAO {
    
    public void cadastrarProdutos(Produtos pruduto);
    public void removerProdutos(Produtos pruduto);
    public void atualizarDados(Produtos pruduto);
    public List<Produtos> listar();
    public Produtos procurarPorId(int id);
    //Adicionado
    public Produtos procurarPorCpf(String codProduto);
    public List<Produtos> listarPorNome(String nomeProduto);
    public List<Produtos> listarPorMaiorPreco(double precoProduto);
}
