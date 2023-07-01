package com.veronica.app;
import java.util.Scanner;
import org.mindrot.jbcrypt.BCrypt;

import com.veronica.DAO.CompradorDAO;
import com.veronica.DAO.EstoqueDAO;
import com.veronica.DAO.FornecedorDAO;
import com.veronica.DAO.ProdutoDAO;
import com.veronica.DAO.UsuarioDAO;
import com.veronica.DAO.CompraDAO;
import com.veronica.classes.Comprador;
import com.veronica.classes.CompradorFisico;
import com.veronica.classes.CompradorJuridico;
import com.veronica.classes.Estoque;
import com.veronica.classes.Produto;
import com.veronica.classes.Usuario;
import com.veronica.classes.Compra;
import com.veronica.classes.Fornecedor;

import java.util.ArrayList;
import java.util.List;
/**
 * Hello world!
 *
 */
public class App 
{
    //declara e inicializa as classes DAO como estaticas para serem visiveis em todos os metodos
    private static ProdutoDAO produtoDAO;
    private static EstoqueDAO estoqueDAO;
    private static FornecedorDAO fornecedorDAO;
    private static CompraDAO compraDAO;
    private static CompradorDAO compradorDAO;
    private static UsuarioDAO usuarioDAO;
    static{
        produtoDAO = new ProdutoDAO();
        estoqueDAO = new EstoqueDAO();
        fornecedorDAO = new FornecedorDAO();
        compraDAO = new CompraDAO();
        compradorDAO = new CompradorDAO();
        usuarioDAO = new UsuarioDAO();
    }
    
    public static Scanner s = new Scanner(System.in);


    static public void criaUsuario(UsuarioDAO usuarioDAO){
        try{
            int a=0;
            List<Usuario> usuarios = usuarioDAO.buscarTodosUsuarios();//resgata usuarios
            System.out.println("Crie um Usuário: ");
            String u = s.nextLine();//le o user
            for(Usuario usuario: usuarios){//percorre a lista
                if(usuario.getUser().equals(u)){//se o user ja foi cadastrado a recebe ++;
                    a++;
                }
            }
            if(a==0){//se o user nao foi cadastrado
                System.out.println("Crie uma Senha: ");//cria a senha
                String se = s.nextLine();
                if(se.length()>=6){//se for maior ou igual a 6 caracteres
                    Usuario useUsuario = new Usuario(u, se);//cria o usuario
                    usuarioDAO.salvarUsuario(useUsuario);//salva no banco
                    System.out.println("Usuario criado com sucesso!");
                }else{
                    System.out.println("Senha deve ter no mínimo 6 caracteres!");
                }
            }
            if(a!=0){
                System.out.println("Usuario já esta em uso!");
            }
        }catch (IllegalArgumentException e) {
        System.out.println("Entrada inválida: " + e.getMessage());

        }catch(Exception e){
            System.out.println("Ocorreu um erro ao criar Usuário!");
            e.printStackTrace();
        }
    }
   
