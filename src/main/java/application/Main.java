package application;

import java.util.Scanner;

import dao.impl.TbUserDaoImpl;
import dao.impl.TbUserLoginDaoImpl;
import entity.TbUser;
import entity.TbUserLogin;

public class Main {
	private static Scanner input = new Scanner(System.in);
	private static TbUserLogin activeuserLogin = new TbUserLogin();
	private static TbUser user = new TbUser();
	private static TbUserLoginDaoImpl tulDao = new TbUserLoginDaoImpl();
	private static TbUserDaoImpl tuDao = new TbUserDaoImpl();

	public static void main(String[] args) {

		signLogIn();

		if (activeuserLogin != null) {
			if (validateUser()) {
				// usuário já existe
			} else {
				createUser();
			}

		}

	}

	private static void createUser() {

	}

	private static boolean validateUser() {
		user.setUserLogin(activeuserLogin);
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
				activeuserLogin = tul;
			} else {
				System.out.println("Dados inválidos.");
			}
		}
	}
}
