package ch.verdsmanFramework.verdsmanStimulator;

import ch.verdsmanFramework.verdsmanMessagingClient.VMCFactory;
import ch.verdsmanFramework.verdsmanMessagingClient.VerdsmanMessagingClient;

public class VerdsmanStimulator {
	protected VerdsmanMessagingClient messagingClient = null;
	protected VMCFactory vmcFactory;
	
	public VerdsmanStimulator(VMCFactory vmcFactory) {
		this.vmcFactory = vmcFactory;

	}
	
	public void setVerdsmanMessagingClient(VerdsmanMessagingClient messagingClient) {
		this.messagingClient = messagingClient;
	}
	
	public void listenForTopic(String topic) {
		if(messagingClient != null) {
			this.messagingClient.registerTopic(topic);
		} else {
			System.err.println("VerdsmanMessagingClient not set! @VMCExampleUserBot::listenForTopic()");
		}
	}
	
}
