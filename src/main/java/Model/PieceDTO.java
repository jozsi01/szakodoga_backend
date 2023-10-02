package Model;

import lombok.Data;

@Data
public class PieceDTO {
    private boolean gotAround;
    private String id;
    private int positionOnTheBoard;
}
