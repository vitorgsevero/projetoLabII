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

    public List<Clientes> getClientes() {
        return clientes;
    }


    public void buscarClientes(String cpf) { // Retorna os dados do cliente caso o CPF esteja cadastrado
        
            for (Clientes clientes : RepositorioClientes.getInstance().getClientes()) {
                if(clientes.getCpfCliente().equalsIgnoreCase(cpf)){

                System.out.println("\n------------------------");
                System.out.println("Nome: " + clientes.getNomeCliente().toUpperCase());
                System.out.println("CPF: " + clientes.getCpfCliente());
                System.out.println("E-mail: " + clientes.getEmailCliente().toLowerCase());
                System.out.println("Nº de conta: " + clientes.getNumConta());
                System.out.println("Saldo: " + clientes.getSaldoConta());
                System.out.println("------------------------");
                }else{
                    System.out.println("Nenhum cliente encontrado...");
                }

            }
    
    }
    
    public boolean clienteIgual (String cpf) {  //Verifica se o CPF informado já foi cadastrado
        for (Clientes clientes : clientes) {
            if (clientes.getCpfCliente().equalsIgnoreCase(cpf)) {
               return true;
            }
        }
        return false;
    }   
    
    
    public boolean contaIgual (String numConta) { // Verifica se a conta informada já foi cadastrada
        for (Clientes clientes : clientes) {
            if (clientes.getCpfCliente().equalsIgnoreCase(numConta)) {
               return true;
            }
        }
        return false;
    } 
    
    
    
}    
