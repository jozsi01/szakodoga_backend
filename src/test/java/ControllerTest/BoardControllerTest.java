package ControllerTest;

import Controller.Controller;
import Model.CreateBoardDTO;
import BLL.BoardService;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;


import static org.mockito.Mockito.verify;


@SpringBootTest(classes = {Application.Application.class})
public class BoardControllerTest {

	@Mock
	BoardService boardService;

	@InjectMocks
	Controller controller;
	@Test
	void getBoards_shouldCallTheServiceGetBoardsMethod() throws Exception {
		controller.getBoards();
		verify(boardService).getBoards();
	}

	@Test
	void getBoard_shouldCallTheServiceGetBoardMethodWithTheRightParameter() throws Exception {
		controller.getBoard("BoardId");
		verify(boardService).getBoard("BoardId");
	}

	@Test
	void valami(){

	}
	@Test
	void createBoard_shouldCallTheServiceCreateBoardMethodWithTheRightParameter() throws Exception {
		CreateBoardDTO createBoardDTO = CreateBoardDTO.builder()
						.boardName("Test")
						.distanceBetweenPlayers(2)
						.numberOfPieces(2)
						.numberOfPlayer(2)
						.build();
		controller.createBoard(createBoardDTO);
		verify(boardService).createBoard(createBoardDTO);
	}

	@Test
	void deleteBoard_shouldCallTheServiceDeleteMethodWithTheRightParameter() throws Exception{
		controller.deleteBoard("BoardId");
		verify(boardService).deleteBoard("BoardId");
	}

}
