import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

public class Block implements Constants{
	
	public Image blockImage;

	public Block(int identity){
		
		if(identity == DIRT){
			blockImage = loadImage("Graphics/Blocks/dirtBlock.png");
		}else if(identity == GRASS){
			blockImage = loadImage("Graphics/Blocks/grassBlock.png");
		}else if(identity == STONE){
			blockImage = loadImage("Graphics/Blocks/stoneBlock.png");
		}else if(identity == DARKSTONE){
			blockImage = loadImage("Graphics/Blocks/darkstoneBlock.png");
		}else if(identity == SAND){
			blockImage = loadImage("Graphics/Blocks/sandBlock.png");
		}else if(identity == AQUATIC){
			blockImage = loadImage("Graphics/Blocks/aquaticBlock.png");
		}else if(identity == FIRE){
			blockImage = loadImage("Graphics/Blocks/fireBlock.png");
		}else if(identity == EXIT){
			blockImage = loadImage("Graphics/Blocks/exit.png");
		}else if(identity == CONCRETE){
			blockImage = loadImage("Graphics/Blocks/concrete.png");
		}else if(identity == LAVA){
			blockImage = loadImage("Graphics/Blocks/lava.png");
		}
		
	}
	
	public Image loadImage(String fileName){
		ImageIcon temp = new ImageIcon(fileName);
		return temp.getImage();
	}
	
	
	public Image getImage(){
		return blockImage;
	}


	
	
}
