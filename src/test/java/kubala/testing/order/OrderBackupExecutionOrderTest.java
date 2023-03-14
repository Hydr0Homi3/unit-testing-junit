package kubala.testing.order;

import com.thoughtworks.qdox.model.expression.Or;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderBackupExecutionOrderTest {

    @Test
    public void callingBackupWithoutCreatingTheFileFirstShouldThrowException() throws IOException {

        //given
        OrderBackup orderBackup = new OrderBackup();

        //then
        assertThrows(IOException.class, () ->orderBackup.backupOrder(new Order()));
    }
}
