package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Scanner;

import dao.impl.TbDailyCaloriesDaoImpl;
import dao.impl.TbDailyExerciseDaoImpl;
import dao.impl.TbMonthCaloriesGoalDaoImpl;
import dao.impl.TbUserDaoImpl;
import dao.impl.TbUserLoginDaoImpl;
import entity.TbDailyCalories;
import entity.TbDailyExercise;
import entity.TbMonthCaloriesGoal;
import entity.TbUser;
import entity.TbUserLogin;

public class Main {
	private static Scanner input = new Scanner(System.in);
	private static TbUserLogin activeUserLogin;
	private static TbUser user = new TbUser();
	private static List<TbDailyCalories> listCalories = new ArrayList<TbDailyCalories>();

	private static TbUserLoginDaoImpl tulDao = new TbUserLoginDaoImpl();
	private static TbUserDaoImpl tuDao = new TbUserDaoImpl();
	private static TbMonthCaloriesGoalDaoImpl tmcDao = new TbMonthCaloriesGoalDaoImpl();
	private static TbDailyCaloriesDaoImpl tdcDao = new TbDailyCaloriesDaoImpl();
	private static TbDailyExerciseDaoImpl tdeDao = new TbDailyExerciseDaoImpl();
	private static DateTimeFormatter fmt;

	public static void main(String[] args) {

		signLogIn();

		if (activeUserLogin != null) {
			if (validateUser()) {
				menu();
			} else {
				insertUserData();
				menu();
			}
		}
	}

	private static void menu() {
		int opt = 0;
		int opr = 0;
		System.out.println("\nBem vindo ao seu Registrador de Calorias!");
		System.out.println("\nQual operação deseja realizar?");
		System.out.println("1 -> Cadastrar Caloria");
		System.out.println("2 -> Atualizar Caloria");
		System.out.println("3 -> Deletar Caloria");
		System.out.println("4 -> Adicionar Caloria Queimada");
		System.out.println("5 -> Dados e Estatísticas");
		// criar métodos -> estatística de hoje, semanal e mensal
		opr = input.nextInt();
		if (opr == 5) {
			System.out.println("Escolha uma opção -> ");
			System.out.println("1 -> Dados e Estatísticas de HOJE");
			System.out.println("2 -> Dados e Estatísticas da SEMANA");
			System.out.println("3 -> Dados e Estatísticas do MÊS");
			opt = input.nextInt();
		}

		operations(opr, opt);
	}

	private static void operations(int opr, int opt) {
		Optional<TbDailyCalories> tbDailyCalories;

		switch (opr) {
		case 1:
			System.out.println("Digite a quantidade de calorias: ");
			Integer quantity = input.nextInt();
			TbDailyCalories calories = new TbDailyCalories(null, LocalDate.now(), quantity, user);
			if (tdcDao.insert(calories)) {
				System.out.println("Caloria adicionada!");
			}
			break;
		case 2:
			System.out.println("Suas lista de calorias -> ");
			listCalories = tdcDao.findAll(user);

			listCalories.forEach(System.out::println);

			System.out.println("\nDigite o código do registro que deseja atualizar ->");
			int valueAtt = input.nextInt();
			tbDailyCalories = listCalories.stream().filter(c -> c.getCdDailyCalories() == valueAtt).findFirst();

			System.out.println("Digite a nova quantidade de calorias ->");
			tbDailyCalories.get().setQtCalories(input.nextInt());
			
			if (tdcDao.update(tbDailyCalories.get())) {
				System.out.println("Caloria atualizada!");
			}
			break;
		case 3:
			System.out.println("Suas lista de calorias -> ");
			listCalories = tdcDao.findAll(user);

			listCalories.forEach(System.out::println);

			System.out.println("\nDigite o código do registro que deseja deletar ->");
			int valueDel = input.nextInt();
			tbDailyCalories = listCalories.stream().filter(c -> c.getCdDailyCalories() == valueDel).findFirst();

			if (tdcDao.delete(tbDailyCalories.get().getCdDailyCalories())) {
				System.out.println("Caloria deletada!");
			}
			break;
		case 4:
			System.out.println("Digite a quantidade de calorias queimadas -> ");
			
			TbDailyExercise dailyExercise = new TbDailyExercise(null, LocalDate.now(), input.nextInt(), user);
			
			if (tdeDao.insert(dailyExercise)) {
				System.out.println("Calorias queimadas adicionadas!");
			}
			break;
		default:
			break;
		}

	}

	private static void insertUserData() {
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

		if (tuDao.insert(user)) {
			Main.user = user;

			System.out.println("\nDigite a sua meta de caloria diária ->");
			Integer quantity = input.nextInt();
			input.nextLine();
			// validar se o user é o que acabamos de persistir acima
			TbMonthCaloriesGoal monthCaloriesGoal = new TbMonthCaloriesGoal(null,
					LocalDate.now().getMonth().getDisplayName(TextStyle.FULL, new Locale("pt", "BR")), quantity, 1,
					user);
			tmcDao.insert(monthCaloriesGoal);

			System.out.println("Informações de Usuário Adicionadas!");
		} else {
			System.out.println("Não foi possível criar o usuário.");
		}
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

			if (tulDao.insert(tul)) {
				System.out.println("Conta criada!");
				opt = 2;
			} else {
				System.out.println("Erro ao criar conta! Verifique os dados inseridos e tente novamente.");
			}

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
