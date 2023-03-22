import com.alphadev.utils.HelpUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelpUtilsTest {


    @Test
    public void testIfNotNull(){
        Assertions.assertTrue(HelpUtils.sortPercent() > 0 && HelpUtils.sortPercent() < 101 );
    }
}
