package model;


import java.util.ArrayList;
import util.Console;
import view.ClienteUI;

public class Menu {

    public void abrirMenuPrincipal() {

        Cadastro c = new Cadastro();
        ClienteUI cc = new ClienteUI();

        int op = 0;

        do {

          try {
                op = Console.scanInt("\nBem-vindo! Informe uma opção: \n1) Cadastrar clientes \n2) Exibir dados de clientes cadastrados \n3) Cadastrar Produtos \n4) Exibir produtos \n0)Sair do programa ");

                switch (op) {

                    case 1:
                        cc.cadastrarClientes();
                        break;

                    case 2:
                        cc.listarClientes();
                        break;

                    case 3:
                        c.cadastrarProduto();
                        break;

                    case 4:
                        c.exibirProduto();
                        break;

                    case 0:
                        System.out.println("Saindo do programa...");
                        break;

                    default:
                        System.out.println("Opção inválida!");

                }

            } catch (Exception e) {
                System.out.println("Não foi possível acessar as opções do Menu! Por favor, informe uma opção válida.");
            }

        } while (0 != op);

    }
}
