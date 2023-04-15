import com.alphadev.utils.HelpUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HelpUtilsTest {

    @Test
    void testIfNotNull(){
      assertTrue(HelpUtils.sortPercent() > 0 && HelpUtils.sortPercent() < 101 );
    }

    @Test
    void testisNullOrEmpty(){
        assertTrue(HelpUtils.isNullOrEmpty(null));
        assertTrue(HelpUtils.isNullOrEmpty(new ArrayList()));
        assertFalse(HelpUtils.isNullOrEmpty(HelpUtils.HOUSES));
    }

}
