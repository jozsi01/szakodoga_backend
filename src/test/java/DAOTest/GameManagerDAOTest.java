package DAOTest;

import DAL.GameManagerDAO;
import DAL.HttpClientFactory;
import Model.BoardDTO;
import Model.ColorListDTO;
import BLL.ManageBoardAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameManagerDAOTest {

    GameManagerDAO gameManagerDAO;

    HttpClientFactory httpClientFactory;

    HttpClient client;
    HttpResponse response;

    @BeforeEach
    void mocking() throws Exception{
        client = mock(HttpClient.class);
        httpClientFactory = mock(HttpClientFactory.class);
        response = mock(HttpResponse.class);
        gameManagerDAO = new GameManagerDAO(httpClientFactory);
        when(httpClientFactory.getNewHttpClient()).thenReturn(client);
        when(client.send(any(),any())).thenReturn(response);

    }

    @Test
    void manageBoard_shouldReturnWithTheBoardThatTheActionIsExecutedOn() throws Exception {

        String boardId = "testId";
        String responseBody = "{\n" +
                " \"boardName\":\"testName\",\n" +
                "      \"distanceBetweenPlayers\": 2,\n" +
                "      \"id\": \""+boardId+"\",\n" +
                "      \"nextPlayerId\": \"testNextPlayerId\",\n" +
                "      \"numberOfPieces\": 3,\n" +
                "      \"numberOfPlayer\": 0,\n" +
                "      \"players\": [],\n" +
                "      \"status\": \"STARTED\"\n" +
                "    }";

        when(response.body()).thenReturn(responseBody);
        BoardDTO startedBoard = gameManagerDAO.manageBoard(boardId, ManageBoardAction.START);
        BoardDTO stoppedBoard = gameManagerDAO.manageBoard(boardId, ManageBoardAction.STOP);
        BoardDTO resetedBoard = gameManagerDAO.manageBoard(boardId, ManageBoardAction.RESET);

        assertEquals(startedBoard.getId(),boardId);
        assertEquals(stoppedBoard.getId(),boardId);
        assertEquals(resetedBoard.getId(),boardId);
    }

    @Test
    void getSupportedColors_shouldReturnWithAllTheSupportedColors() throws Exception {

        String responseBody = "{\"colors\":[\"Red\",\"Blue\",\"Orange\"]}";

        when(response.body()).thenReturn(responseBody);

        ColorListDTO colorListDTO = gameManagerDAO.getSupportedColors();

        assertEquals(colorListDTO.getColors(),new ArrayList<>(Arrays.asList("Red","Blue","Orange")));

    }

}
