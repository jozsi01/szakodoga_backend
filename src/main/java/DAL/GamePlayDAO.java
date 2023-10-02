package DAL;

import Model.*;
import ErrorHandling.ErrorCodeException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GamePlayDAO {
    private final String url = new String("https://who-laugh-last.szamat.hu/api/board/");

    private final HttpClientFactory httpClientFactory;

    public PlayerDTO joinPlayer(String id,PlayerJoinRequestDTO playerJoinRequestDTO) throws Exception{
        StringBuilder modifiedURL = new StringBuilder(url);
        modifiedURL.append(String.format("/%s/join",id));
        String requestBody = new ObjectMapper().writeValueAsString(playerJoinRequestDTO);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(modifiedURL.toString()))
                .header("Content-Type","application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        HttpClient client = httpClientFactory.getNewHttpClient();
        HttpResponse<String> response  = client.send(request, HttpResponse.BodyHandlers.ofString());
        try{
            return new ObjectMapper().readValue(response.body(),PlayerDTO.class);
        }catch(UnrecognizedPropertyException upe) {
            throw new ErrorCodeException(response.body());
        }
    }

    public BoardDTO leavePlayer(String id, RemoveFromBoardRequestDTO removeFromBoardRequestDTO) throws Exception{
        StringBuilder modifiedURL = new StringBuilder(url);
        modifiedURL.append(String.format("/%s/leave",id));
        String requestBody = new ObjectMapper().writeValueAsString(removeFromBoardRequestDTO);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(modifiedURL.toString()))
                .header("Content-Type","application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        HttpClient client = httpClientFactory.getNewHttpClient();
        HttpResponse<String> response  = client.send(request, HttpResponse.BodyHandlers.ofString());
        try{
            return new ObjectMapper().readValue(response.body(),BoardDTO.class);
        }catch(UnrecognizedPropertyException upe) {
            throw new ErrorCodeException(response.body());
        }
    }

    public BoardDTO movePlayer(String id, MoveRequestDTO moveRequestDTO) throws Exception{
        StringBuilder modifiedURL = new StringBuilder(url);
        modifiedURL.append(String.format("/%s/move",id));
        String requestBody = new ObjectMapper().writeValueAsString(moveRequestDTO);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(modifiedURL.toString()))
                .header("Content-Type","application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        HttpClient client = httpClientFactory.getNewHttpClient();
        HttpResponse<String> response  = client.send(request, HttpResponse.BodyHandlers.ofString());
        try{
            return new ObjectMapper().readValue(response.body(),BoardDTO.class);
        }catch(UnrecognizedPropertyException upe) {
            throw new ErrorCodeException(response.body());
        }
    }

    public RollResponseDTO rollADice(String id, RollingRequestDTO rollingRequestDTO) throws Exception{
        StringBuilder modifiedURL = new StringBuilder(url);
        modifiedURL.append(String.format("/%s/roll-a-dice",id));
        String requestBody = new ObjectMapper().writeValueAsString(rollingRequestDTO);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(modifiedURL.toString()))
                .header("Content-Type","application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        HttpClient client = httpClientFactory.getNewHttpClient();
        HttpResponse<String> response  = client.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() == 202) {
            throw new ErrorCodeException(new String("{\"errorCode\":\"error_cannot_move_with_the_thrown_number\"}"));
        }
        else {
            try{
                return new ObjectMapper().readValue(response.body(),RollResponseDTO.class);
            }catch(UnrecognizedPropertyException upe) {
                throw new ErrorCodeException(response.body());
            }
        }

    }

}
