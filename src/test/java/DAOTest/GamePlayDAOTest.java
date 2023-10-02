package DAOTest;

import DAL.GamePlayDAO;
import DAL.HttpClientFactory;
import Model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GamePlayDAOTest {

    GamePlayDAO gamePlayDAO;

    HttpClientFactory httpClientFactory;

    HttpClient client;
    HttpResponse response;

    @BeforeEach
    void mocking () throws Exception{
        httpClientFactory = mock(HttpClientFactory.class);
        client = mock(HttpClient.class);
        response = mock(HttpResponse.class);
        gamePlayDAO = new GamePlayDAO(httpClientFactory);
        when(httpClientFactory.getNewHttpClient()).thenReturn(client);
        when(client.send(any(),any())).thenReturn(response);
    }


    @Test
    void joinPlayer_shouldReturnedWithTheJoinedPlayer() throws Exception {

        PlayerDTO responseObject = new PlayerDTO("Blue",0,"testId","testName",null,0);
        PlayerJoinRequestDTO playerJoinRequestDTO = new PlayerJoinRequestDTO("Blue","testName");
        String responseBody = new ObjectMapper().writeValueAsString(responseObject);
        when(response.body()).thenReturn(responseBody);

        PlayerDTO playerDTO = gamePlayDAO.joinPlayer("testId",playerJoinRequestDTO);
        assertEquals(playerDTO.getId(),responseObject.getId());
    }

    @Test
    void leavePlayer_shouldReturnTheLeftBoard() throws Exception {


        BoardDTO responseObject = new BoardDTO("testName",2,"testId","nextPlayerId",0,1,null,"Created");
        String responseBody = new ObjectMapper().writeValueAsString(responseObject);
        when(response.body()).thenReturn(responseBody);

        RemoveFromBoardRequestDTO removeFromBoardRequestDTO = new RemoveFromBoardRequestDTO("TestPlayerId");
        BoardDTO boardDTO = gamePlayDAO.leavePlayer("testId",removeFromBoardRequestDTO);

        assertEquals(boardDTO.getBoardName(),responseObject.getBoardName());
    }

    @Test
    void movePlayer_shouldReturnWithTheBoardThatThePlayerMovedOn() throws Exception {

        BoardDTO responseObject = new BoardDTO("testName",2,"testId","nextPlayerId",0,1,null,"Created");
        String responseBody = new ObjectMapper().writeValueAsString(responseObject);
        when(response.body()).thenReturn(responseBody);

        MoveRequestDTO moveRequestDTO = new MoveRequestDTO("testPieceId","testPlayerId","testToken");
        BoardDTO boardDTO = gamePlayDAO.movePlayer("testId",moveRequestDTO);

        assertEquals(boardDTO.getBoardName(),responseObject.getBoardName());

    }

    @Test
    void rollADice_shouldReturnWithTheRollResponse() throws Exception {

        RollResponseDTO responseObject = new RollResponseDTO(3,"TestToken");
        String responseBody = new ObjectMapper().writeValueAsString(responseObject);
        when(response.body()).thenReturn(responseBody);

        RollingRequestDTO rollingRequestDTO = new RollingRequestDTO("testPlayerId");
        RollResponseDTO rollResponseDTO = gamePlayDAO.rollADice("testPlayerId",rollingRequestDTO);

        assertEquals(rollResponseDTO.getToken(),responseObject.getToken());
    }



}
