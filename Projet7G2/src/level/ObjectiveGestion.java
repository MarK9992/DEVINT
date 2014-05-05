package level;

import util.Preferences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by tom on 09/04/14.
 */
public class ObjectiveGestion {
    private List<Direction> objectiveWay;
    private List<Direction> wrongWay;
    private int currentPosition;
    private boolean way;

    public ObjectiveGestion(List<Direction> objectiveWay){
        this.objectiveWay=objectiveWay;
        this.wrongWay=new ArrayList<Direction>();
        this.currentPosition=0;
        this.way = false;
    }

    public ObjectiveGestion(){
        this(new ArrayList<Direction>());
    }

    public void moveForward(Direction direction){
       if(wrongWay.isEmpty()){

           if (objectiveWay.get(currentPosition).equals(direction))
               currentPosition++;
           else wrongWay.add(direction);

       }
       else {

           if(wrongWay.get(wrongWay.size()-1).getOppositeDirection().equals(direction))
               wrongWay.remove(wrongWay.size()-1);
           else wrongWay.add(direction);
       }

    }


    public String getNextInstruction(Direction direction) {
        switch (isFinish(direction)){
            case -1:
                this.moveForward(direction);
                return this.getNextStep().getAction();

            case 0:
                Preferences.makeSivoxSay("Bravo, vous avez gagné.");
                Preferences.game.win();
                return "";

            case 1:
                return this.getNextStep().getAction();

            default:
                return "";


        }




        /*
        if (!this.isFinish()){
            this.moveForward(direction);
            return this.getNextStep().getAction();
        }
        else return "You Win !";*/
    }

    private int isFinish(Direction dir) {
        if (!way){

            if (this.currentPosition==this.objectiveWay.size()-1 && dir.equals(this.objectiveWay.get(currentPosition))&&wrongWay.isEmpty()){
                this.way=true;
                Preferences.retour=true;
                Collections.reverse(objectiveWay);
                System.out.println("-----------------------");
                for (int i=0;i<objectiveWay.size();i++){
                    objectiveWay.add(i,objectiveWay.remove(i).getOppositeDirection());
                    System.out.println(i+" : "+objectiveWay.get(i).getAction());
                }

                System.out.println("-----------------------");
                this.currentPosition=0;
                Preferences.makeSivoxSay("Retournez au point de départ.");
                return 1;
            }
            else return -1;
        }
        else return (this.currentPosition==this.objectiveWay.size()-1 && dir.equals(this.objectiveWay.get(currentPosition))&&wrongWay.isEmpty())?0:-1;
    }

    private Direction getNextStep() {
        if(wrongWay.isEmpty()){
            return objectiveWay.get(currentPosition);
        }
        return wrongWay.get(wrongWay.size()-1).getOppositeDirection();
    }

    public String getFirstInstruction() {
        return objectiveWay.get(0).getAction();
    }

    public String getCurrentInstruction(){
        return wrongWay.isEmpty()?objectiveWay.get(currentPosition).getAction():wrongWay.get(wrongWay.size()-1).getOppositeDirection().getAction();
    }
}
