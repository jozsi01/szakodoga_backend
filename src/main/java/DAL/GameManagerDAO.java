package DAL;

import Model.BoardDTO;
import Model.ColorListDTO;
import ErrorHandling.ErrorCodeException;
import BLL.ManageBoardAction;
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
public class GameManagerDAO {

    private final HttpClientFactory httpClientFactory;
    private final String url = new String("https://who-laugh-last.szamat.hu/api/manage/board/");

    public BoardDTO manageBoard(String boardId, ManageBoardAction action) throws Exception {
        StringBuilder modifiedURL = new StringBuilder(url);
        modifiedURL.append(String.format("/%s/%s",boardId,action.getAction()));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(modifiedURL.toString()))
                .PUT(HttpRequest.BodyPublishers.ofString(boardId))
                .build();
        HttpClient client = httpClientFactory.getNewHttpClient();
        HttpResponse<String> response  = client.send(request, HttpResponse.BodyHandlers.ofString());

        try{
            return new ObjectMapper().readValue(response.body(),BoardDTO.class);
        }catch(UnrecognizedPropertyException upe) {
            throw new ErrorCodeException(response.body());
        }
    }

    public ColorListDTO getSupportedColors() throws Exception {
        StringBuilder modifiedURL = new StringBuilder(url);
        modifiedURL.append("supported-colors");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(modifiedURL.toString()))
                .GET()
                .build();
        HttpClient client = httpClientFactory.getNewHttpClient();
        HttpResponse<String> response  = client.send(request, HttpResponse.BodyHandlers.ofString());
        try{
            return new ObjectMapper().readValue(response.body(),ColorListDTO.class);
        }catch(UnrecognizedPropertyException upe) {
            throw new ErrorCodeException(response.body());
        }
    }



}
