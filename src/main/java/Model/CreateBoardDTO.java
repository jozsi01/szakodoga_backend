package Model;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class CreateBoardDTO {

    private String boardName;
    private int distanceBetweenPlayers;
    private int numberOfPieces;
    private int numberOfPlayer;
}
