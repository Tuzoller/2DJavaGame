package Components;

import Core.GamePanel;
import Entity.EntityManager;

public class Collision {

    GamePanel gp;

    public Collision(GamePanel gp){
        this.gp = gp;
    }

    public void checkTile(EntityManager EntityManager){
        int entityLeft = EntityManager.worldX + EntityManager.solidArea.x;
        int entityRight = EntityManager.worldX + EntityManager.solidArea.x + EntityManager.solidArea.width;
        int entityTop = EntityManager.worldY + EntityManager.solidArea.y;
        int entityBottom = EntityManager.worldY + EntityManager.solidArea.y + EntityManager.solidArea.height;

        int entityLeftCol = entityLeft/gp.tileSize;
        int entityRightCol = entityRight/gp.tileSize;
        int entityTopRow = entityTop/gp.tileSize;
        int entityBottomRow = entityBottom/gp.tileSize;

        int tileNum1, tileNum2;

        switch (EntityManager.direction){
            case "up":
                entityTopRow = (entityTop - EntityManager.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if(gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision){
                    EntityManager.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottom + EntityManager.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision){
                    EntityManager.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeft - EntityManager.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if(gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision){
                    EntityManager.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRight + EntityManager.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision){
                    EntityManager.collisionOn = true;
                }
                break;
        }
    }
}
