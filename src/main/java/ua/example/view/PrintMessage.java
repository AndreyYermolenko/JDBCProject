package ua.example.view;

public class PrintMessage {
    public static void printMainMenu() {
        System.out.println("1. Вывести информацию о работнике." + "\n"
                        + "2. Добавить работника." + "\n"
                        + "3. Удалить работника." + "\n"
                        + "Нажмите Enter для завершения работы." + "\n"
                        + "Ваш ввод: ");
    }

    public static void printInfoEmployeeMenu() {
        System.out.println("Введите ID работника." + "\n"
                + "Ваш ввод:");
    }

    public static void printAddEmployeeMenu() {
        System.out.println("Введите данные о работнике в следующем порядке." + "\n"
                + "После каждого ввода нажимайте Enter." + "\n"
                + "ID, lastname, job, manager id (не обязательно), hiredate (dd-MM-yyyy), salary, commission (не обязательно), department id.");
    }

    public static void printAddEmployeeSuccessful() {
        System.out.println("Данные о работнике успешно добавлены в БД!");
    }

    public static void printDeleteEmployeeSuccessful() {
        System.out.println("Данные о работнике успешно удалены из БД!");
    }

    public static void printErrorSqlRequest() {
        System.err.println("Ошибка ответа БД! Возможно введены неверные данные.");
    }
    public static void printToContinue() {
        System.out.println("Нажмите любую кнопку для продолжения...");
    }

    public static void printToCorrectInput() {
        System.err.println("Сделайте правильный ввод!");
    }
}
