package view;

import model.Clientes;
import model.ContaCliente;
import repositorio.RepositorioClientes;
import repositorio.RepositorioContasClientes;
import util.Console;

public class ClienteUI {
     
    
    

    

    public void cadastrarClientes(){
//        try {

            System.out.println("\nCadastrando Clientes...");
            String nome = Console.scanString("Informe o nome do cliente: ");
            String cpf = Console.scanString("Informe o CPF do cliente: ");
            String email = Console.scanString("Informe o endereço de e-mail do cliente: ");
            RepositorioClientes.getInstance().add(new Clientes(nome, cpf, email));



//        } catch (Exception e) {
//
//            System.err.println("Não foi possível cadastrar o cliente, algum valor inválido foi informado.");
//
//        }

    }
    
    
    public void listarClientes(){
        
        if(RepositorioClientes.getInstance().estaVazio()){
            System.out.println("Nenhum cliente cadastrado...");
        }else{
            for(Clientes clientes : RepositorioClientes.getInstance().getPacientes()){
                System.out.println("CPF:" + clientes.getCpfCliente());
                System.out.println("E-mail:" + clientes.getEmailCliente());
                System.out.println("Nome: " + clientes.getNomeCliente());
            }
        }
        
    }
    
    
    public void cadastrarContas(){
        
            String numConta = Console.scanString(RepositorioClientes.getInstance().getPacientes()+ ", Informe o número da sua conta para cadastrar: ");
            double saldoConta = Console.scanDouble(RepositorioClientes.getInstance().getPacientes() + ", Informe o saldo da sua conta: ");
            
            RepositorioContasClientes.getinstance().add(new ContaCliente(numConta, saldoConta));
    }
    
}
