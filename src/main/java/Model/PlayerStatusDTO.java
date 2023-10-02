package Model;

import lombok.Data;

@Data
public class PlayerStatusDTO {
    private int finsihedIn;
    private PiecePositionDTO piecePosition;
    private String playerId;
}