     static public void listaUsuario(UsuarioDAO usuarioDAO){
        try{
            List<Usuario> usuarios = usuarioDAO.buscarTodosUsuarios();//resgata usuarios
            if(usuarios.size()!=0){//se a lista estiver povoada
                System.out.println(usuarios);//mostra lista
            }else{
                System.out.println("Ainda não existem usuarios cadastrados...");
            }
        }catch(Exception e){
            System.out.println("Erro: "+e);
            e.printStackTrace();
        }
    }
      static public void buscaUsuario(UsuarioDAO usuarioDAO){
        try{
            int a=0;
            List<Usuario> usuarios = usuarioDAO.buscarTodosUsuarios();//resgata usuarios
            System.out.println("Informe o user:");
            String user = s.nextLine();//informa o user desejado
            if(usuarios.size()!=0){//se a lista estiver povoada
                for(Usuario usuario:usuarios){//percorre a lista
                    if(usuario.getUser().equals(user)){//se encontrar o user informado
                        a++;
                        System.out.println(usuario);//mostra userw
                    }
                }
            }else{
                System.out.println("Ainda não existem usuarios cadastrados...");
            }
            if(a==0){
                System.out.println("Não foi encontrado nenhum usuário...");
            }
        }catch(Exception e){
            System.out.println("Erro: "+e);
            e.printStackTrace();
        }
    }
    static public boolean verificaUsuario(UsuarioDAO usuarioDAO){//usada para poder deletar itens;
        try{
            int a=0;
            List<Usuario> usuarios = usuarioDAO.buscarTodosUsuarios();//resgata usuarios
            System.out.println("Informe o user:");
            String user = s.nextLine();//informa user
            if(usuarios.size()!=0){//se a lista estiver povoada
                for(Usuario usuario:usuarios){//percorre a lista
                    if(usuario.getUser().equals(user)){//se encontrar o usuario
                        a++;
                        System.out.println("Informe senha: ");
                        String se = s.nextLine();//pede a senha
                        String senha = usuario.getSenhaCriptografada();//resgata senha do usuario
                         if (BCrypt.checkpw(se, senha)) {//se a senha for a mesma
                           return true;//verificação retorna true;
                       }else{
                        System.out.println("Senha incorreta!");
                        return false;
                       }
                    }
                }
            }else{
                System.out.println("Ainda não existem usuarios cadastrados...");
                return false;
            }
            if(a==0){
                System.out.println("Não foi encontrado nenhum usuário...");
                return false;
            }
        }catch(Exception e){
            System.out.println("Erro: "+e);
            e.printStackTrace();
            return false;
        }
        return false;
    }
    public static void atualizaUsuario(UsuarioDAO usuarioDAO){
        try{
            int a =0;
            List<Usuario> usuarios = usuarioDAO.buscarTodosUsuarios();//resgata usuarios
            System.out.println("Informe o user: ");
            String user = s.nextLine();//informa o user a ser atualizado
            if(usuarios.size()!=0){// se a lista for diferente de zero
                for(Usuario usuario:usuarios){//percorre a lista de usuarios
                    if(usuario.getUser().equals(user)){
                        a++;
                        System.out.println("Senha: ");
                        String se = s.nextLine();//pede senha do usuario
                        String senha = usuario.getSenhaCriptografada();//resgata senha
                        if (BCrypt.checkpw(se, senha)) {//se a senha for a mesma
                            System.out.println("Informe o novo user: ");
                            String u =s.nextLine();//pede novo usuario
                            usuario.setUser(u);//seta user
                            System.out.println("Informe senha: ");//pede nova senha
                            String sen = s.nextLine();
                            usuario.setSenhaCriptografada(sen);//seta senha
                            usuarioDAO.atualizarUsuario(usuario);//atualiza os dados no banco
                        System.out.println("Usuário atualizado com sucesso...");
                       }else{
                        System.out.println("Senha incorreta!");
                       }
                    }
                }
            }else{
                System.out.println("Ainda não foi cadastrado nenhum usuário...");
            }
            if(a==0){
                System.out.println("Não foi encontrado nenhum usuário com esse login...");
            }
    }catch (IllegalArgumentException e) {
        System.out.println("Entrada inválida: " + e.getMessage());
    } catch(Exception e){
            System.out.println("Erro: "+e);
            e.printStackTrace();
        }
    }
    static public void deletaUsuario(UsuarioDAO usuarioDAO){
        int a=0;
        try{
            List<Usuario> usuarios = usuarioDAO.buscarTodosUsuarios();//resgata usuarios
            System.out.println("Informe o user para excluir:");
            String user = s.nextLine();//user a ser deletado
            if(usuarios.size()!=0){//se a lista estiver povoada
                for(Usuario usuario:usuarios){//percorre a lista
                    if(usuario.getUser().equals(user)){//se encontrar o usuario
                        a++;
                        System.out.println("Senha: ");
                        String se = s.nextLine();//pede a senha
                        String senha = usuario.getSenhaCriptografada();//resgata a senha
                        if (BCrypt.checkpw(se, senha)) {//se a senha for igual
                            usuarioDAO.excluirUsuario(usuario);//deleta usuario no banco
                            System.out.println("Usuário excluido com sucesso!");
                       }else{
                        System.out.println("Senha incorreta!");
                       }
                  }
                }
            }else{
                System.out.println("Ainda não existem usuarios cadastrados...");
            }
            if(a==0){
                System.out.println("Não foi possível excluir o usuário...");
            }
            }catch (IllegalArgumentException e) {
            System.out.println("Entrada inválida: " + e.getMessage());
            } catch(Exception e){
            System.out.println("Erro: "+e);
            e.printStackTrace();
        }
    }
   public static void criaComprador(){
        try{
            //pede dados ao usuario para criar um comprador
            System.out.println("Informe nome: ");
            String nome = s.nextLine();
            Comprador c = new Comprador(nome);
            System.out.println("1- Físico/2- Jurídico?");
            String op = s.nextLine();
            if(Integer.parseInt(op)==1){
                System.out.println("Informe CPF(minimo 11 caracteres): ");
                String cpf = s.nextLine();
                if(!validaCPF(cpf)){//se o cpf for maior ou igual a 11 caracteres
                    System.out.println("Informe um cpf válido!");
                }else{
                    //cria o comprador
                CompradorFisico f = new CompradorFisico(nome, cpf);
                compradorDAO.salvarComprador(f);//cria no banco
                System.out.println("Comprador cadastrado com sucesso!");
                }
            }else if(Integer.parseInt(op)==2){
                System.out.println("Informe CNPJ(minimo 14 caracteres): ");
                String cnpj = s.nextLine();
                if(!validaCNPJ(cnpj)){
                    System.out.println("Informe um cnpj válido");
                }else{
                    CompradorJuridico j = new CompradorJuridico(nome, cnpj);
                    compradorDAO.salvarComprador(j);
                    System.out.println("Comprador cadastrado com sucesso!");
                }
                }else{
                    System.out.println("Opção inválida!");
                }
             }catch (IllegalArgumentException e) {
                System.out.println("Entrada inválida: " + e.getMessage());
             } catch(Exception e){
            System.out.println("Erro: "+e);
            e.printStackTrace();
        }

    } 

    
    static public void criaProduto(ProdutoDAO produtoDAO, EstoqueDAO estoqueDAO, FornecedorDAO fornecedorDAO){
        try{
            //pede dados para criar um produto
            int a = 0;
            System.out.println("Informe o número de produtos: ");
            String n = s.nextLine();
            for(int i=0;i<Integer.parseInt(n);i++){
                System.out.println("Informe nome do produto: ");
                String nome = s.nextLine();
                System.out.println("Informe preço do produto: ");
                String preço = s.nextLine();
                preço = preço.replace(",", ".");//substitui a virgula pelo ponto
                Produto p = new Produto(Double.parseDouble(preço),  nome);//cria produto no banco
                produtoDAO.salvarProduto(p);//salva no banco
                System.out.println("Informe quantidade em estoque: ");
                String qtd = s.nextLine();
                Estoque e = new Estoque(Integer.parseInt(qtd), p);//cria o estoque do produto
                estoqueDAO.salvarEstoque(e);
                System.out.println("Estoque criado com sucesso!");
                List<Fornecedor> fornecedores = fornecedorDAO.buscarTodosFornecedores();//lista fornecedores
                System.out.println(fornecedores);
                System.out.println("Informe o id do Fornecedor: ");
                String na = s.nextLine();
                Fornecedor f = fornecedorDAO.buscarFornecedorPorId(Long.parseLong(na));
                if(f!=null){//se encontrar o fornecedor
                    p.setFornecedor(f);//seta o fornecedor para o produto
                    System.out.println("Fornecedor add com sucesso!");
                    produtoDAO.atualizaProduto(p);//atualiza o produto
                }else{
                    System.out.println("Fornecedor não encontrado!");
                }
            }
        }catch (IllegalArgumentException e) {
                System.out.println("Entrada inválida: " + e.getMessage());
             } catch(Exception e){
            System.out.println("Erro: "+e);
            e.printStackTrace();
        }
    }
 
