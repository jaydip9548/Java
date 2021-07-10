package Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Hash_Map {
    public static void main(String[] args) {
        Map<String,String> phonebook = new HashMap<>();
        phonebook.put("Jaydip","8347375611");
        phonebook.put("Kishan","6353631020");
        phonebook.put("Hardik","9712294947");
        phonebook.put("papa","9428474103");

//        System.out.println(phonebook.get("Jaydip"));


//        Set<String> keys = phonebook.keySet();
//        System.out.println(keys);
//        for (String i : keys){
//            System.out.println(i +" "+phonebook.get(i));
//        }

        Set<Map.Entry<String,String>> values = phonebook.entrySet();
        for (Map.Entry<String,String> e : values){
            System.out.println(e.getKey() +" : "+e.getValue());

        }
    }
}
