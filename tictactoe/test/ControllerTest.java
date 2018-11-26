import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;


import model.*;
import controller.*;
import view.*;

public class ControllerTest {
    @Test
    public void testGameState() {
		Controller c = new Controller();  
		Model m = new Model();  
		View v = new View();
		m.registerView(v);
		c.setModel(m);
		
		ArrayList<Integer> pos = new ArrayList<Integer>();
		pos.add(1);
		pos.add(1);
		
		// ask the model to update its board depending on the request
		c.setRequest(pos);
		
		// check if the requested operation updated the model successfully
		assertEquals ('X', m.getBoard()[1][1]);
		
    }

}
