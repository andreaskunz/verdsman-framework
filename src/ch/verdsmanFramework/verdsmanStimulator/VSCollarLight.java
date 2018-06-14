package ch.verdsmanFramework.verdsmanStimulator;


import ch.verdsmanFramework.verdsmanLightController.VerdsmanLight;
import ch.verdsmanFramework.verdsmanMessagingClient.IVMCMessageReceiver;
import ch.verdsmanFramework.verdsmanMessagingClient.VMCFactory;
import ch.verdsmanFramework.verdsmanMessagingClient.messageObjects.UMCCommandMessage;
import ch.verdsmanFramework.verdsmanMessagingClient.messageObjects.UMCMessage;

public class VSCollarLight extends VerdsmanStimulator implements IVMCMessageReceiver {

	VerdsmanLight vmLight = null;
	
	public VSCollarLight(VMCFactory vmcFactory) {
		super(vmcFactory);
	}

	@Override
	public void messageArrived(UMCMessage message) {
		if(message instanceof UMCCommandMessage) {
			UMCCommandMessage commandMessage = (UMCCommandMessage) message;
			
			String command = commandMessage.command;
			
			switch(command) {
			case "collarLight_init":
				if(this.vmLight == null) {
					this.vmLight = new VerdsmanLight();
				}
				break;
			case "collarLight_on":
				if(this.vmLight != null) {
					this.vmLight.switchOn();
				}
				break;
			case "collarLight_off":
				if(this.vmLight != null) {
					this.vmLight.switchOff();
				}
				break;
			case "collarLight_shutdown":
				if(this.vmLight != null) {
					this.vmLight.shutdownLight();
					try {
						Thread.sleep(100);
						this.vmLight = null;
					}catch (Exception e) {
						e.printStackTrace();
					}	
				}
				break;
			
			default:
				System.err.println("Message droped since not interpretable. @ VSCollarLight::messageArrived()");
			}
			
		} else { // print notification and drop message. //TODO send error message back
			System.err.println("Message droped since not interpretable. @ VSCollarLight::messageArrived()");
		}
	}

}
