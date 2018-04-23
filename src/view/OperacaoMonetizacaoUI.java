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
                        System.out.println("Em desenvolvimento.");
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
                    System.out.println("\nInformações do Cliente: " + clientes.getNomeCliente());
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

                } else {
                    System.out.println("Nº de conta não encontrado.");
                }
            }

        } catch (Exception e) {

            System.out.println("Não foi possível realizar um depósito para o cliente, algum valor inválido foi informado.");

        }

    }

    public void listarSaldoCliente() {

        String numConta = Console.scanString("Informe o Nº de conta do cliente: ");
        for (Clientes clientes : RepositorioClientes.getInstance().getClientes()) {
            if (clientes.getNumConta().equalsIgnoreCase(numConta)) {
                System.out.println("\n------------------------");
                System.out.println("Nome do Cliente: " + clientes.getNomeCliente().toUpperCase());
                System.out.println("Nº de conta do Cliente: " + clientes.getNumConta());
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
