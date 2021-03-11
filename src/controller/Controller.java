import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class Controller {

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String getRoot(){
        return "<h1>I am root</h1>";
    }

    @GetMapping(path = "/hello")
    public String getHello(){
        return "<h1>Hi.How are you?Do you like a Java?</h1>";
    }

    @RequestMapping("/time")
    public String currentTime(){
        return new Date().toString();
    }

    @GetMapping(path = "/hello/sk")
    public String getHelloSk(){
        return "<h1>Ahoj, ako sa mas ty Java expert?</h1>";
    }

    @RequestMapping(path = "/hi", method = RequestMethod.GET)
    public String getHi(){
        return "<h2>Hi user, what are you doing?</h2>";
    }

    @RequestMapping(path = "/data", method = RequestMethod.POST)
    public String getHiTest(@RequestBody String text){
        return "<h2>This is a test page. Your name is: "+text;
    }
    @RequestMapping(path = "/hi/{username}")
    public String getHiWithName(@PathVariable String username){
        return "<h2>Hi "+username+", what are you doing?</h2>";
    }
    @RequestMapping(path = "/hi/calc")
    public String getCalcValue(@PathVariable String username){

        return "";
    }

}
