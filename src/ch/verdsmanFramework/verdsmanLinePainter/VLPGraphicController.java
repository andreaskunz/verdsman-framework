package ch.verdsmanFramework.verdsmanLinePainter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import figurePainter4j.FPCanvas;
import figurePainter4j.FPJFrame;
import figurePainter4j.FPRectangle;

public class VLPGraphicController {

	FPCanvas canvas;
	FPJFrame frame;
	Dimension displaySize;
	
	public static VLPGraphicController createVLPGraphicController(Dimension displaySize) {
		return new VLPGraphicController(displaySize);
	}
	
	
	public VLPGraphicController(Dimension displaySize) {
		canvas = new FPCanvas(displaySize, Color.BLACK);
		frame = new FPJFrame(canvas, displaySize);
		canvas.initialize();
		this.displaySize = displaySize;
	} 
	
	public void deleteAllLines() {
		canvas.delete();
	}
	
	public void drawCenterLine(int thickness, Color color) {
		int lineTop = displaySize.height/2 - thickness/2;
		this.drawLine(thickness, lineTop, color);
	}
	
	public void drawLine(int thickness, int position, Color color) {
		FPRectangle rectangle = new FPRectangle(new Point(0, position), new Dimension((int)displaySize.getWidth(), thickness), color);
		canvas.paintFigure(rectangle);
	}
	
	public void drawFlashingLine() {
		 int yLocation = 5;
		FPRectangle rectangle = new FPRectangle(new Point(0, yLocation), new Dimension((int)displaySize.getWidth(), 20), Color.WHITE);
		canvas.paintFigure(rectangle);
		for(int i=0;i<200;i++) {
			try {
				Thread.sleep(100);
				//rectangle.setLocation(0,yLocation+=1);
				rectangle.setColor(Color.BLACK);
				canvas.paintFigure(rectangle);
				Thread.sleep(100);
				rectangle.setColor(Color.WHITE);
				canvas.paintFigure(rectangle);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		
	}
}
