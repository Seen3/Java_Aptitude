import com.mongodb.client.FindIterable;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

public class ObjectConverter {
    public static String[][] convert(FindIterable<Document> iterable) {
        List<String[]> objectList = new ArrayList<>();

        for (Document document : iterable) {
            //System.out.println(document);
            String[] object = new String[6];
            object[0] = document.getString("question");
            object[1] = document.getString("option1");
            object[2] = document.getString("option2");
            object[3] = document.getString("option3");
            object[4] = document.getString("option4");
            object[5] = document.getString("optionCorrect");
            objectList.add(object);
        }

        String[][] objects = new String[objectList.size()][6];
        for (int i = 0; i < objectList.size(); i++) {
            objects[i] = objectList.get(i);
        }

        return objects;
    }
}