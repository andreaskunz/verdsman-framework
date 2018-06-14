package ch.verdsmanFramework.applications;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import ch.verdsmanFramework.verdsmanMessagingClient.VMCFactory;
import ch.verdsmanFramework.verdsmanMessagingClient.VMCMqttFactory;
import ch.verdsmanFramework.verdsmanMessagingClient.VerdsmanMQTTMessagingClient;
import ch.verdsmanFramework.verdsmanMessagingClient.VerdsmanMessagingClient;
import ch.verdsmanFramework.verdsmanMessagingClient.messageObjectParsers.UMCMessageParserFactory;
import ch.verdsmanFramework.verdsmanMessagingClient.messageObjectParsers.UMCMessageParserPool;
import ch.verdsmanFramework.verdsmanMessagingClient.messageObjects.UMCMessageFactory;
import ch.verdsmanFramework.verdsmanStimulator.VSCollarLight;
import ch.verdsmanFramework.verdsmanStimulator.VSProjector;


public class Main {
	
	//settings
	//private final static String brokerURI = "tcp://broker.hivemq.com:1883";
	private final static String brokerURI = "tcp://192.168.5.4:1883";
	private final static String clientID = "S.A.I.N.T._No5_aka_VMC_bot";
	
	public void run() throws MqttException {
		VMCMqttFactory mqttFactory = new VMCMqttFactory();
		VMCFactory vmcFactory = new VMCFactory();
		UMCMessageFactory messageFactory = new UMCMessageFactory();
		MqttClient mqttclient = mqttFactory.createMqttClient(brokerURI, clientID, mqttFactory.createMemoryPersistence());
		//VSProjector vsprojector = new VSProjector(parser, vmcFactory);
		VSCollarLight collarLight = new VSCollarLight(vmcFactory);
		UMCMessageParserPool parserPool = new UMCMessageParserPool(new UMCMessageParserFactory(messageFactory));
		VerdsmanMessagingClient messagingClient = new VerdsmanMQTTMessagingClient(collarLight, mqttclient, mqttFactory, vmcFactory, parserPool);
		
		collarLight.setVerdsmanMessagingClient(messagingClient);
		collarLight.listenForTopic("collar");
		//vsprojector.startTest();
	}
	
	public static void main (String[] arguments) {
		Main app = new Main();
		try {
			app.run();
		} catch (MqttException e) {
			System.err.println("something really baaad happened but the global Exception catcher got it for you ;-)");
			e.printStackTrace();
		}
	}
}
