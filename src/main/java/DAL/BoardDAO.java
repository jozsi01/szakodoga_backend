package DAL;

import Model.BoardDTO;
import Model.BoardListDTO;
import Model.CreateBoardDTO;
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
public class BoardDAO {
    private final String url = new String("https://who-laugh-last.szamat.hu/api/manage/board");

    private final HttpClientFactory httpClientFactory;

    public BoardListDTO getBoards() throws Exception{
        HttpClient client = httpClientFactory.getNewHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response  = client.send(request, HttpResponse.BodyHandlers.ofString());
        try{
            String body = response.body();
            return new ObjectMapper().readValue(body, BoardListDTO.class);
        }catch(UnrecognizedPropertyException upe) {
            throw new ErrorCodeException(response.body());
        }
    }

    public BoardDTO getBoard(String id) throws Exception{
        StringBuilder modifiedURL = new StringBuilder(url);
        modifiedURL.append(String.format("/%s",id));
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(modifiedURL.toString()))
                .GET()
                .build();
        HttpClient client = httpClientFactory.getNewHttpClient();
        HttpResponse<String> response  = client.send(request, HttpResponse.BodyHandlers.ofString());
        try{
            return new ObjectMapper().readValue(response.body(),BoardDTO.class);
        }catch(UnrecognizedPropertyException upe) {
            throw new ErrorCodeException(response.body());
        }

    }

    public BoardDTO createBoard(CreateBoardDTO board) throws Exception{
        HttpClient client = httpClientFactory.getNewHttpClient();
        String requestBody = new ObjectMapper().writeValueAsString(board);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type","application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        try{
            return new ObjectMapper().readValue(response.body(),BoardDTO.class);
        }catch(UnrecognizedPropertyException upe) {
            throw new ErrorCodeException(response.body());
        }
    }

    public int deleteBoard(String id) throws Exception{
        StringBuilder modifiedURL = new StringBuilder(url);
        modifiedURL.append(String.format("/%s",id));
        HttpClient client = httpClientFactory.getNewHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(modifiedURL.toString()))
                .header("Content-Type","application/json")
                .DELETE()
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.statusCode();
    }


}