    //valida tamanhos de cpf e cnpj
    static public boolean validaCNPJ(String cnpj){
        if(cnpj.length()<14){
            return false;
        }else{
            return true;
        }
    }
     static public boolean validaCPF(String cpf){
        if(cpf.length()<11){
            return false;
        }else{
            return true;
        }
    }

static public void criaFornecedor(FornecedorDAO fornecedorDAO) {
    try {
        //solicita dados para criar um fornecedor
        List<Fornecedor> fornecedores = fornecedorDAO.buscarTodosFornecedores();//resgata fornecedores
        System.out.println("Informe o número de fornecedores: ");
        int numFornecedores = Integer.parseInt(s.nextLine());

        for (int i = 0; i < numFornecedores; i++) {
            System.out.println("Informe o nome do fornecedor: ");
            String nome = s.nextLine();
            System.out.println("Informe o CNPJ do fornecedor(14 caracteres): ");
            String cnpj = s.nextLine();
            if(!validaCNPJ(cnpj)){//valida cnpj
                System.out.println("O CNPJ deve ter no minimo 14 caracteres!");
            }else{
                boolean cnpjDuplicado = false;
                for (Fornecedor fornecedor : fornecedores) {//percorre a lista
                    if (fornecedor.getCNPJ().equals(cnpj)) {//se encontrar um cnpj igual
                        System.out.println("CNPJ já cadastrado.");
                        cnpjDuplicado = true;
                        break;
                    }
                }
                if (!cnpjDuplicado) {//se nao encontrar cnpj igual
                    Fornecedor fornecedor = new Fornecedor(nome, cnpj);//cria fornecedor
                    fornecedorDAO.salvarFornecedor(fornecedor);//cria no banco
                }
            } 
        System.out.println("Fornecedores cadastrados com sucesso!");
    } }catch (IllegalArgumentException e) {
                System.out.println("Entrada inválida: " + e.getMessage());
             } catch (Exception e) {
        System.out.println("Erro: " + e);
        e.printStackTrace();
    }
}

