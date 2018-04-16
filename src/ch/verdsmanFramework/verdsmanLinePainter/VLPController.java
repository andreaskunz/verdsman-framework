package ch.verdsmanFramework.verdsmanLinePainter;

import java.awt.Color;
import java.awt.Dimension;

public class VLPController {
	public static void main(String[] args) {
		VLPGraphicController graphicController = new VLPGraphicController(new Dimension(800, 600));
		graphicController.drawCenterLine(20, Color.WHITE);
		graphicController.drawLine(20, 20, Color.RED);
		graphicController.drawLine(20, 150, Color.BLUE);
		graphicController.drawFlashingLine();
//		for(int i=0;i<100;i++) {
//			try {
//				Thread.sleep(500);
//				graphicController.deleteAllLines();
//				Thread.sleep(500);
//				graphicController.drawLine(20, 150, Color.BLUE);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}	
//		}
	}
}
