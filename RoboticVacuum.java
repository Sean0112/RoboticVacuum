import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

public class RoboticVacuum {
    int diameter;
    int x; int y;
    String[] directions = {"SOUTH", "EAST", "WEST", "NORTH"};
    ArrayList<Visited> visits = new ArrayList<>();
    String previousDirection = "NORTH";

    public RoboticVacuum(){
        this.diameter = 30;
        this.x = 35;
        this.y = 435;
        Visited v = new Visited(x,y);
        visits.add(v);
    }

    public void moveRoboticVacuum(ArrayList<Furniture> fs){
        ArrayList<String> prevDirs = new ArrayList<>(); //previously visited "directions" (i.e. the direction leads to a prev visited cell)
        String direction = "NORTH";
        ArrayList<String> goodDirs = new ArrayList<>();

        //fill ArrayList with possible directions
        for(int i = 0; i < 4; i++){
            if(canMove(directions[i],fs)) {
                goodDirs.add(directions[i]);
                direction = directions[i];
            }
        }

        for(String goodDirection : goodDirs){
            for (Visited v : visits) {
                //fill prevDirs
                switch (goodDirection) {
                    case("NORTH"):
                        if(this.y - 100 == v.y && this.x == v.x)
                            prevDirs.add("NORTH");
                        break;
                    case("SOUTH"):
                        if(this.y + 100 == v.y && this.x == v.x)
                            prevDirs.add("SOUTH");
                        break;
                    case("EAST"):
                        if(this.y == v.y && this.x + 100 == v.x)
                            prevDirs.add("EAST");
                        break;
                    case("WEST"):
                        if(this.y == v.y && this.x - 100 == v.x)
                            prevDirs.add("WEST");
                        break;
                }
            }
        }

        boolean move = false;
        if(!prevDirs.isEmpty()){
            for(String gD : goodDirs){
                if(!prevDirs.contains(gD)){
                    move = true;
                    moveNEWS(gD);
                    previousDirection = gD;
                    break;
                }
            }
        }
        if(!move){
            moveNEWS(direction);
            previousDirection = direction;
        }

    }

    public void moveNEWS(String direction){
        switch(direction){
            case("NORTH"):
                this.y -= 100;
                Visited v1 = new Visited(this.x, this.y); visits.add(v1);
                break;
            case("SOUTH"):
                this.y += 100;
                Visited v2 = new Visited(this.x, this.y); visits.add(v2);
                break;
            case("EAST"):
                this.x += 100;
                Visited v3 = new Visited(this.x, this.y); visits.add(v3);
                break;
            case("WEST"):
                this.x -= 100;
                Visited v4 = new Visited(this.x, this.y); visits.add(v4);
                break;
        }
    }

    //boundaries
    public boolean canMove(String direction, ArrayList<Furniture> fs){
        boolean result = true;
        switch(direction){
            case("NORTH") :
                if(y == 35)
                    result = false;
                else{
                    for(Furniture f : fs){
                        if( x-f.x == 25 && y-f.y == 125)
                            result = false;
                    }
                }
                break;

            case("SOUTH") :
                if(y == 435)
                    result = false;
                else{
                    for(Furniture f : fs){
                        if(x-f.x == 25 && f.y-y == 75)
                            result = false;
                    }
                }
                break;

            case("EAST") :
                if(x == 435)
                    result = false;
                else{
                    for(Furniture f : fs){
                        if(f.x-x == 75 && y-f.y == 25)
                            result = false;
                    }
                }
                break;

            case("WEST") :
                if(x == 35)
                    result = false;
                else{
                    for(Furniture f : fs){
                        if(x-f.x == 125 && y-f.y == 25)
                            result = false;
                    }
                }
                break;
        }
        return result;
    }

    public void drawRoboticVacuum(Graphics g){
        g.fillOval(x,y,diameter,diameter);
    }
}

class Visited{
    int x;
    int y;
    public Visited(int x, int y){
        this.x = x;
        this.y = y;
    }
}