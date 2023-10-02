package BLL;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
@Getter
@RequiredArgsConstructor
public enum ManageBoardAction {
        START("start"),
        RESET("reset"),
        STOP("stop");
        private final String action;

}