    static public void listaFornecedores(FornecedorDAO fornecedorDAO){
        try{
            List<Fornecedor> fornecedores = fornecedorDAO.buscarTodosFornecedores();//resgata fornecedores
            if(fornecedores.size()!=0){//se a lista estiver povoada
                for(Fornecedor fornecedor: fornecedores){//percorre
                    System.out.println(fornecedor);//mostra a lista
                }
            }else{
                System.out.println("Ainda não existem fornecedores cadastrados!");
            }
        }catch (IllegalArgumentException e) {
                System.out.println("Entrada inválida: " + e.getMessage());
             } catch(Exception e){
            System.out.println("Erro: "+e);
            e.printStackTrace();
        }
    }
    static public void listaProdutos(ProdutoDAO produtoDAO){
        try{
            List<Produto> produtos = produtoDAO.buscarTodosProdutos();//resgata produtos
            if(produtos.size()!=0){//se a lista estiver povoada
                for (Produto produto : produtos) {//percorre
                    System.out.println(produto);//imprime a lista
                }
            }else{
                System.out.println("Ainda não foram cadastrados produtos!");
            }
        }catch (IllegalArgumentException e) {
                System.out.println("Entrada inválida: " + e.getMessage());
             } catch(Exception e){
            System.out.println("Erro: "+e);
            e.printStackTrace();
        }
    }
    static public void listaCompradores(CompradorDAO compradorDAO){
        try{
            //resgata os compradores
            List<Comprador> compradores = compradorDAO.buscarTodosCompradores();
            if(compradores.size()!=0){//se a lista estiver povoada
                System.out.println(compradores);//imprime a lista
            }else{
                System.out.println("Ainda não existem compradores cadastrados!");
            }
        }catch (IllegalArgumentException e) {
                System.out.println("Entrada inválida: " + e.getMessage());
             } catch(Exception e){
            System.out.println("Erro: "+e);
            e.printStackTrace();
        }
    }
    static public void buscaComprador(CompradorDAO compradorDAO){
        try{
            int a=0;
            List<Comprador> compradores = compradorDAO.buscarTodosCompradores();//resgata compradores
            System.out.println("Informe o nome do comprador: ");
            String name = s.nextLine();//solicita nome de comprador
            if(compradores.size()!=0){//se a lista estiver povoada
                for(Comprador comprador: compradores){//percorre
                    if(comprador.getNome().equals(name)){//se ecnotrar o comprador
                        System.out.println(comprador);//imprime comprador
                        a++;
                    }
                }
            }else{
                System.out.println("Ainda não foram cadastrados Compradores...");
            }
            if(a==0){
                System.out.println("Não foi encontrado nenhum comprador com esse nome...");
            }
        }catch (IllegalArgumentException e) {
                System.out.println("Entrada inválida: " + e.getMessage());
             } catch(Exception e){
            System.out.println("Erro: "+e);
            e.printStackTrace();
        }
    }
    static public void buscaFornecedor(FornecedorDAO fornecedorDAO){
        try{
            int a=0;
            //resgata fornecedores
            List<Fornecedor> fornecedores = fornecedorDAO.buscarTodosFornecedores();
            System.out.println("Informe o nome do fornecedor a ser buscado: ");
            String n = s.nextLine();//solicita fornecedor
            if(fornecedores.size()!=0){//se a lista estiver povoada
                for(Fornecedor fornecedor:fornecedores){//percorre
                    if(fornecedor.getNome().equals(n)){//se ecnontrar o fornecedor
                        a++;
                        System.out.println(fornecedor);//mostra fornecedor
                    }
                }
            }else{
                System.out.println("Ainda não foram cadastrados fornecedores!");
            }
            if(a==0){
                System.out.println("Não foi encontrado nenhum fornecedor com esse nome...");
            }
        }catch (IllegalArgumentException e) {
                System.out.println("Entrada inválida: " + e.getMessage());
             } catch(Exception e){
            System.out.println("Erro: "+e);
            e.printStackTrace();
        }
    }
    static public void buscaProduto(ProdutoDAO produtoDAO){
       try{
            int a = 0;
            //resgata produtos
            List<Produto> produtos = produtoDAO.buscarTodosProdutos(); 
            System.out.println("Informe o nome do produto a ser buscado: ");
            String name = s.nextLine();//solicita produto
            if(produtos.size()!=0){//se a lista estiver povoada
                for(Produto produto:produtos){//percorre
                    if(produto.getNome().equals(name)){//se encontrar produto
                        a++;
                        System.out.println(produto);//mostra produto
                    }
                }     
            }else{
                System.out.println("Ainda não foram cadastrados produtos!");
            }
            if(a==0){
                System.out.println("Não foi encontrado nenhum produto com esse nome...");
            }
        }catch (IllegalArgumentException e) {
                System.out.println("Entrada inválida: " + e.getMessage());
             } catch(Exception e){
            System.out.println("Erro: "+e);
            e.printStackTrace();
        }
    }
   

