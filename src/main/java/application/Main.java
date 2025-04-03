package application;

import java.util.Scanner;

import dao.impl.TbUserDaoImpl;
import dao.impl.TbUserLoginDaoImpl;
import entity.TbUser;
import entity.TbUserLogin;

public class Main {
	private static Scanner input = new Scanner(System.in);
	private static TbUserLogin activeUserLogin;
	private static TbUser user = new TbUser();
	private static TbUserLoginDaoImpl tulDao = new TbUserLoginDaoImpl();
	private static TbUserDaoImpl tuDao = new TbUserDaoImpl();

	public static void main(String[] args) {

		signLogIn();

		if (activeUserLogin != null) {
			if (validateUser()) {
				//operações da conta do user -> criar método
			}else {
				createUser();
			}
		}

	}

	private static void createUser() {
		System.out.println("Adicione suas informações de usuário!");
		
		System.out.println("\nDigite seu NOME ->");
		String name = input.nextLine();
		
		System.out.println("\nDigite sua IDADE ->");
		Integer age = input.nextInt();
		input.nextLine();
		
		System.out.println("\nDigite seu PESO ->");
		Double weight = input.nextDouble();
		input.nextLine();
		
		System.out.println("\nDigite sua ALTURA ->");
		Double height = input.nextDouble();
		input.nextLine();
		
		System.out.println("\nDigite seu GENÊRO ->");
		String gender = input.nextLine();
		
		TbUser user = new TbUser(null, activeUserLogin, name, age, weight, height, gender);
		tuDao.insert(user);
		
		System.out.println("Usuário criado!");
	}

	private static boolean validateUser() {
		user.setUserLogin(activeUserLogin);
		user = tuDao.findByUSer(user);
		if (user != null) {
			System.out.printf("Bem vindo novamente, %s!", user.getNameString());
			return true;
		}
		return false;
	}

	public static void signLogIn() {
		System.out.println("Digite -> (1) para se cadastrar | (2) para entrar na sua conta: ");
		int opt = input.nextInt();
		input.nextLine();

		if (opt == 1) {
			System.out.println("Cadastrar!\n");
			System.out.println("Digite o seu NOME DE USUÁRIO: ->");
			String userName = input.nextLine();

			System.out.println("Digite a sua SENHA: ->");
			String passWord = input.nextLine();

			TbUserLogin tul = new TbUserLogin(userName, passWord);
			tulDao.insert(tul);

			System.out.println("Conta criada!");
			opt = 2;
		}

		if (opt == 2) {
			System.out.println("Entrar na sua conta!\n");
			System.out.println("Digite o seu NOME DE USUÁRIO: ->");
			String userName = input.nextLine();

			System.out.println("Digite a sua SENHA: ->");
			String passWord = input.nextLine();

			TbUserLogin tul = tulDao.findByLogin(userName);

			if (tul != null && tul.getDsPassword().equalsIgnoreCase(passWord)) {
				activeUserLogin = tul;
			} else {
				System.out.println("Dados inválidos.");
			}
		}
	}
}
