package application;

import java.util.Scanner;

import dao.impl.TbUserLoginDaoImpl;
import entity.TbUserLogin;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);	
		TbUserLogin activeuserLogin = new TbUserLogin();
		TbUserLoginDaoImpl tulDao = new TbUserLoginDaoImpl();
		
		System.out.println("Digite a opção desejada: ");
		System.out.println("1 - CREATE");
		System.out.println("2 - READ");
		System.out.println("3 - UPDATE");
		System.out.println("4 - DELETE");
		System.out.println("0 - SAIR");

		int opt = input.nextInt();
		input.nextLine();
		
		while (opt != 0) {
			switch (opt) {
			case 1:
				System.out.println("Digite o seu LOGIN e SENHA->");
				TbUserLogin tul = new TbUserLogin(input.nextLine(), input.nextLine());
				System.out.println(tul);
				tulDao.insert(tul);
				activeuserLogin = tul;
				break;
			case 2:
				System.out.println("Digite o seu LOGIN -> ");
				String dsLogin = input.nextLine();
				TbUserLogin readTbUserLogin = tulDao.findByLogin(dsLogin);
				System.out.println(readTbUserLogin);				
				break;
			case 3:
				System.out.println("Deseja atualizar qual campo? (1) LOGIN | (2) SENHA | (3) AMBOS");
				if ((input.nextInt()) == 1) {
					input.nextLine();
					
					System.out.println("Digite o LOGIN: ");
					activeuserLogin.setDsLogin(input.nextLine());
				} else if ((input.nextInt()) == 2) {
					System.out.println("Digite a SENHA: ");
					activeuserLogin.setDsPassword(input.nextLine());
				} else {
					System.out.println("Digite o LOGIN: ");
					activeuserLogin.setDsLogin(input.nextLine());
					System.out.println("Digite a SENHA: ");
					activeuserLogin.setDsPassword(input.nextLine());
				}
				tulDao.update(activeuserLogin);
				break;
			case 4:
				tulDao.delete(activeuserLogin.getCdLogin());
				break;					
			default:
				System.out.println("saindo...");
			}
			
			if (opt != 0) {
				System.out.println("Digite a opção desejada: ");
				System.out.println("1 - CREATE");
				System.out.println("2 - READ");
				System.out.println("3 - UPDATE");
				System.out.println("4 - DELETE");
				System.out.println("0 - SAIR");
				
				opt = input.nextInt();
				input.nextLine();
				//TODO quando opt = 0, fecha em e emf
			}
		}
	}
}
