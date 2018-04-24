package view;

import model.Clientes;
import model.OperacaoMonetizacao;
import repositorio.RepositorioClientes;
import repositorio.RepositorioOperacaoMonetizacao;
import util.Console;

public class OperacaoMonetizacaoUI {

    public void menuOperacoes() {
        int op = 0;
        do {

            try {
                op = Console.scanInt("\nBem-vindo ao Menu de Operações de Monetização! Informe uma opção: \n1) Depositar valor \n2) Realizar transferência \n3) Visualizar saldo da conta \n0) Voltar para o menu principal");

                switch (op) {

                    case 1:
                        this.depositarValor();
                        break;

                    case 2:
                        this.realizarTransferencia();
                        break;

                    case 3:
                        this.listarSaldoCliente();
                        break;

                    case 0:
                        System.out.println("\nVoltando para o menu principal...");
                        break;

                    default:
                        System.out.println("Opção inválida!");
                }

            } catch (Exception e) {
                System.out.println("Não foi possível acessar as opções do Menu de Operações de Monetização! Por favor, informe uma opção válida.");
            }

        } while (op != 0);

    }

    public void depositarValor() {

        try {

            System.out.println("\nRealizando depósito...");

            String numConta = Console.scanString("\nInforme o Nº de conta do cliente: ");

            for (Clientes clientes : RepositorioClientes.getInstance().getClientes()) {
                if (clientes.getNumConta().equalsIgnoreCase(numConta)) {

                    System.out.println("\nCliente encontrado!");
                    
                    System.out.println("\n------------------------");
                    System.out.println("Nome: " + clientes.getNomeCliente().toUpperCase());
                    System.out.println("CPF: " + clientes.getCpfCliente());
                    System.out.println("E-mail: " + clientes.getEmailCliente().toLowerCase());
                    System.out.println("Nº de conta: " + clientes.getNumConta());
                    System.out.println("Saldo: " + clientes.getSaldoConta() + " R$");
                    System.out.println("------------------------");

                    try {
                        double valorDeposito = Console.scanDouble("\nInforme o valor para depositar: ");

                        if (valorDeposito <= 0) {
                            System.out.println("Valor inválido!");
                            break;
                        }

                        double novoSaldoConta = valorDeposito + clientes.getSaldoConta();
                        clientes.setSaldoConta(novoSaldoConta);
                        System.out.println("Novo saldo: " + novoSaldoConta + " R$");

                    } catch (Exception e) {
                        System.out.println("Valor inválido!");
                    }

                } 
            }

        } catch (Exception e) {

            System.out.println("Não foi possível realizar um depósito para o cliente, algum valor inválido foi informado.");

        }

    }

    public void realizarTransferencia() {

        System.out.println("\nRealizando transferência...");

        String numConta = Console.scanString("\nInforme o Nº de conta do cliente que vai transferir: ");
        
        double valorTransferencia=0;
        double novoSaldoConta;

        for (Clientes clientes : RepositorioClientes.getInstance().getClientes()) {
            if (clientes.getNumConta().equalsIgnoreCase(numConta)) {

                System.out.println("\nCliente encontrado!");
                System.out.println("\nInformações do Cliente: " + clientes.getNomeCliente());
                System.out.println("\n------------------------");
                System.out.println("Nome: " + clientes.getNomeCliente().toUpperCase());
                System.out.println("CPF: " + clientes.getCpfCliente());
                System.out.println("E-mail: " + clientes.getEmailCliente().toLowerCase());
                System.out.println("Nº de conta: " + clientes.getNumConta());
                System.out.println("Saldo: " + clientes.getSaldoConta() + " R$");
                System.out.println("------------------------");

                try {
                        valorTransferencia = Console.scanDouble("\nInforme o valor para transferir: ");
                        clientes.setValorTransferencia(valorTransferencia);
                    if (valorTransferencia <= 0) {
                        System.out.println("Valor inválido!");
                        break;
                    }

                    
                    novoSaldoConta = clientes.getSaldoConta() - valorTransferencia;
                  
                    clientes.setSaldoConta(novoSaldoConta);

                    System.out.println("Novo saldo de " + clientes.getNomeCliente() + ": " + novoSaldoConta + " R$");

                } catch (Exception e) {
                    System.out.println("Valor inválido!");
                }

            } 
        }

        // Adicionando o valor da transferência na outra conta
        
        String numConta2 = Console.scanString("\nInforme o Nº da conta que vai receber o valor da transferência: ");

        for (Clientes clientes : RepositorioClientes.getInstance().getClientes()) {
            if (clientes.getNumConta().equalsIgnoreCase(numConta2)) {

                System.out.println("\nCliente encontrado!");
                System.out.println("\nInformações do Cliente: " + clientes.getNomeCliente());
                System.out.println("\n------------------------");
                System.out.println("Nome: " + clientes.getNomeCliente().toUpperCase());
                System.out.println("CPF: " + clientes.getCpfCliente());
                System.out.println("Nº de conta: " + clientes.getNumConta());
                System.out.println("Saldo sem o valor da transferência: " + clientes.getSaldoConta() + " R$");
                System.out.println("------------------------");

            

            double novoSaldoConta2 = valorTransferencia + clientes.getSaldoConta();
            clientes.setValorTransferencia(valorTransferencia);
            clientes.setSaldoConta(novoSaldoConta2);
            System.out.println("\n------------------------");
            System.out.println("Valor da transferência: " + clientes.getValorTransferencia());

            System.out.println("Novo saldo da conta : " + novoSaldoConta2 + " R$");
            System.out.println("------------------------");
            } else { // Caso não encontre a segunda conta, ele informa para o usuário e passa o valor da transferência de volta pra conta inicial
                 System.out.println("Conta que vai receber o valor não foi encontrada...");
                 /*novoSaldoConta = clientes.getSaldoConta() + valorTransferencia;
                 clientes.setSaldoConta(novoSaldoConta);*/
            }  
             
        }
    }

    public void listarSaldoCliente() {

        String numConta = Console.scanString("\nInforme o Nº de conta do cliente: ");
        for (Clientes clientes : RepositorioClientes.getInstance().getClientes()) {
            if (clientes.getNumConta().equalsIgnoreCase(numConta)) {
                System.out.println("\n------------------------");
                System.out.println("Nome do Cliente: " + clientes.getNomeCliente().toUpperCase());
                System.out.println("Nº de conta do Cliente: " + clientes.getNumConta());
                System.out.println("Saldo: " + clientes.getSaldoConta() + " R$");
                System.out.println("------------------------");
            } else{
                System.out.println("Conta inválida!");
            }

        }
    }

    public void buscarClientes() {

        System.out.println("\nBUSCANDO CLIENTE: ");
        String cpf = Console.scanString("Informe o CPF do cliente: ");
        RepositorioClientes.getInstance().buscarClientes(cpf);

    }
}
