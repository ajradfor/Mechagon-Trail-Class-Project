package doslosmuertos.mechagontrail.util;

import android.app.Application;

/**
 * MechagonTrailApplication is basically used to keep track
 * of a global GameState object. In an activity, create a
 * MechagonTrailApplication using getApplication();
 * (i.e. MechagonTrailApplication app = (MechagonTrailApplication)getApplication(); )
 * From there, just use app.getGameState() to manipulate the global
 * GameState object.
 * Created by Will DeVries on 3/22/2015.
 */
public class MechagonTrailApplication extends Application {
    private GameState gameState;

    @Override
    public void onCreate() {
        super.onCreate();
        this.gameState = new GameState();
    }

    public GameState getGameState() {
        return this.gameState;
    }

}
