package org.dm;

import org.dm.entities.Entity;
import org.dm.entities.creatures.Herbivore;
import org.dm.entities.creatures.Predator;
import org.dm.entities.static_object.Grass;
import org.dm.entities.static_object.Rock;
import org.dm.entities.static_object.Tree;

import java.util.HashMap;
import java.util.Map;

public class SpriteRepository {

    private final Map<Class<? extends Entity>, String> sprites;

    public SpriteRepository() {
        sprites = new HashMap<>();
        mapping();
    }

    private void mapping(){
        sprites.put(Herbivore.class, "H");
        sprites.put(Predator.class, "P");
        sprites.put(Grass.class, "G");
        sprites.put(Rock.class, "R");
        sprites.put(Tree.class, "T");
    }

    public String getSprite(Entity entity){
        if (entity == null){
            return " . ";
        }
        return sprites.get(entity);
    }

}
