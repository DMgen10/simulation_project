package org.dm;

import org.dm.entities.Entity;
import org.dm.entities.creatures.Herbivore;
import org.dm.entities.creatures.Predator;
import org.dm.entities.static_object.Grass;
import org.dm.entities.static_object.Rock;
import org.dm.entities.static_object.Tree;

import java.util.HashMap;
import java.util.Map;

public class SpriteView {

    private final Map<Class<? extends Entity>, String> sprites;

    public SpriteView() {
        sprites = new HashMap<>();
        mapping();
    }

    private void mapping(){
        sprites.put(Herbivore.class, SpriteDictionary.SPRITE_HERBIVORE);
        sprites.put(Predator.class, SpriteDictionary.SPRITE_PREDATOR);
        sprites.put(Grass.class, SpriteDictionary.SPRITE_GRASS);
        sprites.put(Rock.class, SpriteDictionary.SPRITE_ROCK);
        sprites.put(Tree.class, SpriteDictionary.SPRITE_TREE);
    }

    public String getView(Entity entity){
        if (entity == null){
            return SpriteDictionary.SPRITE_EMPTY;
        }
        return sprites.get(entity.getClass());
    }

}
