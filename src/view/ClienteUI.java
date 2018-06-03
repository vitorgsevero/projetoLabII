package view;

import dao.BD.ClienteDaoBD;
import dao.ClienteDAO;
import model.Clientes;
import repositorio.RepositorioClientes;
import util.Console;
import util.DateUtil;


public class ClienteUI {
    
  private ClienteDaoBD clienteDao;
  
 public ClienteUI() {
        clienteDao = new ClienteDaoBD();
    }

    public void menuCliente() {
        int op = 0;
        do {

          //  try {
                op = Console.scanInt("\nBem-vindo ao Menu de Cliente! Informe uma opção: \n1) Cadastrar clientes \n2) Listar clientes \n3) Buscar Clientes \n0) Voltar para o menu anterior ");

                switch (op) {

                    case 1:
                        this.cadastrarClientes();
                        break;

                    case 2:
                        this.listarClientes();
                        break;

                    case 3:
                        this.buscarClientes();
                        break;

                    case 0:
                        System.out.println("\nVoltando para o menu principal...");
                        break;

                    default:
                        System.out.println("Opção inválida!");
                }

           // } catch (Exception e) {
           /*     System.out.println("Não foi possível acessar as opções do Menu de Clientes! Por favor, informe uma opção válida.");*/
           // }

        } while (op != 0);

    }

    public void cadastrarClientes() {

        System.out.println("\nCadastrando Clientes...");

        

       /* if (RepositorioClientes.getInstance().clienteIgual(cpf)) {*/
            //System.out.println("CPF já cadastrado, informe outro CPF.");
        /*} else {*/

            String nome = Console.scanString("Informe o nome do cliente: ");
            String cpf = Console.scanString("Informe o CPF do cliente: ");
            String email = Console.scanString("Informe o endereço de e-mail do cliente: ");
            String numConta = Console.scanString(nome.toUpperCase() + ", Informe o número da sua conta para cadastrar: ");
           // double saldoConta = Console.scanDouble(nome.toUpperCase() + ", Informe o saldo da sua conta: ");
            String dataString = Console.scanString("Informe a sua data de nascimento: ");

           /* if (RepositorioClientes.getInstance().contaIgual(numConta)) {*/
                
                //System.out.println("Conta já cadastrada, informe outro número de conta.");
                
            /*} else {*/
                
                
             

               /* try {*/

                    clienteDao.cadastrarClientes(new Clientes(nome, cpf, email, numConta,  DateUtil.stringToDate(dataString))); 
                    
                    //RepositorioClientes.getInstance().add(new Clientes(nome, cpf, email, numConta, saldoConta));

                /*} catch (Exception e) {*/

                    //System.out.println("Não foi possível cadastrar o cliente, algum valor inválido foi informado.");

               // }

            //}

        //}
    }

    public void listarClientes() {

        if (RepositorioClientes.getInstance().estaVazio()) {
            System.out.println("\nNenhum cliente cadastrado...");
        } else {
            System.out.println("\nClientes cadastrados: ");
            for (Clientes clientes : RepositorioClientes.getInstance().getClientes()) {
                System.out.println("\n------------------------");
                System.out.println("Nome: " + clientes.getNomeCliente().toUpperCase());
                System.out.println("CPF: " + clientes.getCpfCliente());
                System.out.println("E-mail: " + clientes.getEmailCliente().toLowerCase());
                System.out.println("Nº de conta: " + clientes.getNumConta());
                System.out.println("Saldo: " + clientes.getSaldoConta() + " R$");
                System.out.println("------------------------");

            }
        }

    }

    public void buscarClientes() {

        System.out.println("\nBUSCANDO CLIENTE: ");
        String cpf = Console.scanString("Informe o CPF do cliente: ");
        RepositorioClientes.getInstance().buscarClientes(cpf);

    }

}
