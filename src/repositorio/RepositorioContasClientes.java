package repositorio;

import java.util.ArrayList;
import java.util.List;
import model.ContaCliente;

public class RepositorioContasClientes {
    
    private List<ContaCliente> contas;
    private static RepositorioContasClientes instance = null;
    
    private RepositorioContasClientes(){
        contas = new ArrayList<ContaCliente>();
    }
    
    public static RepositorioContasClientes getinstance(){
        if (instance == null) instance = new RepositorioContasClientes();
        return instance;
    }    
    
    public boolean add(ContaCliente contas) {
        return (contas.add(contas));
    }
    
    public boolean estaVazio(){
        return contas.isEmpty();
    }

    public List<ContaCliente> getContas() {
        return contas;
    }

    public boolean procuraContas(String numConta) {
        for (ContaCliente contas : contas) {
            if (contas.getNumConta().equalsIgnoreCase(numConta)) {
                return true;
            }
        }
        return false;
    }
      

    public ContaCliente buscarContas(String numConta) {
        for (ContaCliente contas : contas) {
            if (contas.getNumConta().equalsIgnoreCase(numConta)) {
                return contas;
           }
        }
        return null;
    }
    
    
}
