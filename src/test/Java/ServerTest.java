
import client.ChatClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import server.Server;


import java.io.*;

public class ServerTest implements SysoutCaptureAndAssertionAbility {
	//region given
	@Before
	public void setUpSystemOut() throws IOException {
		resetOut();
		captureSysout();
	}
	
	@After
	public void tearDown() {
		resetOut();
	}
	//endregion
	
	@Test
	public void shouldLoadThousandChatUser() throws IOException {
		//region when
		new Thread(() -> Server.main(new String[]{})).start();
		
		
		for (int i = 0; i < 10000; i++) {
			int j = i;
			new Thread(() -> {
				
				new ChatClient("localhost", 9999).start();
			});
			
		}
		//endregion
	}
}
