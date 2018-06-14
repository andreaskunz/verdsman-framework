package ch.verdsmanFramework.verdsmanStimulator;

import java.awt.Color;
import java.awt.Dimension;

import ch.verdsmanFramework.verdsmanLinePainter.VLPGraphicController;
import ch.verdsmanFramework.verdsmanMessagingClient.IVMCMessageReceiver;
import ch.verdsmanFramework.verdsmanMessagingClient.VMCFactory;
import ch.verdsmanFramework.verdsmanMessagingClient.messageObjects.UMCMessage;
import ch.verdsmanFramework.verdsmanMessagingClient.messageObjects.UMCStringMessage;

public class VSProjector extends VerdsmanStimulator implements IVMCMessageReceiver {
	public static final Dimension DISPLAY_RESOLUTION = new Dimension(1024, 768); //TODO read from config.

	public VLPGraphicController graphicController = null;
	
	public VSProjector(VMCFactory vmcFactory) {
		super(vmcFactory);
	}

	@Override
	public void messageArrived(UMCMessage message) {
		if(message instanceof UMCStringMessage) {
			String content = (String) ((UMCStringMessage) message).value;
			
			switch(content) {
			case "init":
				graphicController = VLPGraphicController.createVLPGraphicController(DISPLAY_RESOLUTION);
				break;
			case "lineon":
				if(graphicController !=null) {
					graphicController.drawCenterLine(20, Color.WHITE);
				}
				break;
			case "lineoff":
				if(graphicController !=null) {
					graphicController.deleteAllLines();
				}
				break;
			
			default:
				System.err.println("Message droped since not interpretable. @ VSProjector::messageArrived()");
			}
			
		} else { // print notification and drop message. //TODO send error message back
			System.err.println("Message droped since not interpretable. @ VSProjector::messageArrived()");
		}
	}
	
	public void startTest() {
		Runnable botTask = () -> {
			UMCStringMessage message1 = this.vmcFactory.createUMCStringMessage();
			message1.value = "init";
			message1.from = "tester";
			message1.topic = "vmcFunTopic";
			message1.to = "all interested parties";
			this.messagingClient.postMessage(message1);
			System.out.println("tester: message posted");
			
			while(true) {
				try {
					Thread.sleep(2000);
					
					UMCStringMessage message2 = this.vmcFactory.createUMCStringMessage();
					message2.value = "lineon";
					message2.from = "tester";
					message2.topic = "vmcFunTopic";
					message2.to = "all interested parties";
					this.messagingClient.postMessage(message2);
					System.out.println("tester: message posted");
					Thread.sleep(2000);
					
					UMCStringMessage message3 = this.vmcFactory.createUMCStringMessage();
					message3.value = "lineoff";
					message3.from = "tester";
					message3.topic = "vmcFunTopic";
					message3.to = "all interested parties";
					this.messagingClient.postMessage(message3);
					System.out.println("tester: message posted");	
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		};
			new Thread(botTask).start();
	}
	
	

}
