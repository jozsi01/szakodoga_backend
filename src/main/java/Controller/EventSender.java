package Controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Component
public class EventSender {

    public List<SseEmitter> emmitters = new CopyOnWriteArrayList<SseEmitter>();

    @RequestMapping("/subscribe")
    public SseEmitter subscribe() {
        System.out.println("Subscripbed");
        SseEmitter emmitter = new SseEmitter(Long.MAX_VALUE);
        try{
            emmitter.send(SseEmitter.event().name("INIT"));
        }catch (IOException e) {
            e.printStackTrace();
        }
        emmitter.onCompletion(() -> emmitters.remove(emmitter));
        emmitters.add(emmitter);
        return emmitter;

    }
    public void sendEvents(String eventname, Object data){
        for(SseEmitter emitter : emmitters){
            try{
                emitter.send(SseEmitter.event().name(eventname).data(data));
            }catch(IOException e){
                emmitters.remove(emitter);
            }
        }
    }
}
