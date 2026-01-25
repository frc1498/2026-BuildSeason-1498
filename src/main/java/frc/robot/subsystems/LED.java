package frc.robot.subsystems;

import com.ctre.phoenix6.signals.RGBWColor;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LED extends SubsystemBase{

    /** State enumeration for the LEDs.  Based on the state of the robot, the color and flash pattern of the LEDs should change. */
    private enum LEDState {
        IDLE(new RGBWColor(255, 255, 255, 0)),
        SPINUP(new RGBWColor(255, 255, 255, 0)),
        SHOOTING(new RGBWColor(255, 255, 255, 0)),
        CLIMBING(new RGBWColor(255, 255, 255, 0));

        private RGBWColor color;

        /** Set the LED color associated with the state. */
        LEDState(RGBWColor color) {
            this.color = color;
        }

        /**Return the color associated with the state. */
        public RGBWColor color() {
            return this.color;
        }
    }

    /** Create a new LED subsystem. */
    public LED() {

    }
    
    /*private Command setColor(LEDState state) {
        runOnce(() -> {
            candle.set(state.color,)
        });
    }*/

}
