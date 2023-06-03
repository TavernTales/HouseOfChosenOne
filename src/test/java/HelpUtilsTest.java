
import com.alphadev.enums.HouseEnum;
import com.alphadev.utils.HelpUtils;
import org.bukkit.entity.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;


class HelpUtilsTest {

    @Test
    void testIfNotNull() {
        assertTrue(HelpUtils.sortPercent() > 0 && HelpUtils.sortPercent() < 101);
    }

    @Test
    void testisNullOrEmpty() {
        assertTrue(HelpUtils.isNullOrEmpty((Collection<?>) null));
        assertTrue(HelpUtils.isNullOrEmpty(new ArrayList()));
        assertFalse(HelpUtils.isNullOrEmpty(HelpUtils.HOUSES));
        assertFalse(HelpUtils.isNullOrEmpty(new Object()));

    }

//    @Test
//    void playerNaoTemPermissao() {
//        Player player = PowerMockito.mock(Player.class);
//        assertFalse(AdminCommandService.playerHasAdminPermission(player));
//    }

    @Test
    void HouseEnumFindFromName() {
        assertEquals(1, HouseEnum.fromName("zeronia").getId());
        assertEquals(1, HouseEnum.fromName("dasdas").getId());
        assertEquals(2, HouseEnum.fromName("vlarola").getId());
        assertNotEquals(1, HouseEnum.fromName("vlarola").getId());

    }
}
