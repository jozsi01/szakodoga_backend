package Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDTO {

    private String color;
    private int finishedRound;
    private String id;
    private String name;
    private List<PieceDTO> pieces;
    private int startingIndexOnBoard;
}
