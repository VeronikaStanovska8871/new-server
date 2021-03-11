import jdk.nashorn.internal.parser.JSONParser;
import net.minidev.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
public class MathController {

    @PostMapping(path = "/add")
    public ResponseEntity<String> addTwoNumbers(@RequestBody String input){

        try {
            JSONObject object = (JSONObject) new JSONParser().parse(input);
            int a = Integer.parseInt(String.valueOf(object.get("a")));
            int b = Integer.parseInt(String.valueOf(object.get("b")));

            int result = a+b;
            JSONObject res=new JSONObject();
            res.put("result",result);

            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(res.toJSONString());

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (NumberFormatException e){
            JSONObject obj = new JSONObject();
            obj.put("error","Incorrect body of the request");
            return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body(obj.toJSONString());
        }
        return null;
    }

    @GetMapping("/mul")
    public ResponseEntity<String> multiply(@RequestParam(value="a") int value1, @RequestParam(value="b") int value2){
        int result = (value1*value2);
        JSONObject obj = new JSONObject();
        obj.put("result",result);

        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(obj.toJSONString());
    }
}