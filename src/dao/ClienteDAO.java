package dao;

import model.Clientes;
import java.util.List;

public interface ClienteDAO {
    
    public void cadastrarClientes(Clientes cliente);
    public void removerClientes(Clientes cliente);
    public void atualizarDados(Clientes cliente);
    public List<Clientes> listar();
    public Clientes procurarPorId(int id);
    //Adicionado
    public Clientes procurarPorRg(String rg);
    public List<Clientes> listarPorNome(String nome);
    
}
