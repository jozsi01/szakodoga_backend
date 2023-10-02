package BLL;

import DAL.GamePlayDAO;
import Model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GamePlayService {
    private final GamePlayDAO gamePlayDAO;

    public PlayerDTO joinPlayer(String id, PlayerJoinRequestDTO playerJoinRequestDTO) throws Exception {
        return gamePlayDAO.joinPlayer(id,playerJoinRequestDTO);
    }

    public BoardDTO leavePlayer(String id, RemoveFromBoardRequestDTO removeFromBoardRequestDTO) throws Exception {
        return gamePlayDAO.leavePlayer(id,removeFromBoardRequestDTO);
    }

    public BoardDTO movePlayer(String id, MoveRequestDTO moveRequestDTO) throws Exception {
        return gamePlayDAO.movePlayer(id,moveRequestDTO);
    }

    public RollResponseDTO rollADice(String id, RollingRequestDTO rollingRequestDTO) throws Exception {
        return gamePlayDAO.rollADice(id,rollingRequestDTO);
    }




}
