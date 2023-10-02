package ServiceTest;

import DAL.BoardDAO;
import Model.CreateBoardDTO;
import BLL.BoardService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BoardServiceTest {

    @Mock
    BoardDAO boardDAO;

    @InjectMocks
    BoardService boardService;

    @Test
    void getBoards_shouldCallBoardDaoGetBoardsFunction() throws Exception{
        boardService.getBoards();
        verify(boardDAO).getBoards();
    }

    @Test
    void getBoard_shouldCallBoardDAOGetBoardFunctionWithTheProperParameter() throws Exception {
        boardService.getBoard("TestId");
        verify(boardDAO).getBoard("TestId");
    }

    @Test
    void createBoard_shouldCallBoardDAOCreateBoardFunctionWithTheRightParameter() throws Exception {
        CreateBoardDTO createBoardDTO = CreateBoardDTO.builder()
                .boardName("Test")
                .distanceBetweenPlayers(2)
                .numberOfPieces(2)
                .numberOfPlayer(2)
                .build();
        boardService.createBoard(createBoardDTO);
        verify(boardDAO).createBoard(createBoardDTO);
    }

    @Test
    void deleteBoard_shouldCallBoardDAODeleteMethodWithTheRightParameter() throws Exception{
        boardService.deleteBoard("BoardId");
        verify(boardDAO).deleteBoard("BoardId");
    }

}
