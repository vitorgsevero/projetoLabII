package repositorio;

import java.util.ArrayList;
import java.util.List;
import model.Clientes;

public class RepositorioClientes {
    
    private List<Clientes> clientes;
    private static RepositorioClientes instance = null;
    
    private RepositorioClientes(){
        clientes = new ArrayList<Clientes>();
    }
    
    public static RepositorioClientes getInstance() {
        if(instance == null) instance = new RepositorioClientes();
        return instance;
    }
    
    public boolean add(Clientes cliente) {
        return (clientes.add(cliente));
    }
    
    public boolean estaVazio(){
        return clientes.isEmpty();
    }

    public List<Clientes> getPacientes() {
        return clientes;
    }

    public boolean procuraCliente(String cpf) {
        for (Clientes clientes : clientes) {
            if (clientes.getCpfCliente().equalsIgnoreCase(cpf)) {
                return true;
            }
        }
        return false;
    }

    public Clientes buscarClientes(String cpf) {
        for (Clientes clientes : clientes) {
            if (clientes.getCpfCliente().equalsIgnoreCase(cpf)) {
                return clientes;
           }
        }
        return null;
    }
    
}
