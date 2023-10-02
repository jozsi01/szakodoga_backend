package Model;

import lombok.Data;

@Data
public class PiecePositionDTO {
    private boolean finsihed;
    private String pieceId;
    private int positionOnBoard;
}
