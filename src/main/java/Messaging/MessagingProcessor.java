package Messaging;
import Controller.EventSender;
import com.topdesk.hu.intern.wholaughlast.messaging.api.WhoLaughLastMessagingProcessor;
import com.topdesk.hu.intern.wholaughlast.messaging.api.dto.BoardStatusChangeMessage;
import com.topdesk.hu.intern.wholaughlast.messaging.api.dto.PlayerUpdateMessage;
import com.topdesk.hu.intern.wholaughlast.messaging.api.dto.PositionsUpdateMessage;
import com.topdesk.hu.intern.wholaughlast.messaging.api.dto.RolledDiceMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessagingProcessor implements WhoLaughLastMessagingProcessor{
    private final EventSender sender;
    @Override
    public void playerUpdated(PlayerUpdateMessage playerUpdateMessage) {
        System.out.println("PlayerChange"+playerUpdateMessage.getBoardId());
        sender.sendEvents("PlayerChange"+playerUpdateMessage.getBoardId(),playerUpdateMessage);
    }

    @Override
    public void rolledDice(RolledDiceMessage rolledDiceMessage) {
        sender.sendEvents("RollChange"+rolledDiceMessage.getBoardId(),rolledDiceMessage);
    }

    @Override
    public void move(PositionsUpdateMessage positionsUpdateMessage) {

        sender.sendEvents("PositionChange"+positionsUpdateMessage.getBoardId(),positionsUpdateMessage);
    }

    @Override
    public void gameStatusChange(BoardStatusChangeMessage boardStatusChangeMessage) {
        sender.sendEvents("StatusChange"+boardStatusChangeMessage.getBoardId(),boardStatusChangeMessage);
    }
}
