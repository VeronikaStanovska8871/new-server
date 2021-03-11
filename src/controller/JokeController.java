import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class JokeController {
    String joke1 = "If I wanted to kill myself, I would climb your ego and jump to your IQ";
    String joke2 = "Always remember: You’re just as unique as everybody else.";
    String joke3 = "My son, who's into astronomy, asked me how stars die. \"Usually an overdose, son,\" I told him.";
    List<String> list = new ArrayList<>();

    public JokeController(){
        list.add(joke1);
        list.add(joke2);
        list.add(joke3);
    }


    @GetMapping("/jokes")
    public ResponseEntity<String> getJokes(){
        JSONObject object = new JSONObject();
        JSONArray jArray = new JSONArray();
        for(String s : list)
            jArray.add(s);
        object.put("jokes",jArray);
        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(object.toJSONString());

    }

    @GetMapping("/joke/{id}")
    public ResponseEntity<String> getJokeById(@PathVariable Integer id){
        JSONObject object = new JSONObject();
        int status;
        if(id<1 || id>list.size()){
            // incorrect id
            object.put("error","Invalid id");
            status = 404;
        }else {
            object.put("joke", list.get(id - 1));
            status = 200;
        }
        return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).body(object.toJSONString());
    }
    @GetMapping("/joke")
    public ResponseEntity<String> getRandomJoke(){

        JSONObject object = new JSONObject();
        int status;
        if(list.size()==0){
            // incorrect id
            object.put("error","No joke in database");
            status = 404;
        }else {
            Random random = new Random();
            int index = random.nextInt(list.size());
            object.put("id",index);
            object.put("joke", list.get(index));
            status = 200;
        }
        return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).body(object.toJSONString());
    }
}