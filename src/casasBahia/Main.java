package casasBahia;

import java.util.InputMismatchException;
import java.util.Scanner;

import excecoes.*;


public class Main {
    public static void main(String[] args) {

        Sistema sistema = new Sistema();
        Scanner scan = new Scanner(System.in);

        int opcao;

        do {
            System.out.println("---Menu----");
            System.out.println("1- Adicionar");
            System.out.println("2- Listar");
            System.out.println("3- Buscar");
            System.out.println("4- Remover");
            System.out.print("Digite sua opção: ");
            opcao = scan.nextInt();
            scan.nextLine();

            switch (opcao) {
                case 1:
                    try {
                        sistema.adicionarProduto(scan);
                    } catch (PrecoInvalidoException e) {
                        System.out.println(e.getMessage());
                    } catch (NomeProdutoInvalidoException e) {
                        System.out.println(e.getMessage());
                    } catch (CodigoDoProdutoException e) {
                        System.out.println(e.getMessage());
                    } catch (InputMismatchException e) {
                        System.out.println(e.getMessage());
                    } catch (OpcaoInvalidaException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    sistema.listarProdutos();
                    break;
                case 3:
                    try {
                        sistema.buscarProduto(scan);
                    } catch (CodigoDoProdutoException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 4:
                    try {
                        sistema.removerProduto(scan);
                    } catch (CodigoDoProdutoException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                default:
                    break;
            }

        } while (opcao != 5);
        System.out.println("Saindo do Sistema!");
    }
}



