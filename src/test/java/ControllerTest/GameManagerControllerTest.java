package ControllerTest;

import Controller.Controller;
import Model.ColorListDTO;
import BLL.GameManagerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {Application.Application.class})
public class GameManagerControllerTest {

    @Mock
    GameManagerService gameManagerService ;

    @InjectMocks
    Controller controller;

    @Test
    void resetBoard_shouldCallServiceResetBoardWithTheProperParameters() throws Exception{
        controller.resetBoard("boardId");
        verify(gameManagerService).resetBoard("boardId");
    }

    @Test
    void startBoard_shouldCallServiceStartBoardWithTheProperParameters() throws Exception{
        controller.startBoard("boardId");
        verify(gameManagerService).startBoard("boardId");
    }

    @Test
    void stopBoard_shouldCallServiceStopBoardWithTheProperParameters() throws Exception{
        controller.stopBoard("boardId");
        verify(gameManagerService).stopBoard("boardId");
    }

    @Test
    void getSupportedColors_shouldReturnWithTheGivenParameters() throws Exception {
        ColorListDTO colorListDTO = new ColorListDTO(new ArrayList<>(Arrays.asList("Blue","Green")));
        when(gameManagerService.getSupportedColors()).thenReturn(colorListDTO);
        assertThat(controller.getColors()).isEqualTo(new ResponseEntity<>(colorListDTO, HttpStatusCode.valueOf(200)));
    }


}
