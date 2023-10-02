package ControllerTest;

import Controller.Controller;
import Model.*;
import BLL.GamePlayService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = Application.Application.class)
public class GamePlayControllerTest {
    @Mock
    GamePlayService gamePlayService ;
    @InjectMocks
    Controller controller;
    @Test
    void joinPlayer_shouldCallServiceJoinPlayerWithTheProperParameters() throws Exception{
        PlayerJoinRequestDTO playerJoinRequestDTO = new PlayerJoinRequestDTO("Blue","testName");
        controller.joinPlayer(playerJoinRequestDTO,"boardId");
        verify(gamePlayService).joinPlayer("boardId",playerJoinRequestDTO);
    }

    @Test
    void leavePlayer_shouldCallServiceLeavePlayerWithTheProperParameters() throws Exception {
        RemoveFromBoardRequestDTO removeFromBoardRequestDTO = new RemoveFromBoardRequestDTO("TestPlayerId");
        controller.leavePlayer(removeFromBoardRequestDTO,"boardId");
        verify(gamePlayService).leavePlayer("boardId",removeFromBoardRequestDTO);
    }
    @Test
    void movePlayer_shouldCallServiceMovePlayerWithTheProperParameters() throws Exception {
        MoveRequestDTO moveRequestDTO = new MoveRequestDTO("TestPlayerId","TestToken","PieceId");
        controller.movePlayer(moveRequestDTO,"boardId");
        verify(gamePlayService).movePlayer("boardId",moveRequestDTO);
    }

    @Test
    void rollADice_shouldReturnWithTheGivenParameters() throws Exception {
        RollResponseDTO rollResponseDTO = new RollResponseDTO(3,"TestToken");
        RollingRequestDTO rollingRequestDTO = new RollingRequestDTO("TestPlayerId");
        when(gamePlayService.rollADice("boardId",rollingRequestDTO)).thenReturn(rollResponseDTO);
        assertThat(controller.rollADice(rollingRequestDTO,"boardId")).isEqualTo(new ResponseEntity<>(rollResponseDTO,HttpStatusCode.valueOf(200)));
    }



}
