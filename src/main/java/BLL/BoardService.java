package BLL;

import DAL.BoardDAO;
import Model.BoardDTO;
import Model.BoardListDTO;
import Model.CreateBoardDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BoardService {


    private final BoardDAO boardDAO;

    public BoardListDTO getBoards() throws Exception{
        return boardDAO.getBoards();
    }
    public BoardDTO getBoard(String id) throws Exception {
        return boardDAO.getBoard(id);
    }
    public BoardDTO createBoard(CreateBoardDTO board) throws Exception {
        return boardDAO.createBoard(board);
    }

    public int deleteBoard(String id) throws Exception {
       return boardDAO.deleteBoard(id);
    }

}
