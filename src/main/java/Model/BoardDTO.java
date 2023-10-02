package Model;

import lombok.*;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

    private String boardName;
    private int distanceBetweenPlayers;
    private String id;
    private String nextPlayerId;
    private int numberOfPieces;
    private int numberOfPlayer;
    private List<PlayerDTO> players;
    private String status;

}


