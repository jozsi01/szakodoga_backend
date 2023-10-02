package ServiceTest;

import DAL.GameManagerDAO;
import BLL.GameManagerService;
import BLL.ManageBoardAction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class GameManagerServiceTest {

    @Mock
    GameManagerDAO gameManagerDAO;

    @InjectMocks
    GameManagerService gameManagerService;

    @Test
    void resetBoard_shouldCallGameManagerDAOResetBoardWithTheProperParameters() throws Exception{
        gameManagerService.resetBoard("boardId");
        verify(gameManagerDAO).manageBoard("boardId", ManageBoardAction.RESET);
    }

    @Test
    void resetBoard_shouldCallGameManagerDAOStartBoardWithTheProperParameters() throws Exception{
        gameManagerService.startBoard("boardId");
        verify(gameManagerDAO).manageBoard("boardId", ManageBoardAction.START);
    }
    @Test
    void resetBoard_shouldCallGameManagerDAOStopBoardWithTheProperParameters() throws Exception{
        gameManagerService.stopBoard("boardId");
        verify(gameManagerDAO).manageBoard("boardId", ManageBoardAction.STOP);
    }

    @Test
    void getSupportedColors_shouldCallGameManagerDAOGetSupportedColorsMethod() throws Exception {
        gameManagerService.getSupportedColors();
        verify(gameManagerDAO).getSupportedColors();
    }


}
