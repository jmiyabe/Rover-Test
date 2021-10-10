import com.miyabe.app.core.RoverServiceImpl;
import com.miyabe.app.exceptions.CardinalException;
import com.miyabe.app.exceptions.CommandException;
import com.miyabe.app.ports.RoverService;
import org.junit.Assert;
import org.junit.Test;


public class RoverServiceTest {

    @Test
    public void whenRoverLanded() throws CardinalException, CommandException {
        RoverService roverService = new RoverServiceImpl(5,5,1,2,"N");
        roverService.commandRover("LMLMLMLMM");
        Assert.assertEquals("1 3 N", roverService.getPosition());
    }

    @Test
    public void whenRoverNotLanded() throws CardinalException, CommandException {
        RoverService roverService = new RoverServiceImpl(5,5,6,2,"N");
        roverService.commandRover("LMLMLMLMM");
        Assert.assertEquals("Rover not landed", roverService.getPosition());
    }

    @Test(expected = CommandException.class)
    public void whenInvalidCommand() throws CardinalException, CommandException {
        RoverService roverService = new RoverServiceImpl(5,5,6,2,"N");
        roverService.commandRover("DMLMLMLMM");
    }

    @Test(expected = CardinalException.class)
    public void whenInvalidCardinal() throws CardinalException, CommandException {
        RoverService roverService = new RoverServiceImpl(5,5,6,2,"X");
        roverService.commandRover("LMLMLMLMM");
    }


}
