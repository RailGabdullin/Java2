import java.util.ArrayList;

public class MainClass {
    public static void main(String[] args) {

        //Част 1. Массив имен

        ArrayList<String> stringList = new ArrayList<String>();
        stringList.add("Саша");
        stringList.add("Петя");
        stringList.add("Вася");
        stringList.add("Вася");
        stringList.add("Маша");
        stringList.add("Катя");
        stringList.add("Таня");
        stringList.add("Соня");
        stringList.add("Маша");
        stringList.add("Даша");
        stringList.add("Соня");
        stringList.add("Света");
        stringList.add("Тема");
        stringList.add("Соня");

        //Проходимся по массиву, поочередно сравнивая каждый элемент со всеми остальными кроме него самого.
        //Если находим дубль, то плюсуем счетчик, повторы выпиливаем
        //В конце кадого цикла выводим имя и счетчик. Так как повторы выпилиываются - в итоге список уникальных имен.
        for (int i = 0; i < stringList.size(); i++) {
            int click = 1;
            for (int j = 0; j < stringList.size(); j++) {
                if ((i != j) && (stringList.get(j).equalsIgnoreCase(stringList.get(i)))){
                    click ++;
                    stringList.remove(j);
                }
            }
            System.out.println(stringList.get(i) + " " + click);
        }

        //Част 2. Телефонный справочник

//На самом деле здесь номера телефонов это ключи в хешмапе, так как они уникальны, а
// имена - это значения, так как они могут повторяться
        Phonebook phonebook = new Phonebook();
        phonebook.add("Иванов", 113514321);
        phonebook.add("Петров", 91234152);
        phonebook.add("Боширов", 687948742);
        phonebook.add("Семушкин", 64834561);
        phonebook.add("Котиков", 385609);
        phonebook.add("Боширов", 5263451);

        System.out.println(phonebook.get("Петров"));
        System.out.println(phonebook.get("Боширов"));
    }
}
