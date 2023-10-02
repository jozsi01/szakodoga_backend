package Controller;

import Model.*;

import ErrorHandling.ErrorCodeException;
import BLL.BoardService;
import BLL.GameManagerService;
import BLL.GamePlayService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Controller {
    private final BoardService boardService;
    private final GameManagerService gameManagerService;
    private final GamePlayService gamePlayService;

    @GetMapping("/board")
    public ResponseEntity<?> getBoards() {
        System.out.println("Controlllerben bent van");
        BoardListDTO boardListDTO = null;
        try{
             boardListDTO = boardService.getBoards();
        }catch (ErrorCodeException e) {
            System.out.println(e);
            return new ResponseEntity<>(e.getErrorCode().getErrorCode(),HttpStatusCode.valueOf(400));
        }
        catch (Exception e){
            System.out.println(e);

        }HttpHeaders headers = new HttpHeaders();
        System.out.println(boardListDTO.toString());
        headers.add("Content-Security-Policy", "default-src 'self' http://localhost:8080");
        return new ResponseEntity<>(boardListDTO,headers, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/board/{id}")
    public ResponseEntity<?> getBoard(@PathVariable String id) {
        BoardDTO boardDTO = null;
        try{
            boardDTO = boardService.getBoard(id);
        }catch (ErrorCodeException e) {
            return new ResponseEntity<>(e.getErrorCode().getErrorCode(),HttpStatusCode.valueOf(400));
        }
        catch (Exception e){
            return new ResponseEntity<>("Board not found",HttpStatusCode.valueOf(404));
        }
        return new ResponseEntity<>(boardDTO,HttpStatusCode.valueOf(200));
    }

    @PostMapping("/board")
        public ResponseEntity<?> createBoard(@RequestBody CreateBoardDTO board) {
        BoardDTO createdBoard = null;
        try {
            createdBoard= boardService.createBoard(board);

        }catch (ErrorCodeException e) {
            return new ResponseEntity<>(e.getErrorCode().getErrorCode(),HttpStatusCode.valueOf(400));
        }
        catch(Exception e){
            System.out.println(e);
        }
        return new ResponseEntity<>(createdBoard,HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/board/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable String id) {
        int status = 0;
        try{
            status = boardService.deleteBoard(id);
        }catch (ErrorCodeException e) {
            return new ResponseEntity<>(e.getErrorCode().getErrorCode(),HttpStatusCode.valueOf(400));
        }
        catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatusCode.valueOf(status)).body("Board not found");
        }
        return ResponseEntity.status(HttpStatusCode.valueOf(status)).body("Board deleted");
    }

    @PutMapping("/board/manage/{id}/reset")
    public ResponseEntity<?> resetBoard(@PathVariable String id) {

        BoardDTO response = null;
        try {
            response = gameManagerService.resetBoard(id);

            return new ResponseEntity<>(response,HttpStatusCode.valueOf(200));
        }catch (ErrorCodeException e) {
            return new ResponseEntity<>(e.getErrorCode().getErrorCode(),HttpStatusCode.valueOf(400));
        }
        catch (Exception e){
            return new ResponseEntity<>("Board not found",HttpStatusCode.valueOf(404));
        }
    }
    @PutMapping("/board/manage/{id}/start")
    public ResponseEntity<?> startBoard(@PathVariable String id) {
        BoardDTO response = null;
        try{
            response = gameManagerService.startBoard(id);
            return new ResponseEntity<>(response,HttpStatusCode.valueOf(200));
        }catch(ErrorCodeException e) {
            return new ResponseEntity<>(e.getErrorCode().getErrorCode(),HttpStatusCode.valueOf(400));
        }
        catch (Exception e){
            return new ResponseEntity<>("Board not found",HttpStatusCode.valueOf(404));
        }
    }

    @PutMapping("/board/manage/{id}/stop")
    public ResponseEntity<?> stopBoard(@PathVariable String id) {
        BoardDTO response = null;
        try{
            response = gameManagerService.stopBoard(id);
            return new ResponseEntity<>(response,HttpStatusCode.valueOf(200));
        }catch (ErrorCodeException e) {
            return new ResponseEntity<>(e.getErrorCode().getErrorCode(),HttpStatusCode.valueOf(400));
        }
        catch (Exception e){
            return new ResponseEntity<>("Board not found",HttpStatusCode.valueOf(404));
        }
    }
    @GetMapping("/board/manage/supported-colors")
    public ResponseEntity<?> getColors() {
        ColorListDTO response = null;
        try{
            response = gameManagerService.getSupportedColors();
        }
        catch (ErrorCodeException e) {
            return new ResponseEntity<>(e.getErrorCode().getErrorCode(),HttpStatusCode.valueOf(400));
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return new ResponseEntity<>(response,HttpStatusCode.valueOf(200));
    }
    @PostMapping("/board/{boardId}/join")
    public ResponseEntity<?> joinPlayer(@RequestBody PlayerJoinRequestDTO playerJoinRequestDTO,@PathVariable String boardId) {
        PlayerDTO joinedPlayer = null;
        try {
            joinedPlayer = gamePlayService.joinPlayer(boardId,playerJoinRequestDTO);
            return new ResponseEntity<>(joinedPlayer,HttpStatusCode.valueOf(200));
        }catch (ErrorCodeException e) {
            return new ResponseEntity<>(e.getErrorCode().getErrorCode(),HttpStatusCode.valueOf(400));
        }
        catch(Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Board not found", HttpStatusCode.valueOf(404));
        }
    }

    @PostMapping("/board/{boardId}/leave")
    public ResponseEntity<?> leavePlayer(@RequestBody RemoveFromBoardRequestDTO removeFromBoardRequestDTO,@PathVariable String boardId) {
        BoardDTO leftPlayer = null;
        try {
            leftPlayer = gamePlayService.leavePlayer(boardId,removeFromBoardRequestDTO);
            return new ResponseEntity<>(leftPlayer,HttpStatusCode.valueOf(200));
        }catch (ErrorCodeException e) {
            return new ResponseEntity<>(e.getErrorCode().getErrorCode(),HttpStatusCode.valueOf(400));
        }
        catch(Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Board not found", HttpStatusCode.valueOf(404));
        }
    }

    @PostMapping("/board/{boardId}/move")
    public ResponseEntity<?> movePlayer(@RequestBody MoveRequestDTO moveRequestDTO,@PathVariable String boardId) {
        BoardDTO movedPlayer = null;
        try {
            movedPlayer = gamePlayService.movePlayer(boardId,moveRequestDTO);
            return new ResponseEntity<>(movedPlayer,HttpStatusCode.valueOf(200));
        }catch (ErrorCodeException e) {
            return new ResponseEntity<>(e.getErrorCode().getErrorCode(),HttpStatusCode.valueOf(400));
        }
        catch(Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Board not found", HttpStatusCode.valueOf(404));
        }
    }

    @PostMapping("/board/{boardId}/roll-a-dice")
    public ResponseEntity<?> rollADice(@RequestBody RollingRequestDTO rollingRequestDTO,@PathVariable String boardId) {
        RollResponseDTO rolledDice = null;
        System.out.println(rollingRequestDTO);
        try {

            rolledDice = gamePlayService.rollADice(boardId,rollingRequestDTO);
            System.out.println("Controllerbol "+ rolledDice);
            return new ResponseEntity<>(rolledDice,HttpStatusCode.valueOf(200));
        }catch (ErrorCodeException e) {
            return new ResponseEntity<>(e.getErrorCode().getErrorCode(),HttpStatusCode.valueOf(400));
        }
        catch(Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Board not found", HttpStatusCode.valueOf(404));
        }
    }



}
