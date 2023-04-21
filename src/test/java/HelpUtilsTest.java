import com.alphadev.commands.services.AdminCommandService;
import com.alphadev.utils.HelpUtils;
import org.bukkit.entity.Player;
import org.junit.jupiter.api.Test;
import org.powermock.api.mockito.PowerMockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class HelpUtilsTest  {

    @Test
    void testIfNotNull(){
      assertTrue(HelpUtils.sortPercent() > 0 && HelpUtils.sortPercent() < 101 );
    }

    @Test
    void testisNullOrEmpty(){
        assertTrue(HelpUtils.isNullOrEmpty(null));
        assertTrue(HelpUtils.isNullOrEmpty(new ArrayList()));
        assertFalse(HelpUtils.isNullOrEmpty(HelpUtils.HOUSES));
        assertFalse(HelpUtils.isNullOrEmpty(new Object()));

    }
    @Test
    void playerNaoTemPermissao(){
        Player player = PowerMockito.mock(Player.class);
        assertFalse(AdminCommandService.playerHasAdminPermission(player));
    }

}
