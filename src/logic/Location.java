package logic;

import java.util.ArrayList;

public class Location {
	int width;
	int height;
	Block[][] blocks;
	ArrayList<Monster> monsters;
	public Location(int width, int height, int[][] blocks) {
		this.width = width;
		this.height = height;
		this.blocks = new Block[this.width][this.height];
		for(int i = 0; i < this.width; i++) {
			for(int j = 0; j < this.height; j++) {
				if(blocks[i][j] == 0) this.blocks[i][j] = new GrassBlock();
				if(blocks[i][j] == 0) this.blocks[i][j] = new RockBlock();
			}
		}
	}
}
