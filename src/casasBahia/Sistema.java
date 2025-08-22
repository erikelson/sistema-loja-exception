package casasBahia;

import java.util.ArrayList;
import java.util.Scanner;

import excecoes.*;

public class Sistema {

    private ArrayList<Produto> produtos;

    public Sistema() {
        this.produtos = new ArrayList<Produto>();
    }

    public void adicionarProduto(Scanner scanner) throws PrecoInvalidoException, NomeProdutoInvalidoException, CodigoDoProdutoException, OpcaoInvalidaException {
        System.out.println("Digite o nome do produto: ");
        String nome = scanner.nextLine();
        if (nome == null || nome.trim().isEmpty()) {
            throw new NomeProdutoInvalidoException("Nome do produto não pode está vazio.");
        } else if (nome.trim().matches("\\d+$")) {
            throw new NomeProdutoInvalidoException("Nome do produto não pode conter apenas número");
        } else if (nome.trim().length() < 3) {
            throw new NomeProdutoInvalidoException("Nome do produto não pode ocnter menos de 3 caracteres");
        }

        System.out.println("Digite o código: ");
        String codigo = scanner.nextLine();
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new CodigoDoProdutoException("Código não pode está vazio.");
        } else if (!codigo.trim().matches("\\d+$")) {
            throw new CodigoDoProdutoException("Código do produto não pode conter letra");
        }


        System.out.println("Digite o preço do produto: ");
        //Double preco = scanner.nextDouble();
        String precoStr = scanner.nextLine();
        if (precoStr == null || precoStr.trim().isEmpty()) {
            throw new PrecoInvalidoException("Preço não pode estar vazio");
        } else if (precoStr.matches(".*[a-zA-Z].*")) {
            throw new PrecoInvalidoException("Não são permitidas letras. Exemplo: (9.99 ou 9,99), tente novamente!");
        }
        Double preco = Double.parseDouble(precoStr.replace(',', '.'));


        if (preco <= 0) {
            throw new PrecoInvalidoException("O preço precisa ser maior que 0");
        }
        System.out.println("Qual o tipo do produto: ");
        System.out.println("1 - Móvel ");
        System.out.println("2 - Eletro ");



        String opcaoStr = scanner.nextLine();
        if (opcaoStr == null || opcaoStr.trim().isEmpty()) {
            throw new OpcaoInvalidaException("Opção não pode estar vazia, tente novamente!");
        } else if (opcaoStr.matches(".*[a-zA-Z].*")) {
            throw new OpcaoInvalidaException("Escolha opção 1 ou 2. Não são permitidas letras, tente novamente!");
        }

        int opcao = Integer.parseInt(opcaoStr);


        if (opcao == 1) {
            adicionarMovel(scanner, nome, codigo, preco);
        } else if (opcao == 2) {
            adicionarEletro(scanner, nome, codigo, preco);
        } else {
            System.out.println("Opção invalida, tente novamente!");
        }
    }

    private void adicionarMovel(Scanner scanner, String nome, String codigo, double preco) {
        System.out.println("Digite o material:");
        String material = scanner.nextLine();

        System.out.println("Digite a categoria:");
        String categoria = scanner.nextLine();

        Movel movel = new Movel(codigo, nome, preco, categoria, material);
        produtos.add(movel);

        System.out.println("Produto adicionado");

    }

    private void adicionarEletro(Scanner scanner, String nome, String codigo, double preco) {
        CategoriaEletro categoriaEletro = null;
        System.out.println("Qual a categoria do eletrodomestico cadastrado?");
        System.out.println("1 - Cozinha");
        System.out.println("2 - Quarto");
        System.out.println("3 - Lavanderia");

        int opcaoCategoria = scanner.nextInt();
        scanner.nextLine();

        if (opcaoCategoria == 1) {
            categoriaEletro = CategoriaEletro.COZINHA;
        } else if (opcaoCategoria == 2) {
            categoriaEletro = CategoriaEletro.QUARTO;
        } else if (opcaoCategoria == 3) {
            categoriaEletro = CategoriaEletro.LAVANDERIA;
        }
        System.out.println("Digite a voltagem");
        int voltagem = scanner.nextInt();
        scanner.nextLine();
        Eletrodomestico eletro = new Eletrodomestico(codigo, nome, preco, categoriaEletro, voltagem);
        produtos.add(eletro);
        System.out.println("Produto adicionado");

    }

    public void listarProdutos() {
        if (produtos.size() == 0) {
            System.out.println("Nenhum produto cadastrado!");
        } else {
            for (Produto produto : produtos) {
                System.out.println(produto);
            }
        }

    }


    public Produto buscarProduto(Scanner scanner) throws CodigoDoProdutoException {
        System.out.println("Digite o código procurado:");
        String codigoProcurado = scanner.nextLine();
        if (codigoProcurado == null || codigoProcurado.trim().isEmpty()) {
            throw new CodigoDoProdutoException("Código não pode está vazio.");
        } else if (!codigoProcurado.trim().matches("\\d+$")) {
            throw new CodigoDoProdutoException("Código do produto não pode conter letra");
        }

        for (Produto produto : produtos) {
            if (produto.getCodigo().equals(codigoProcurado)) {
                System.out.println("Produto encontrado!");
                System.out.println(produto);
                return produto;
            }
        }
        System.out.println("Produto não encontrado!");
        return null;
    }

    public void removerProduto(Scanner scanner) throws CodigoDoProdutoException {
        System.out.println("Digite o código do produto: ");
        String codigoProcurado = scanner.nextLine();
        if (codigoProcurado == null || codigoProcurado.trim().isEmpty()) {
            throw new CodigoDoProdutoException("Código não pode está vazio.");
        } else if (!codigoProcurado.trim().matches("\\d+$")) {
            throw new CodigoDoProdutoException("Código do produto não pode conter letra");
        }

        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getCodigo().equals(codigoProcurado)) {
                produtos.remove(i);
                System.out.println("Produto removido");
                return;
            }
        }
        System.out.println("Produto não encontrado!");
    }
}