    static public void atualizaProduto(ProdutoDAO produtoDAO, FornecedorDAO fornecedorDAO){
        try{
            List<Produto> produtos = produtoDAO.buscarTodosProdutos(); //resgata produtos
            System.out.println(produtos);//mostra lista
            System.out.println("Informe o id do produto a ser atualizado: ");
            String id = s.nextLine();
            Produto p = produtoDAO.buscarProdutoPorId(Long.parseLong(id));
            if(p!= null){//se encontrar o id do produto
                System.out.println("Deseja atualizar este produto? (1-SIM/0-NÃO)"+p);
                String r = s.nextLine();
                if(Integer.parseInt(r)==1){//se a resposta for sim
                    System.out.println("Informe nome: ");//solicita novos dados
                    String n = s.nextLine();
                    p.setNome(n);
                    System.out.println("Informe PREÇO: ");
                    String pre = s.nextLine();
                    pre = pre.replace(",", ".");//troca virgula por ponto
                    p.setPreco(Double.parseDouble(pre));
                    List<Fornecedor> fornecedores = fornecedorDAO.buscarTodosFornecedores();//resgata fornecedores
                    System.out.println(fornecedores);//mostra lista
                    System.out.println("Informe o id do Fornecedor: ");
                    String f = s.nextLine();
                    Boolean v = false;
                    Fornecedor fo = fornecedorDAO.buscarFornecedorPorId(Long.parseLong(f));
                        if(fo!=null){//se encontrar fornecedor seta
                            p.setFornecedor(fo);
                            v = true;
                        }
                    if(v){
                        produtoDAO.atualizaProduto(p);//atualiza
                        System.out.println("Os dados foram atualizados com sucesso!");
                    }else{
                        System.out.println("Fornecedor não encontrado! Os dados não foram atualizados!");
                    }
                }

            }else{
                System.out.println("Produto não encontrado!");
            }
        }catch (IllegalArgumentException e) {
                System.out.println("Entrada inválida: " + e.getMessage());
             } catch(Exception e){
            System.out.println("Erro: "+e);
            e.printStackTrace();
        }

    }
    static public void atualizaFornecedor(FornecedorDAO fornecedorDAO){
       try{
            List<Fornecedor> fornecedores = fornecedorDAO.buscarTodosFornecedores();//resgata fornecedores
            System.out.println(fornecedores);
            System.out.println("Informe o id do fornecedor a ser atualizado: ");
            String id = s.nextLine();
            Fornecedor f = fornecedorDAO.buscarFornecedorPorId(Long.parseLong(id));
                if(f!=null){//se encontrar o id do fornecedor
                    System.out.println("Deseja atualizar o Fornecedor(1 -SIM/0 -NÃO)?");
                     String op = s.nextLine();
                        if(Integer.parseInt(op)==1){//se a resposta for sim
                                System.out.println("Informe o nome do fornecedor: ");//solicita novos dados
                                String na = s.nextLine();
                                f.setNome(na);
                                System.out.println("Informe o CNPJ(minimo 14 caracteres): ");
                                String cnpj = s.nextLine();
                                if(!validaCNPJ(cnpj)){//valida cnpj
                                    System.out.println("Informe um cnpj com no minimo 14 caracteres.");
                                }else{
                                    f.setCNPJ(cnpj);//seta cnpj
                                    fornecedorDAO.atualizarFornecedor(f);//atualzia
                                    System.out.println("Dados atualizados com sucesso!");
                                }
                            }
                    }
                }catch (IllegalArgumentException e) {
                System.out.println("Entrada inválida: " + e.getMessage());
             
            }catch(Exception e){
                        System.out.println("Erro: "+e);
                        e.printStackTrace();
                    }
            }
    static public void atualizaEstoque(EstoqueDAO estoqueDAO){
        try{
            System.out.println("Informe o id do Produto do estoque a ser buscado: ");
            String id = s.nextLine();
            Estoque e = estoqueDAO.buscarEstoquePorIdProduto(Long.parseLong(id));
            if(e!= null){//se encontrar o produto em estoque
                System.out.println("Deseja atualizar o Estoque (1-SIM/0-NÃO)"+e);
                String r = s.nextLine();
                if(Integer.parseInt(r)==1){//se a resposta for sim
                    System.out.println("Informe quantidade: ");
                    String qtd = s.nextLine();
                    e.setQuantidade(Integer.parseInt(qtd));//seta quantidade
                    estoqueDAO.atualizarestoque(e);//atualiza estoque
                    System.out.println("Os dados foram atualizados com sucesso!");
                };
            }else{
                System.out.println("Estoque não encontrado!");
            }
                }catch (IllegalArgumentException e) {
                System.out.println("Entrada inválida: " + e.getMessage());
             
            }catch(Exception e){
                        System.out.println("Erro: "+e);
                        e.printStackTrace();
                    }

    }
    static public void deletaProduto(ProdutoDAO produtoDAO, EstoqueDAO estoqueDAO){
        try{
            List<Produto> produtos = produtoDAO.buscarTodosProdutos();
            System.out.println(produtos);
            System.out.println("Informe o id do produto a ser deletado: ");
            String id = s.nextLine();
            Produto prod = produtoDAO.buscarProdutoPorId(Long.parseLong(id));
            if(prod!=null){//se encontrar o produto
                produtoDAO.excluirProduto(prod);//exclui o produto
                Estoque est = estoqueDAO.buscarEstoquePorIdProduto(Long.parseLong(id));//procura estoque do produto
                if(est!=null){
                    estoqueDAO.excluirEstoque(est);//exclui o estoque do produto
                }
                System.out.println("Produto excluido com sucesso!");
            }else{
                System.out.println("Produto não encontrado!");
            }
        }catch (IllegalArgumentException e) {
                System.out.println("Entrada inválida: " + e.getMessage());
        }catch(Exception e){
            System.out.println("Erro: "+e);
            e.printStackTrace();
        }
    }
 static public void deletaFornecedor(FornecedorDAO fornecedorDAO, ProdutoDAO produtoDAO){
    try {
        int a = 0;
        List<Fornecedor> fornecedores = fornecedorDAO.buscarTodosFornecedores();//resgata fornecedores
        List<Produto> produtos = produtoDAO.buscarTodosProdutos();//resgata produtos
        System.out.println(fornecedores);//imprime fornecedores
        System.out.println("Informe o nome do Fornecedor a ser deletado: ");
        String n = s.nextLine();
        
        for(Fornecedor fornecedor : fornecedores){//percorre
            if(fornecedor.getNome().equals(n)){//se encontrar o fornecedor
                a++;
                fornecedorDAO.excluirFornecedor(fornecedor);//exclui o fornecedor
                System.out.println("Excluído com sucesso!");
                
                for(Produto produto : produtos){//percorre produtos
                    if(produto.getFornecedor()==fornecedor){//se encontrar o produto do fornecedor
                        produtoDAO.excluirProduto(produto);//exclui o produto
                    }
                }
            }
        }
        
        if(a == 0){
            System.out.println("Fornecedor não encontrado!");
        }
    }catch (IllegalArgumentException e) {
                System.out.println("Entrada inválida: " + e.getMessage());
     } catch(Exception e){
        System.out.println("Erro: " + e);
        e.printStackTrace();
    }
}

