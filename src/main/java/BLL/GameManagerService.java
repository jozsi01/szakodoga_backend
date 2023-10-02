package BLL;

import DAL.GameManagerDAO;
import Model.BoardDTO;
import Model.ColorListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GameManagerService {

    private final GameManagerDAO gameManagerDAO;

    public BoardDTO startBoard(String id) throws Exception {
       return gameManagerDAO.manageBoard(id,ManageBoardAction.START);
    }

    public BoardDTO stopBoard(String id) throws Exception {
        return gameManagerDAO.manageBoard(id,ManageBoardAction.STOP);
    }
    public BoardDTO resetBoard(String id) throws Exception {
        return gameManagerDAO.manageBoard(id,ManageBoardAction.RESET);
    }

    public ColorListDTO getSupportedColors() throws Exception {

        return gameManagerDAO.getSupportedColors();
    }


}
