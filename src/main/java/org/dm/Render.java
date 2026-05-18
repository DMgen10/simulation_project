package org.dm;

public class Render {

    private final MapSimulation map;
    private final SpriteView sprites = new SpriteView();

    public Render(MapSimulation simulation) {
        this.map = simulation;
    }

    public void rendering(){

        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Position position = new Position(x,y);
                System.out.print(sprites.getView(map.getEntity(position)));
            }
            System.out.println();
        }
    }
}
