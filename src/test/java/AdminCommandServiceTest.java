import com.alphadev.commands.services.AdminCommandService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AdminCommandServiceTest {
    @Test
    void verificaSeNomeDaCasaExisteNaListaDeCasas(){
        assertTrue(AdminCommandService.checkIfHouseNameExists("zeronia"));
        assertTrue(AdminCommandService.checkIfHouseNameExists("zeRonia"));
        assertFalse(AdminCommandService.checkIfHouseNameExists("zeRonia1"));
    }
}