    public static void venda(CompraDAO compraDAO, ProdutoDAO produtoDAO, CompradorDAO compradorDAO){
        try{
            List<Comprador> compradores = compradorDAO.buscarTodosCompradores();//RESGATA COMPRADORES
            List<Produto> produtosvenda =  new ArrayList<>();   //ARRAY PARA PRODUTOS SELECIONADOS NA COMPRA
            List<Produto> produtos = produtoDAO.buscarTodosProdutos();//resgata todos os produtos
            int a=0;
            int tp = 0;
            Comprador comp = null;
            String op = "";
            Double tot = 0.0;
                System.out.println(compradores);//mostra compradores
                System.out.println("Informe o nome do comprador: ");
                String n = s.nextLine();//pede nome de um comprador
                while(!op.equals("0")){//enquanto a opao nao for 0
                for(Comprador comprador: compradores){//percorre compradores
                    if(comprador.getNome().equals(n)){//se encontrar o comprador
                        a++;
                        comp = comprador;//guarda o comprador
                        System.out.println(produtos);//mostra  alista de produtos
                        System.out.println("Informe o produto a ser vendido: ");
                        String p = s.nextLine();//solicita para escolher um produto
                        for(int i=0; i<produtos.size();i++){//percorre a lista de produtos
                            if(produtos.get(i).getNome().equals(p)){//se encontrar a o produto
                                System.out.println("Informe quantidade: ");
                                String qtd = s.nextLine();//pede a quantidade
                                if(produtos.get(i).getqtdEstoque()>=Integer.parseInt(qtd)){
                                    //se a quantidade do produto
                                    //estiver de acordo com a qtd em estoque
                                    tp = tp+Integer.parseInt(qtd);//var guarda a qtd solicitada     
                                    Long id = produtos.get(i).getIdProduto();//armazena id do produto correspondente
                                    Estoque e = estoqueDAO.buscarEstoquePorIdProduto(id);//busca estoque pelo id do produto
                                    int esto = e.getQuantidade();//pega a qtd do produto em estoque
                                    esto = esto - Integer.parseInt(qtd);//faz a qtd em estoque - a qtd solicitada
                                    e.setQuantidade(esto);//seta o estoque atualizado
                                    estoqueDAO.atualizarestoque(e);//atualiza estoque no banco
                                    tot +=(produtos.get(i).getPreco()*Integer.parseInt(qtd));//faz o preco * a qtd do prodto
                                    produtosvenda.add(produtos.get(i));//add produto no array de venda
                                    System.out.println("Produto add com sucesso!");
                                    System.out.println("Deseja add mais produtos?(1-SIM/0-NÃO)");//pergunta se deseja add mais
                                    op = s.nextLine();
                            }else{
                                System.out.println("O produto não existe no estoque!");
                            } 
                        }
                    }
                    }   
                }
            }
            if(a==0){
                System.out.println("Comprador não encontrado!");
            }
                Compra v = new Compra(tot, comp, produtosvenda);//add nova venda
                compraDAO.salvarCompra(v);//salva venda no banco de dados;
                System.out.println("Venda realizada com sucesso!");
                System.out.println("Produtos vendidos: "+produtosvenda);
                System.out.println("Total da venda: "+tot);
                System.out.println("Qtd de produtos: "+tp);
                }catch(Exception e){
                    System.out.println("Erro: "+e);
                    e.printStackTrace();
                }
    }
    public static void listaVendas(CompraDAO compraDAO, ProdutoDAO produtoDAO) {
    List<Compra> vendas = compraDAO.buscarTodasCompras();//resgata vendas

    if (!vendas.isEmpty()) {//se a lista de vendas nao for vazia
        for (Compra venda : vendas) {//percorre vendas
            //mostra venda
            System.out.println("ID da Venda: " + venda.getIdCompra());
            System.out.println("Preço Total: " + venda.getPrecoTotal());
            System.out.println("Comprador: " + venda.getComprador());



            System.out.println("------------------------");
        }
    } else {
        System.out.println("Ainda não existem vendas!");
    }
}

