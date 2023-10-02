package DAOTest;

import DAL.BoardDAO;
import DAL.HttpClientFactory;
import Model.BoardDTO;
import Model.BoardListDTO;
import Model.CreateBoardDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class BoardDAOTest {


    HttpResponse response;
    HttpClient client;

    HttpClientFactory httpClientFactory;

    BoardDAO boardDAO;

    @BeforeEach
    void mocking() throws Exception{
        client = mock(HttpClient.class);
        httpClientFactory = mock(HttpClientFactory.class);
        response = mock(HttpResponse.class);
        boardDAO = new BoardDAO(httpClientFactory);
        when(httpClientFactory.getNewHttpClient()).thenReturn(client);
        when(client.send(any(),any())).thenReturn(response);
    }
    @Test
    void getBoards_shouldReturnWithTheProperValue() throws Exception {

        String responseBody = "{\n" +
                "  \"boards\": [\n" +
                "    {\n" +
                "      \"boardName\": \"testName\",\n" +
                "      \"distanceBetweenPlayers\": 2,\n" +
                "      \"id\": \"BoardId1\",\n" +
                "      \"nextPlayerId\": \"testNextPlayerId\",\n" +
                "      \"numberOfPieces\": 3,\n" +
                "      \"numberOfPlayer\": 0,\n" +
                "      \"players\": [],\n" +
                "      \"status\": \"STARTED\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        when(response.body()).thenReturn(responseBody);

        BoardListDTO boardList = boardDAO.getBoards();

        assertEquals(1, boardList.getBoards().size()); // change this line to check the size of the list to 1
        assertEquals("testName", boardList.getBoards().get(0).getBoardName()); // change this line to check the boardName to "testName"
    }

    @Test
    void getBoard_shouldReturnABoardWithTheGivenId() throws Exception {

        String boardId = "testBoardId";
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

        BoardDTO boardDTO = boardDAO.getBoard(boardId);

        assertEquals(boardId, boardDTO.getId());
    }

    @Test
    void createBoard_shouldReturnWithTheCreatedBoard() throws Exception {

        CreateBoardDTO createBoardDTO = CreateBoardDTO.builder()
                        .boardName("testName")
                        .numberOfPieces(2)
                        .numberOfPlayer(3)
                        .distanceBetweenPlayers(4)
                        .build();
        String responseBody = "{\n" +
                " \"boardName\":\"testName\",\n" +
                "      \"distanceBetweenPlayers\": 2,\n" +
                "      \"id\": \"testId\",\n" +
                "      \"nextPlayerId\": \"testNextPlayerId\",\n" +
                "      \"numberOfPieces\": 3,\n" +
                "      \"numberOfPlayer\": 0,\n" +
                "      \"players\": [],\n" +
                "      \"status\": \"STARTED\"\n" +
                "    }";

        when(response.body()).thenReturn(responseBody);

        BoardDTO boardDTO = boardDAO.createBoard(createBoardDTO);

        assertEquals(createBoardDTO.getBoardName(), boardDTO.getBoardName());
    }

    @Test
    void deleteBoard_shouldReturnWithStatusCode204() throws Exception {
        when(response.statusCode()).thenReturn(204);

        int deleteStatus = boardDAO.deleteBoard("TestId");
        assertEquals(204,deleteStatus);
    }

}

