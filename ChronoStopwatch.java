package /* Insert package name here */

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Chronometer;

/**
 * A stopwatch chronometer that can start,stop and reset.
 * Create an object on your activity code and use the methods here.
 * Created by remo5000 on 2/8/2017.
 */

public class ChronoStopwatch extends Chronometer {
    long ChronoStopwatchTimeElapsedSincePause;
    boolean ChronoStopwatchTimerIsRunning;

    public ChronoStopwatch(Context context) {
        super(context, null, 0);
        init();
    }

    public ChronoStopwatch(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        init();
    }

    public ChronoStopwatch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr, 0);
        init();
    }

    public ChronoStopwatch(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init(){
        this.setBase(SystemClock.elapsedRealtime());
        ChronoStopwatchTimerIsRunning = false;
        // -1 used to represent an undefined time elapsed
        ChronoStopwatchTimeElapsedSincePause = -1;
    }

    public void ResetChronometer(){
        if (ChronoStopwatchTimerIsRunning) {   //if chronometer running
            this.stop();                           //stop it now
            ChronoStopwatchTimerIsRunning = false;
        }
        ChronoStopwatchTimeElapsedSincePause = -1;    //set chrono to starting state
        this.setBase(SystemClock.elapsedRealtime());   //reset clock
    }

    public void StartOrStopChronometer(){
        if (!ChronoStopwatchTimerIsRunning) {
            if (ChronoStopwatchTimeElapsedSincePause == -1) {
                this.setBase(SystemClock.elapsedRealtime());
                Log.d("StartOrStopChronometer", "Initial value is " + String.valueOf(this.getBase()));
                this.start();
            } else { 
                this.setBase(SystemClock.elapsedRealtime() - (ChronoStopwatchTimeElapsedSincePause - this.getBase()));
                Log.d("StartOrStopChronometer", "Value on resuming is " + String.valueOf(this.getBase()) 
                    + " and the current timer time elapsed is " + String.valueOf(SystemClock.elapsedRealtime() - this.getBase()));
                this.start();
            }
            ChronoStopwatchTimerIsRunning = true;
        } else {
            // puase the stopwatch
            ChronoStopwatchTimeElapsedSincePause = SystemClock.elapsedRealtime();
            Log.d("StartOrStopChronometer", "Paused value is " + String.valueOf(ChronoStopwatchTimeElapsedSincePause));
            this.stop();
            ChronoStopwatchTimerIsRunning = false;
        }
    }
}
