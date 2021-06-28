package aplicativos;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import classes.Pessoa;
import classes.Pilotos;


public class AppPilotos {
   

    public static void main(String[] args) throws InterruptedException, IOException {
        final int MAX_ELEMENTOS = 20;
        int opcao, qtdCadastrados = 0;
        final int MAX_ERROS_CPF = 3;
        Pessoa[] pilotos = new Pessoa[MAX_ELEMENTOS];
        Scanner in = new Scanner(System.in);
         

        do {
            System.out.println("\n****\nMENU\n****\n");
            System.out.println("1 - Cadastrar piloto");
            System.out.println("2 - Listar pilotos cadastrados");
            System.out.println("3 - Localizar piloto pelo CPF");
            System.out.println("4 - Aumentar espaço de armazenamento");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            opcao = in.nextInt();
            in.nextLine(); // Tira o ENTER que ficou na entrada na instrução anterior

            if (opcao == 1) {
                // Se não tem mais espaço no vetor, caio fora
                if (qtdCadastrados == MAX_ELEMENTOS) {
                    System.out.println("\nNão há espaço para cadastrar novos pilotos.");
                    voltarMenu(in);
                    continue;
                }

                //Cadastre seu piloto aqui

                Pilotos pilo = new Pilotos();
             
                System.out.print("Digite o nome do piloto: ");
                pilo.setNome(in.nextLine());

                System.out.print("Digite o  numero da Habilitação: ");
                pilo.setHabilitacao(in.nextLine());

                System.out.print("Digite o nome da Aeronave: ");
                pilo.setNomeNave(in.nextLine());

                int numVezes = 0;
                do {
                    try {
                        System.out.print("Digite o CPF: ");
                        pilo.setCpf(in.nextLine());
                    } catch (InputMismatchException ex) {
                        System.out.println(ex.getMessage() + " Tente novamente.");
                        numVezes += 1;
                    }
                } while (pilo.getCpf() == null && numVezes < MAX_ERROS_CPF);

                // Se CPF está nulo, é porque errou as MAX_ERROS_CPF vezes. Assim, fim do programa.

                if (pilo.getCpf() == null) {
                    System.out.printf("Você errou o CPF %d vezes. O programa será encerrado.", numVezes);
                    return;
                }

                pilotos[qtdCadastrados] = pilo;
                qtdCadastrados = qtdCadastrados + 1;  


                System.out.println("\nPiloto cadastrado com sucesso.");
                voltarMenu(in);


            } else if (opcao == 2) {
                // Se não tem ninguém cadastrado no vetor, caio fora
                if (qtdCadastrados == 0) {
                    System.out.println("\nNão há m cadastrados para exibir.");
                    voltarMenu(in);
                    continue;
                }

                // Exiba os pilotos aqui

                for (int i = 0; i < qtdCadastrados; i++) {
                    System.out.printf("\nPiloto %d: %s\n", (i + 1), pilotos[i].getNome());
                    System.out.printf("CPF: %s\n", pilotos[i].getCpf());
                    System.out.printf("Habilitação: %s\n", ((Pilotos) pilotos[i]).getHabilitacao());
                    System.out.printf("Aeronave: %s\n " , ((Pilotos) pilotos[i]).getNomeNave());
                }

                // pesquise um piloto pelo cpf


                voltarMenu(in);
            } else if (opcao == 3) {

                System.out.print("Consulta o CPF: ");
                String cpf = (in.nextLine());
                
                for (int i = 0; i < qtdCadastrados; i++) {
                    if(cpf.equals(pilotos[i].getCpf())){
                    System.out.printf("\n Piloto Encontrado \n");    
                    System.out.printf("\nPiloto %d: %s\n", (i + 1), pilotos[i].getNome());
                    System.out.printf("CPF: %s\n", pilotos[i].getCpf());
                    System.out.printf("Habilitação: %s\n", ((Pilotos) pilotos[i]).getHabilitacao());
                    System.out.printf("Aeronave: %s\n " , ((Pilotos) pilotos[i]).getNomeNave());

                }else{
                    System.out.println("Piloto não Cadastrado"); 
                    voltarMenu(in);
                }
            }
                

                voltarMenu(in);
                

            } else if (opcao == 4) {
            System.out.printf("Digite um novo tamanho de armazenamento maior que : " + MAX_ELEMENTOS + ".");
            int tamanhoVetor = in.nextInt();
            Pessoa[] novoVetor = new Pessoa[tamanhoVetor];
            int posicao = 0;
            for(Pessoa pessoa:pilotos){
            if(tamanhoVetor > 0){           
            novoVetor[posicao] = pessoa;
            posicao++;
             }
            }
            
        }
            else if (opcao != 0) {
                System.out.println("\nOpção inválida!");
            }
        } while (opcao != 0);

        System.out.println("Fim do programa!");

        in.close();
    }

    private static void voltarMenu(Scanner in) throws InterruptedException, IOException {
        System.out.println("\nPressione ENTER para voltar ao menu.");
        in.nextLine();

        // Limpa toda a tela, deixando novamente apenas o menu
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            System.out.print("\033[H\033[2J");
        
        System.out.flush();
    }
}