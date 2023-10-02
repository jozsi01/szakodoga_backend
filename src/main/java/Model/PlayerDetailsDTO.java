package Model;

import lombok.Data;

import java.util.List;

@Data
public class PlayerDetailsDTO {
    private String color;
    private String id;
    private String name;
    private List<PieceInformationDTO> pieces;
    private int startingIndexOnBoard;
}
