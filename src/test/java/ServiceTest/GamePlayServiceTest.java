package ServiceTest;

import DAL.GamePlayDAO;
import Model.MoveRequestDTO;
import Model.PlayerJoinRequestDTO;
import Model.RemoveFromBoardRequestDTO;
import Model.RollingRequestDTO;
import BLL.GamePlayService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class GamePlayServiceTest {

    @Mock
    GamePlayDAO gamePlayDAO;

    @InjectMocks
    GamePlayService gamePlayService;

    @Test
    void joinPlayer_shouldCallGamePlayDAOJoinPlayerMethod() throws Exception {
        PlayerJoinRequestDTO playerJoinRequestDTO = new PlayerJoinRequestDTO("Blue","testName");
        gamePlayService.joinPlayer("playerId",playerJoinRequestDTO);
        verify(gamePlayDAO).joinPlayer("playerId",playerJoinRequestDTO);
    }

    @Test
    void leavePlayer_shouldCallGamePlayDAOLeavePlayerMethod() throws Exception {
        RemoveFromBoardRequestDTO removeFromBoardRequestDTO = new RemoveFromBoardRequestDTO("testPlayerId");
        gamePlayService.leavePlayer("playerId",removeFromBoardRequestDTO);
        verify(gamePlayDAO).leavePlayer("playerId",removeFromBoardRequestDTO);
    }

    @Test
    void joinPlayer_shouldCallGamePlayDAOMovePlayerMethod() throws Exception {
        MoveRequestDTO moveRequestDTO = new MoveRequestDTO("testPieceId","playerId","testToken");
        gamePlayService.movePlayer("playerId",moveRequestDTO);
        verify(gamePlayDAO).movePlayer("playerId",moveRequestDTO);
    }

    @Test
    void joinPlayer_shouldCallGamePlayDAORollADicePlayerMethod() throws Exception {
        RollingRequestDTO rollingRequestDTO = new RollingRequestDTO("playerId");
        gamePlayService.rollADice("playerId",rollingRequestDTO);
        verify(gamePlayDAO).rollADice("playerId",rollingRequestDTO);
    }
}