   public static void atualizaComprador(CompradorDAO compradorDAO) {
    try {
        List<Comprador> compradores = compradorDAO.buscarTodosCompradores();
        //resgata compradores
        if (compradores.size() != 0) {//se nao estiver vazio
            System.out.println("Informe o nome do comprador a ser atualizado: ");
            String nome = s.nextLine();//solicita nome do comprador
            
            boolean compradorEncontrado = false;
            
            for (Comprador comprador : compradores) {//percorre compradores
                if (comprador.getNome().equals(nome)) {//se encontrar o comprador
                    compradorEncontrado = true;
                    
                    System.out.println("Informe nome: ");
                    String novoNome = s.nextLine();//solicita novos dados
                    
                    comprador.setNome(novoNome);
                    
                    System.out.println("1 - CPF/2 - CNPJ?");
                    String op = s.nextLine();
                    
                    if (Integer.parseInt(op) == 1) {
                        if (comprador instanceof CompradorFisico) {
                            System.out.println("Informe CPF: ");
                            String cpf = s.nextLine();
                            
                            ((CompradorFisico) comprador).setCPF(cpf);
                            compradorDAO.atualizarComprador(comprador);//atualiza
                            System.out.println("Comprador atualizado com sucesso!");
                        } else {
                            System.out.println("Opção inválida! O comprador não é uma pessoa física.");
                        }
                    } else if (Integer.parseInt(op) == 2) {
                        if (comprador instanceof CompradorJuridico) {
                            System.out.println("Informe CNPJ: ");
                            String cnpj = s.nextLine();
                            
                            ((CompradorJuridico) comprador).setCNPJ(cnpj);
                            compradorDAO.atualizarComprador(comprador);//atualiza
                            System.out.println("Comprador atualizado com sucesso!");
                        } else {
                            System.out.println("Opção inválida! O comprador não é uma pessoa jurídica.");
                        }
                    } else {
                        System.out.println("Opção inválida!");
                    }
                }
            }
            
            if (!compradorEncontrado) {//se nao encontrar
                System.out.println("Comprador não encontrado!");
            }
        } else {
            System.out.println("A lista de compradores está vazia!");
        }
    } catch (Exception e) {
        System.out.println("Erro: " + e);
        e.printStackTrace();
    }
}

