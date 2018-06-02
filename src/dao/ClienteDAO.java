package dao;

import model.Clientes;
import java.util.List;

public interface ClienteDAO {
    
    public void salvar(Clientes cliente);
    public void deletar(Clientes cliente);
    public void atualizar(Clientes cliente);
    public List<Clientes> listar();
    public Clientes procurarPorId(int id);
    //Adicionado
    public Clientes procurarPorRg(String rg);
    public List<Clientes> listarPorNome(String nome);
    
}
