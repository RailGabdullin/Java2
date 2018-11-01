import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Phonebook {

    HashMap<Integer, String> phonebook = new HashMap<>();

    public Phonebook(){
    }

    public ArrayList<Integer> get(String _name){
        //Проходимся по хешмапе и находим соответсвия значений (имен) запросу.
        // Если находим - запрашиваем ключь к этому значению и добавляем в эрейлист как новый элемент.
        // Метод возвращает созданные таким образом эрейлист - найденных ключей, соответсвующих запросу
        ArrayList<Integer> results = new ArrayList<>();
        for (Map.Entry<Integer, String> i : phonebook.entrySet()) {
            if (_name.equalsIgnoreCase(i.getValue())) {
                results.add(i.getKey());
            }
        }
        return results;

    }

    public void add(String _name, Integer _number){
        phonebook.put(_number, _name);
    }

}