       static public void deletaComprador(CompradorDAO compradorDAO, CompraDAO compraDAO){
        try{
            int a=0;
            List<Comprador> compradores = compradorDAO.buscarTodosCompradores();//resgata compradores
            List<Compra> compras = compraDAO.buscarTodasCompras();//resgata compras
            if(compradores.size()!=0){//se estiver povoado
                System.out.println("Informe o nome do comprador a ser deletado: ");
                String nome = s.nextLine();//pergunta nome
                    for(Comprador comprador:compradores){//percorre compradores
                        if(comprador.getNome().equals(nome)){
                            a++;
                            Comprador c = comprador;
                            compradorDAO.excluirComprador(c);//exclui comprador
                            System.out.println("comprador excluido com sucesso!");
                            for(Compra compra: compras){//percorre a lista de compras 
                                if(compra.getComprador()==c){//se o comprador tiver compras
                                    compraDAO.excluirCompra(compra);//exlui compras
                                }
                            }
                        }
                    }
                if(a==0){
                    System.out.println("Comprador não encontrado!");
                }
            }else{
                System.out.println("A lista de compradores está vazia!");
            }
        }catch(Exception e){
            System.out.println("Erro: "+e);
            e.printStackTrace();
        }
    }

    
    public static void main( String[] args )
    {
        try{
                String op = "";
                while(!op.equals("0")){
                    System.out.println("Informe uma opção: ");
                    System.out.println("1 - Criar Usuario");
                    System.out.println("2 - Gerenciar Produtos");
                    System.out.println("3 - Gerenciar Estoques");
                    System.out.println("4 - Gerenciar Fornecedores");
                    System.out.println("5 - Gerenciar Vendas");
                    System.out.println("6 - Gerenciar Compradores");
                    System.out.println("7 - Gerenciar Usuario");
                    System.out.println("0 - Encerrar");
                    op = s.nextLine();
                switch(Integer.parseInt(op)) {
                    case 1:
                        criaUsuario(usuarioDAO);
                        break;
                    case 2:
                        gerenciaProdutos();
                        break;
                    case 3:
                        gerenciaEstoques();
                        break;
                    case 4:
                        gerenciaFornecedores();
                        break;
                    case 5:
                        gerenciaVendas();
                        break;
                    case 6:
                        gerenciaCompradores();
                        break;
                    case 7:
                            gerenciaUsuario();
                            break;
                    case 0:
                        System.out.println("Encerrando...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            }
        }catch(Exception e){
            System.out.println("Erro: "+e);
            e.printStackTrace();
        }
        }


            static public void gerenciaProdutos(){
                String op = "";
                while(!op.equals("0")){
                    System.out.println("MENU DE PRODUTOS");
                    System.out.println("1 - Criar produtos");
                    System.out.println("2 - Listar produtos");
                    System.out.println("3 - Buscar produto");
                    System.out.println("4 - Atualizar produto");
                    System.out.println("5 - Deletar produto");
                    System.out.println("0 - Voltar ao menu principal");
                    op = s.nextLine();
                    switch(Integer.parseInt(op)){
                        case 1:
                            criaProduto(produtoDAO, estoqueDAO, fornecedorDAO);
                            break;
                        case 2:
                            listaProdutos(produtoDAO);
                            break;
                        case 3:
                            buscaProduto(produtoDAO);
                            break;
                        case 4:
                            atualizaProduto(produtoDAO, fornecedorDAO);
                            break;
                        case 5:
                            if(verificaUsuario(usuarioDAO)){
                                deletaProduto(produtoDAO, estoqueDAO);
                                break;
                            }else{
                                System.out.println("Acesso negado!");
                                break;
                            }
                        case 0:
                            System.out.println("voltando...");
                            break;
                        default:
                            System.out.println("Opção inválida!");
                            break;
                        }
                }
    }

        static public void gerenciaEstoques(){
        String op = "";
        while(!op.equals("0")){
            System.out.println("MENU DE ESTOQUES");
            System.out.println("1 - Atualizar estoque");
            System.out.println("0 - Voltar ao menu principal");
            op = s.nextLine();
            switch(Integer.parseInt(op)){
                case 1:
                    atualizaEstoque(estoqueDAO);
                    break;
                case 0:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
                }
        }
    }

    static public void gerenciaFornecedores(){
        String op = "";
        while(!op.equals("0")){
            System.out.println("MENU DE FORNECEDORES");
            System.out.println("1 - Criar fornecedor");
            System.out.println("2 - Listar fornecedores");
            System.out.println("3 - Buscar fornecedor");
            System.out.println("4 - Atualizar fornecedor");
            System.out.println("5 - Deletar fornecedor");
            System.out.println("0 - Voltar ao menu principal");
            op = s.nextLine();
            switch(Integer.parseInt(op)){
                case 1:
                    criaFornecedor(fornecedorDAO);
                    break;
                case 2:
                    listaFornecedores(fornecedorDAO);
                    break;
                case 3:
                    buscaFornecedor(fornecedorDAO);
                    break;
                case 4:
                    atualizaFornecedor(fornecedorDAO);
                    break;
                case 5:
                if(verificaUsuario(usuarioDAO)){
                    deletaFornecedor(fornecedorDAO, produtoDAO);
                    break;
                }else{
                    System.out.println("Acesso negado!");
                    break;
                }
                case 0:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
                }
        }
    }
        static public void gerenciaVendas(){
        String op = "";
        while(!op.equals("0")){
            System.out.println("MENU DE VENDAS");
            System.out.println("1 - Vender");
            System.out.println("2 - Listar vendas");
            System.out.println("0 - Voltar ao menu principal");
            op = s.nextLine();
            switch(Integer.parseInt(op)){
                case 1:
                    venda(compraDAO, produtoDAO, compradorDAO);
                    break;
                case 2:
                    listaVendas(compraDAO, produtoDAO);
                    break;
                case 0:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
                }
        }
    }

    static public void gerenciaCompradores(){
        String op = "";
        while(!op.equals("0")){
            System.out.println("MENU DE COMPRADORES");
            System.out.println("1 - Criar comprador");
            System.out.println("2 - Listar compradores");
            System.out.println("3 - Buscar comprador");
            System.out.println("4 - Atualizar comprador");
            System.out.println("5 - Deletar comprador");
            System.out.println("0 - Voltar ao menu principal");
            op = s.nextLine();
            switch(Integer.parseInt(op)){
                case 1:
                    criaComprador();
                    break;
                case 2:
                    listaCompradores(compradorDAO);
                    break;
                case 3:
                    buscaComprador(compradorDAO);
                    break;
                case 4:
                    atualizaComprador(compradorDAO);
                    break;
                case 5:
                    if(verificaUsuario(usuarioDAO)){
                        deletaComprador(compradorDAO, compraDAO);
                        break;
                    }else{
                        System.out.println("Acesso negado!");
                        break;
                    }
                    
                case 0:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
                }
        }
    }

    static public void gerenciaUsuario(){
        String op = "";
        while(!op.equals("0")){
            System.out.println("MENU DO USUÁRIO");
            System.out.println("1 - Criar novo Usuario");
            System.out.println("2 - Listar Usuarios");
            System.out.println("3 - Buscar Usuario");
            System.out.println("4 - Atualizar Usuario");
            System.out.println("5 - Deletar Usuario");
            System.out.println("0 - Voltar ao menu principal");
            op = s.nextLine();
            switch(Integer.parseInt(op)){
                case 1:
                    criaUsuario(usuarioDAO);
                    break;
                case 2:
                    listaUsuario(usuarioDAO);
                    break;
                case 3:
                    buscaUsuario(usuarioDAO);
                    break;
                case 4:
                    atualizaUsuario(usuarioDAO);
                    break;
                case 5:
                        deletaUsuario(usuarioDAO);
                        break;
                case 0:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
                }
        }
    }
}